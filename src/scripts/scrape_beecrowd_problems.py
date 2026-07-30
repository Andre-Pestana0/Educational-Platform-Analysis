"""
Scraper: beecrowd - lista de todos os problemas
URL alvo: https://judge.beecrowd.com/pt/problems/all

Coleta as colunas:
    #, NOME, CATEGORIA, FAVORITO, RESOLVIDOS, NÍVEL

Requisitos:
    pip install playwright --break-system-packages
    playwright install chromium

Uso:
    python scrape_beecrowd_problems.py --email SEU_EMAIL --password SUA_SENHA

Observações importantes:
- A página /pt/problems/all exige login (redireciona para /pt/login se não autenticado).
- NUNCA deixe usuário/senha hardcoded no script; use argumentos de linha de
  comando ou variáveis de ambiente (BEECROWD_EMAIL / BEECROWD_PASSWORD).
- Os seletores de login/tabela abaixo foram escritos de forma defensiva
  (múltiplos fallbacks), pois a estrutura exata do DOM pode mudar entre
  versões da plataforma. Se algum seletor falhar, rode com --headless false
  e --debug para inspecionar visualmente e ajustar.
"""

import argparse
import csv
import os
import sys
import time

from playwright.sync_api import sync_playwright, TimeoutError as PWTimeoutError

BASE_URL = "https://judge.beecrowd.com"
LOGIN_URL = f"{BASE_URL}/pt/login"
PROBLEMS_URL = f"{BASE_URL}/pt/problems/all"

EXPECTED_HEADERS = ["#", "Nome", "Categoria", "Favorito", "Resolvidos", "Nível"]


def log(msg, debug=False):
    if debug:
        print(f"[debug] {msg}")


def dump_debug_artifacts(page, prefix):
    """Salva screenshot e HTML da página atual para diagnóstico."""
    try:
        png_path = f"{prefix}.png"
        html_path = f"{prefix}.html"
        page.screenshot(path=png_path, full_page=True)
        with open(html_path, "w", encoding="utf-8") as f:
            f.write(page.content())
        print(f"[debug] Screenshot salvo em: {png_path}")
        print(f"[debug] HTML salvo em: {html_path}")
    except Exception as e:
        print(f"[debug] Falha ao salvar artefatos de debug: {e}")


def find_first_visible(page, selectors, timeout_each=4000):
    """
    Tenta cada seletor em ordem, esperando ativamente (não apenas checando
    count() imediatamente) por ele ficar visível. Retorna o Locator do
    primeiro que aparecer, ou None se nenhum aparecer dentro do timeout.
    """
    for sel in selectors:
        try:
            loc = page.locator(sel).first
            loc.wait_for(state="visible", timeout=timeout_each)
            return loc
        except PWTimeoutError:
            continue
    return None


def do_login(page, email, password, debug=False):
    log(f"Navegando até {LOGIN_URL}", debug)
    page.goto(LOGIN_URL, wait_until="domcontentloaded")

    # Site é uma SPA: dá tempo para o JS montar o formulário antes de
    # procurar os inputs. wait_for_load_state pode retornar antes do form
    # estar de fato renderizado, então também esperamos por "form" existir.
    try:
        page.wait_for_selector("form", timeout=15000)
    except PWTimeoutError:
        log("Nenhum <form> apareceu em 15s — a página pode ter mudado de estrutura.", debug)

    try:
        page.wait_for_load_state("networkidle", timeout=15000)
    except PWTimeoutError:
        pass

    # Seletores alternativos para o campo de email/usuário. Inclui
    # variações por placeholder/aria-label em pt-BR, caso não haja
    # name/id previsível (comum em apps Angular com formControlName).
    email_selectors = [
        "input#email",
        "input[name='email']",
        "input[type='email']",
        "input[formcontrolname='email']",
        "input[placeholder*='mail' i]",
        "input[aria-label*='mail' i]",
        "input[name*='login' i]",
    ]
    password_selectors = [
        "input#password",
        "input[name='password']",
        "input[type='password']",
        "input[formcontrolname='password']",
        "input[placeholder*='senha' i]",
        "input[aria-label*='senha' i]",
    ]

    email_input = find_first_visible(page, email_selectors)
    if email_input is None:
        dump_debug_artifacts(page, "login_debug")
        raise RuntimeError(
            "Não encontrei o campo de email na página de login. "
            "Salvei login_debug.png e login_debug.html no diretório atual — "
            "me envie o HTML relevante do formulário (a tag <input> do campo "
            "de email/usuário) para eu ajustar o seletor. "
            "Rode também com --no-headless para ver o navegador ao vivo."
        )

    password_input = find_first_visible(page, password_selectors)
    if password_input is None:
        dump_debug_artifacts(page, "login_debug")
        raise RuntimeError(
            "Não encontrei o campo de senha na página de login. "
            "Salvei login_debug.png e login_debug.html no diretório atual — "
            "me envie o HTML relevante do formulário para eu ajustar o seletor."
        )

    email_input.fill(email)
    password_input.fill(password)

    # Botão de submit: id confirmado é #submit-btn
    submit_selectors = [
        "#submit-btn",
        "button:has-text('Entrar')",
        "button[type='submit']",
        "input[type='submit']",
    ]
    submit_btn = find_first_visible(page, submit_selectors, timeout_each=5000)
    if submit_btn is not None:
        submit_btn.click()
    else:
        # fallback: aperta Enter no campo de senha
        password_input.press("Enter")

    # Espera navegação/pós-login. Ajuste a condição se necessário.
    try:
        page.wait_for_load_state("networkidle", timeout=15000)
    except PWTimeoutError:
        pass

    if "/login" in page.url:
        dump_debug_artifacts(page, "login_debug_post_submit")
        raise RuntimeError(
            "Login parece ter falhado (ainda na página de login). "
            "Verifique credenciais ou possível captcha/2FA."
        )
    log(f"Login OK, URL atual: {page.url}", debug)


def extract_table_rows(page, debug=False):
    """
    Extrai as linhas da tabela de problemas na página atual.
    Retorna lista de dicts com as chaves EXPECTED_HEADERS.
    """
    # Espera a tabela carregar. Ajuste o seletor se a tabela tiver
    # id/classe específica (ex: "table.problems-table").
    page.wait_for_selector("table", timeout=20000)

    tables = page.locator("table")
    table_count = tables.count()
    log(f"{table_count} tabela(s) encontrada(s) na página", debug)

    rows_data = []

    for t_idx in range(table_count):
        table = tables.nth(t_idx)
        header_cells = table.locator("thead tr th")
        if header_cells.count() == 0:
            # alguns temas colocam o cabeçalho na primeira <tr> do <tbody>
            header_cells = table.locator("tr").first.locator("th, td")

        headers = [h.inner_text().strip() for h in header_cells.all()]
        log(f"Tabela {t_idx} headers: {headers}", debug)

        # Só processa tabelas que parecem ser a de problemas
        if not any(h in headers for h in ["Nome", "Categoria", "Nível", "#"]):
            continue

        body_rows = table.locator("tbody tr")
        if body_rows.count() == 0:
            body_rows = table.locator("tr")  # fallback sem thead/tbody

        for r_idx in range(body_rows.count()):
            row = body_rows.nth(r_idx)
            cells = row.locator("td")
            if cells.count() == 0:
                continue
            values = [c.inner_text().strip() for c in cells.all()]
            if not values or not values[0]:
                continue

            # Mapeia célula -> nome de coluna esperado, na ordem em que
            # aparecem. Se o número de colunas não bater exatamente com
            # EXPECTED_HEADERS, ainda assim guarda tudo com chaves
            # genéricas col_0, col_1, ... para não perder dado.
            row_dict = {}
            for i, val in enumerate(values):
                if i < len(EXPECTED_HEADERS):
                    row_dict[EXPECTED_HEADERS[i]] = val
                else:
                    row_dict[f"col_{i}"] = val
            rows_data.append(row_dict)

    return rows_data


def go_to_next_page(page, debug=False):
    """
    Tenta clicar no botão/link de "próxima página" da paginação.
    Retorna True se conseguiu avançar, False se não há mais páginas.
    """
    next_selectors = [
        "a[rel='next']",
        "a:has-text('Próxima')",
        "a:has-text('próxima')",
        "li.next a",
        "a.next",
        "button:has-text('Próxima')",
    ]

    for sel in next_selectors:
        el = page.locator(sel).first
        if el.count() > 0:
            # verifica se o elemento não está desabilitado
            is_disabled = el.evaluate(
                "e => e.classList.contains('disabled') "
                "|| e.getAttribute('aria-disabled') === 'true' "
                "|| e.closest('.disabled') !== null"
            )
            if is_disabled:
                return False
            el.click()
            try:
                page.wait_for_load_state("networkidle", timeout=15000)
            except PWTimeoutError:
                pass
            time.sleep(1)  # pequena folga para re-render da tabela
            return True

    return False


def scrape_all_problems(email, password, headless=True, max_pages=None, debug=False):
    all_rows = []

    with sync_playwright() as p:
        browser = p.chromium.launch(headless=headless)
        context = browser.new_context()
        page = context.new_page()

        do_login(page, email, password, debug=debug)

        log(f"Navegando até {PROBLEMS_URL}", debug)
        page.goto(PROBLEMS_URL, wait_until="domcontentloaded")
        try:
            page.wait_for_load_state("networkidle", timeout=15000)
        except PWTimeoutError:
            pass

        page_num = 1
        while True:
            log(f"Extraindo página {page_num}", debug)
            rows = extract_table_rows(page, debug=debug)
            log(f"  -> {len(rows)} linhas coletadas nesta página", debug)
            all_rows.extend(rows)

            if max_pages is not None and page_num >= max_pages:
                break

            advanced = go_to_next_page(page, debug=debug)
            if not advanced:
                log("Não há próxima página (ou botão não encontrado). Encerrando.", debug)
                break
            page_num += 1

        browser.close()

    return all_rows


def save_to_csv(rows, output_path):
    if not rows:
        print("Nenhuma linha coletada; CSV não foi criado.")
        return

    # Une todas as chaves possíveis mantendo a ordem esperada primeiro
    fieldnames = list(EXPECTED_HEADERS)
    for row in rows:
        for k in row.keys():
            if k not in fieldnames:
                fieldnames.append(k)

    with open(output_path, "w", newline="", encoding="utf-8") as f:
        writer = csv.DictWriter(f, fieldnames=fieldnames)
        writer.writeheader()
        for row in rows:
            writer.writerow(row)

    print(f"Salvo: {output_path} ({len(rows)} linhas)")


def main():
    parser = argparse.ArgumentParser(description="Scraper de problemas do beecrowd")
    parser.add_argument("--email", default=os.environ.get("BEECROWD_EMAIL"),
                         help="Email de login (ou defina BEECROWD_EMAIL)")
    parser.add_argument("--password", default=os.environ.get("BEECROWD_PASSWORD"),
                         help="Senha de login (ou defina BEECROWD_PASSWORD)")
    parser.add_argument("--output", default="beecrowd_problems.csv",
                         help="Caminho do arquivo CSV de saída")
    parser.add_argument("--headless", action="store_true", default=True,
                         help="Rodar em modo headless (padrão: True)")
    parser.add_argument("--no-headless", dest="headless", action="store_false",
                         help="Abrir navegador visível (útil para debug)")
    parser.add_argument("--max-pages", type=int, default=None,
                         help="Limitar número de páginas (para testes rápidos)")
    parser.add_argument("--debug", action="store_true", help="Logs verbosos")

    args = parser.parse_args()

    if not args.email or not args.password:
        print(
            "Erro: informe --email e --password, ou defina as variáveis de "
            "ambiente BEECROWD_EMAIL e BEECROWD_PASSWORD.",
            file=sys.stderr,
        )
        sys.exit(1)

    rows = scrape_all_problems(
        email=args.email,
        password=args.password,
        headless=args.headless,
        max_pages=args.max_pages,
        debug=args.debug,
    )

    save_to_csv(rows, args.output)


if __name__ == "__main__":
    main()
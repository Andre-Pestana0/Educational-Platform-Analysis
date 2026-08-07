"""
Scraper: beecrowd - lista de todos os problemas
URL alvo: https://judge.beecrowd.com/pt/problems/all

Coleta as colunas:
    ID, NAME, CATEGORY, SOLVED, LEVEL

Requisitos:
    pip install playwright --break-system-packages
    python3 -m playwright install

Uso:
    python3 scrape_beecrowd_problems.py --email SEU_EMAIL --password SUA_SENHA
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

# Mapeamento do texto exato do cabeçalho HTML para a coluna de saída desejada
HEADER_MAP = {
    "#": "ID",
    "id": "ID",
    "name": "Name",
    "problem": "Name",
    "title": "Name",
    "category": "Category",
    "solved": "Solved",
    "level": "Level"
}

EXPECTED_HEADERS = ["ID", "Name", "Category", "Solved", "Level"]


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
    Tenta cada seletor em ordem, esperando ativamente por ele ficar visível.
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

    try:
        page.wait_for_selector("form", timeout=15000)
    except PWTimeoutError:
        log("Nenhum <form> apareceu em 15s.", debug)

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
        raise RuntimeError("Não encontrei o campo de email na página de login.")

    password_input = find_first_visible(page, password_selectors)
    if password_input is None:
        dump_debug_artifacts(page, "login_debug")
        raise RuntimeError("Não encontrei o campo de senha na página de login.")

    email_input.fill(email)
    password_input.fill(password)

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
        password_input.press("Enter")

    try:
        page.wait_for_url(lambda url: "/login" not in url, timeout=60000)
    except PWTimeoutError:
        dump_debug_artifacts(page, "login_debug_post_submit")
        raise RuntimeError("Tempo esgotado para o login. Verifique o reCAPTCHA ou credenciais.")

    log(f"Login efetuado com sucesso. URL: {page.url}", debug)


def extract_table_rows(page, debug=False):
    """
    Extrai as linhas relacionando cada célula (td) ao nome do seu cabeçalho (th),
    ignorando a coluna 'Favorite'.
    """
    page.wait_for_selector("table", timeout=20000)

    tables = page.locator("table")
    table_count = tables.count()
    log(f"{table_count} tabela(s) encontrada(s) na página", debug)

    rows_data = []

    for t_idx in range(table_count):
        table = tables.nth(t_idx)
        header_cells = table.locator("thead tr th")
        if header_cells.count() == 0:
            header_cells = table.locator("tr").first.locator("th, td")

        col_index_map = {}
        for idx, h in enumerate(header_cells.all()):
            text = h.inner_text().strip().lower()
            
            # Pula a coluna Favorite explicitamente
            if "favorite" in text:
                continue

            # Mapeia para a chave limpa esperada se existir no dicionário
            for key, target_col in HEADER_MAP.items():
                if key in text:
                    col_index_map[idx] = target_col
                    break

        log(f"Tabela {t_idx} Mapeamento de colunas por índice: {col_index_map}", debug)

        body_rows = table.locator("tbody tr")
        if body_rows.count() == 0:
            body_rows = table.locator("tr")

        for r_idx in range(body_rows.count()):
            row = body_rows.nth(r_idx)
            cells = row.locator("td")
            if cells.count() == 0:
                continue

            all_cells = cells.all()
            row_dict = {}

            for idx, target_col in col_index_map.items():
                if idx < len(all_cells):
                    cell = all_cells[idx]
                    
                    # Se houver tag <a> dentro da célula, pega o texto interno do link
                    link = cell.locator("a")
                    if link.count() > 0:
                        val = link.first.inner_text().strip()
                    else:
                        val = cell.inner_text().strip()

                    row_dict[target_col] = val

            if row_dict and any(row_dict.values()):
                rows_data.append(row_dict)

    return rows_data


def go_to_next_page(page, debug=False):
    """
    Tenta clicar no link/botão de próxima página da paginação.
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
            time.sleep(1)
            return True

    return False


def scrape_all_problems(email, password, headless, max_pages=None, debug=False):
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

    with open(output_path, "w", newline="", encoding="utf-8") as f:
        writer = csv.DictWriter(f, fieldnames=EXPECTED_HEADERS)
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
        headless=False,
        max_pages=args.max_pages,
        debug=args.debug,
    )

    save_to_csv(rows, args.output)


if __name__ == "__main__":
    main()

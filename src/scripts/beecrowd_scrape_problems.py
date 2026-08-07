"""
Scraper: beecrowd - list of all problems
Target URL: https://judge.beecrowd.com/en/problems/all

Collects columns:
    ID, NAME, CATEGORY, SOLVED, LEVEL

Requirements:
    pip install playwright --break-system-packages
    python3 -m playwright install

Usage:
    python3 scrape_beecrowd_problems.py --email YOUR_EMAIL --password YOUR_PASSWORD
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

# Mapping of the exact HTML header text to the desired output column
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
    """Saves a screenshot and the HTML of the current page for debugging."""
    try:
        png_path = f"{prefix}.png"
        html_path = f"{prefix}.html"
        page.screenshot(path=png_path, full_page=True)
        with open(html_path, "w", encoding="utf-8") as f:
            f.write(page.content())
        print(f"[debug] Screenshot saved in: {png_path}")
        print(f"[debug] HTML saved in: {html_path}")
    except Exception as e:
        print(f"[debug] Failed to save debug artifacts: {e}")

def find_first_visible(page, selectors, timeout_each=4000):
    """
    Tries each selector in order, actively waiting for it to become visible.
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
    log(f"Navigating to {LOGIN_URL}", debug)
    page.goto(LOGIN_URL, wait_until="domcontentloaded")

    try:
        page.wait_for_selector("form", timeout=15000)
    except PWTimeoutError:
        log("No <form> appeared in 15 seconds.", debug)

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
        raise RuntimeError("Email not found.")

    password_input = find_first_visible(page, password_selectors)
    if password_input is None:
        dump_debug_artifacts(page, "login_debug")
        raise RuntimeError("Password not found.")

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
        raise RuntimeError("Login timeout. Please check reCAPTCHA or credentials.")

    log(f"Successfully logged in. URL: {page.url}", debug)

def extract_table_rows(page, debug=False):
    """
    Extracts the rows mapping each cell (td) to its header name (th), ignoring the 'Favorite' column.
    """
    page.wait_for_selector("table", timeout=20000)

    tables = page.locator("table")
    table_count = tables.count()
    log(f"{table_count} table(s) found on the page", debug)

    rows_data = []

    for t_idx in range(table_count):
        table = tables.nth(t_idx)
        header_cells = table.locator("thead tr th")
        if header_cells.count() == 0:
            header_cells = table.locator("tr").first.locator("th, td")

        col_index_map = {}
        for idx, h in enumerate(header_cells.all()):
            text = h.inner_text().strip().lower()
            
            if "favorite" in text:
                continue

            for key, target_col in HEADER_MAP.items():
                if key in text:
                    col_index_map[idx] = target_col
                    break

        log(f"Table {t_idx} Column mapping by index: {col_index_map}", debug)

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
    Tries to click the next page link/button in the pagination.
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

        log(f"Navigating to {PROBLEMS_URL}", debug)
        page.goto(PROBLEMS_URL, wait_until="domcontentloaded")
        try:
            page.wait_for_load_state("networkidle", timeout=15000)
        except PWTimeoutError:
            pass

        page_num = 1
        while True:
            log(f"Extracting page {page_num}", debug)
            rows = extract_table_rows(page, debug=debug)
            log(f"  -> {len(rows)} rows collected on this page", debug)
            all_rows.extend(rows)

            if max_pages is not None and page_num >= max_pages:
                break

            advanced = go_to_next_page(page, debug=debug)
            if not advanced:
                log("No next page available (or button not found). Ending.", debug)
                break
            page_num += 1

        browser.close()

    return all_rows

def save_to_csv(rows, output_path):
    if not rows:
        print("No rows collected; CSV was not created.")
        return

    with open(output_path, "w", newline="", encoding="utf-8") as f:
        writer = csv.DictWriter(f, fieldnames=EXPECTED_HEADERS)
        writer.writeheader()
        for row in rows:
            writer.writerow(row)

    print(f"Saved: {output_path} ({len(rows)} rows)")

def main():
    parser = argparse.ArgumentParser(description="Beecrowd problems scraper")
    parser.add_argument("--email", default=os.environ.get("BEECROWD_EMAIL"),
                        help="Email")
    parser.add_argument("--password", default=os.environ.get("BEECROWD_PASSWORD"),
                        help="Password")
    parser.add_argument("--output", default="beecrowd_problems.csv",
                        help="CSV location")
    parser.add_argument("--max-pages", type=int, default=None,
                        help="Number of pages")
    parser.add_argument("--debug", action="store_true", help="Verbose logs")

    args = parser.parse_args()

    if not args.email or not args.password:
        print(
            "Error: type --email and --password",
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

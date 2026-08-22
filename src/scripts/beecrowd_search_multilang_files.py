import os
import re
import shutil
from pathlib import Path
from typing import Set
import pandas as pd

# Path and folder configurations
MISSING_CSV_PATH = "beecrowd_missing_solutions.csv"
REPOS_DIR = "repos"
OUTPUT_DIR = "found_multilang_implementations"

# Common programming file extensions mapping
CODE_EXTENSIONS: Set[str] = {
    ".java", ".py", ".cpp", ".c", ".cs", ".js", ".ts", ".rb", 
    ".go", ".rs", ".kt", ".swift", ".php", ".scala", ".hs", ".pas"
}


def clean_repo_name(raw_name: str) -> str:
    """Removes trailing -master or _master suffix from repository folder names."""
    return re.sub(r"[-_]master$", "", raw_name, flags=re.IGNORECASE)


def get_repo_folder_name(root_path: str, base_repos_dir: str) -> str:
    """Extracts and cleans the top-level repository name under REPOS_DIR."""
    rel_path = os.path.relpath(root_path, base_repos_dir)
    parts = rel_path.split(os.sep)
    raw_repo = parts[0] if parts and parts[0] != "." else "unknown_repo"
    return clean_repo_name(raw_repo)


def find_multilang_solutions() -> None:
    """Searches for unresolved problem IDs across multi-language source files in the repos folder."""
    # 1. Validate paths
    if not os.path.exists(MISSING_CSV_PATH):
        print(f"Error: CSV file '{MISSING_CSV_PATH}' not found.")
        return

    if not os.path.exists(REPOS_DIR):
        print(f"Error: Source directory '{REPOS_DIR}' not found.")
        return

    # 2. Read IDs from CSV
    df = pd.read_csv(MISSING_CSV_PATH)
    if "ID" not in df.columns:
        print("Error: 'ID' column not found in the CSV file.")
        return

    multilang_ids = df["ID"].dropna().astype(int).astype(str).unique()
    print(f"Total IDs to search: {len(multilang_ids)}")

    os.makedirs(OUTPUT_DIR, exist_ok=True)

    # Helper function to check if the file matches an ID as a standalone token
    def file_matches_id(filename: str, ex_id: str) -> bool:
        name_without_ext, ext = os.path.splitext(filename)
        if ext.lower() not in CODE_EXTENSIONS:
            return False
        
        tokens = re.split(r'[^0-9]', name_without_ext)
        return ex_id in tokens

    found_count = 0
    still_multilang = []

    # 3. Search for each ID inside the 'repos' directory
    for ex_id in multilang_ids:
        found = False

        for root, _, files in os.walk(REPOS_DIR):
            for file in files:
                if file_matches_id(file, ex_id):
                    source_path = os.path.join(root, file)
                    
                    # Extract language from extension (e.g., '.py' -> 'py')
                    _, ext = os.path.splitext(file)
                    lang = ext.lstrip(".").lower()

                    # Extract cleaned repository folder name under 'repos/'
                    repo_folder = get_repo_folder_name(root, REPOS_DIR)

                    # Build new filename: NUMERO_DIRETORIO_LINGUAGEM.EXTENSAO
                    new_filename = f"{ex_id}_{repo_folder}_{lang}{ext}"
                    target_path = os.path.join(OUTPUT_DIR, new_filename)

                    # Prevent overwriting if a file with the same new name already exists
                    if os.path.exists(target_path):
                        base_name, _ = os.path.splitext(new_filename)
                        target_path = os.path.join(OUTPUT_DIR, f"{base_name}_dup{ext}")

                    shutil.copy2(source_path, target_path)
                    print(f"[FOUND] ID {ex_id}: '{file}' -> '{os.path.basename(target_path)}'")
                    found_count += 1
                    found = True
                    break  # Move to the next ID after finding the first matching solution

            if found:
                break

        if not found:
            still_multilang.append(ex_id)

    # 4. Final summary report
    print("\n" + "="*40)
    print("SEARCH SUMMARY")
    print("="*40)
    print(f"Total IDs searched        : {len(multilang_ids)}")
    print(f"Solutions found & copied  : {found_count}")
    print(f"Still unresolved          : {len(still_multilang)}")

    if still_multilang:
        print(f"IDs remaining unresolved  : {', '.join(still_multilang)}")


if __name__ == "__main__":
    find_multilang_solutions()

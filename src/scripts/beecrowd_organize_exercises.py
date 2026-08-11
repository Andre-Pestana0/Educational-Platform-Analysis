import os
import shutil
import pandas as pd

# Path and folder configurations
EXCEL_PATH = "beecrowd_problems_shared.xlsx"
TARGET_DIR = "java_implementations"
SEARCH_DIR_SUFFIX = "_Java"  # Searches folders containing or ending with '_Java' (e.g., Repository_Java, Solutions_Java)

def find_and_copy_exercises():
    # 1. Ensure the target directory exists
    os.makedirs(TARGET_DIR, exist_ok=True)

    # 2. Read the Excel file
    if not os.path.exists(EXCEL_PATH):
        print(f"Error: File '{EXCEL_PATH}' not found.")
        return

    df = pd.read_excel(EXCEL_PATH)
    
    if "ID" not in df.columns:
        print("Error: 'ID' column not found in the spreadsheet.")
        return

    # Clean missing values and convert IDs to integer strings
    exercise_ids = df["ID"].dropna().astype(int).astype(str).unique()

    # 3. Map all directories with '_Java' in their name within the current directory
    java_directories = [
        d for d in os.listdir(".") 
        if os.path.isdir(d) and d != TARGET_DIR and (d.endswith(SEARCH_DIR_SUFFIX) or SEARCH_DIR_SUFFIX in d)
    ]

    print(f"Total exercises to check: {len(exercise_ids)}")
    print(f"'_Java' directories found for search: {java_directories}\n")

    copied_count = 0
    already_existed = 0
    not_found_ids = []

    for ex_id in exercise_ids:
        # Helper function to check if a filename matches the exercise ID
        def file_matches_id(filename):
            name_without_ext, _ = os.path.splitext(filename)
            # Checks if ID exists as a standalone token or part of the name (e.g., 1000.java, Main_1000.java, P1000.java)
            return ex_id in name_without_ext.split("_") or ex_id in name_without_ext.split("-") or ex_id in name_without_ext

        # Pass 1: Check if the file is already inside the 'java_implementations' directory
        target_files = os.listdir(TARGET_DIR)
        found_in_target = any(
            os.path.isfile(os.path.join(TARGET_DIR, f)) and file_matches_id(f)
            for f in target_files
        )

        if found_in_target:
            already_existed += 1
            continue

        # Pass 2: Search inside *_Java directories
        found_in_other_folder = False

        for java_dir in java_directories:
            # Traverse through directory and subdirectories
            for root, _, files in os.walk(java_dir):
                for file in files:
                    if file.endswith(".java") and file_matches_id(file):
                        source_path = os.path.join(root, file)
                        target_path = os.path.join(TARGET_DIR, file)
                        
                        # Copy file preserving metadata
                        shutil.copy2(source_path, target_path)
                        print(f"[COPIED] '{file}' from '{java_dir}' -> '{TARGET_DIR}'")
                        copied_count += 1
                        found_in_other_folder = True
                        break
                
                if found_in_other_folder:
                    break
            
            if found_in_other_folder:
                break

        if not found_in_other_folder:
            not_found_ids.append(ex_id)

    # Final summary report
    print("\n" + "="*40)
    print("OPERATION SUMMARY")
    print("="*40)
    print(f"Already existed in target : {already_existed}")
    print(f"Files copied              : {copied_count}")
    print(f"Not found                 : {len(not_found_ids)}")
    
    if not_found_ids:
        print(f"Missing IDs: {', '.join(not_found_ids)}")

if __name__ == "__main__":
    find_and_copy_exercises()
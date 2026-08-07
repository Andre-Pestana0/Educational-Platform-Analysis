import re
from pathlib import Path

def rename_java_files(directory: str):
    folder = Path(directory)
    
    if not folder.exists() or not folder.is_dir():
        print(f"Error: The directory '{directory}' does not exist or is not a folder.")
        return

    # Search for files with the .java extension only
    for file in folder.glob("*.java"):
        # Extract digits from the filename
        digits = re.findall(r'\d+', file.stem)
        
        if not digits:
            print(f"Skipped (no numbers found): {file.name}")
            continue
            
        # Join extracted digits (e.g., 'URI 1000' -> '1000')
        number_id = "".join(digits)
        new_name = f"S_{number_id}.java"
        new_path = folder / new_name
        
        # Skip if already using the target name
        if file.name == new_name:
            continue

        # Check if the target filename already exists
        if new_path.exists():
            print(f"Kept original: '{file.name}' ('{new_name}' already exists)")
        else:
            try:
                file.rename(new_path)
                print(f"Renamed: '{file.name}' -> '{new_name}'")
            except Exception as e:
                print(f"Error renaming '{file.name}': {e}")

if __name__ == "__main__":
    folder_path = "./solutions"
    rename_java_files(folder_path)
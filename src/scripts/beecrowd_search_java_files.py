from pathlib import Path
import re
import shutil
import sys


def clean_repo_name(raw_name: str) -> str:
    """Removes trailing -master or _master suffix from the repository name."""
    return re.sub(r"[-_]master$", "", raw_name, flags=re.IGNORECASE)


def extract_exercise_id(java_file: Path) -> str:
    """Extracts the exercise ID from the filename or its parent directory hierarchy.
    
    First inspects the filename itself for a 3-to-5 digit number. If absent (e.g., Main.java),
    traverses up through the parent directories to locate an ID within the directory structure (e.g., folder '1000').
    """
    # 1. Check the file name stem first
    matches = re.findall(r"\b(\d{3,5})\b", java_file.stem)
    if matches:
        return matches[0]
    
    # 2. Fallback: Traverse parent directories up to the repository root
    for parent in java_file.parents:
        parent_matches = re.findall(r"\b(\d{3,5})\b", parent.name)
        if parent_matches:
            return parent_matches[0]
            
    # 3. Final fallback: First consecutive digits in the file stem
    digits = re.findall(r"\d+", java_file.stem)
    return digits[0] if digits else ""


def process_repositories(base_path: str = ".") -> None:
    base_dir = Path(base_path).resolve()

    # Clean repository name by stripping -master / _master
    raw_repo_name = base_dir.name
    repo_name = clean_repo_name(raw_repo_name)

    print(
        f"Starting scan in repository: {raw_repo_name} -> Using name: {repo_name}"
    )

    # Define output directory (e.g., URI-Online-Judge-Solutions_Java)
    output_dir = base_dir.parent / f"{repo_name}_Java"

    copied_count = 0
    skipped_count = 0

    # Search recursively for all .java files inside the directory
    for java_file in base_dir.rglob("*.java"):
        # Extract ID from file path (checks file stem first, then parent directory hierarchy)
        number_str = extract_exercise_id(java_file)

        if number_str:
            number = int(number_str)
            
            # Filter exercises strictly within the 1000 to 3505 ID range
            if 1000 <= number <= 3505:
                # Ensure the output directory exists
                output_dir.mkdir(parents=True, exist_ok=True)

                # Build new filename
                new_file_name = f"S_{number}_{repo_name}{java_file.suffix}"
                destination = output_dir / new_file_name

                # Prevent overwriting if the file already exists in the output directory
                if destination.exists():
                    skipped_count += 1
                    continue

                # Copy file preserving metadata
                shutil.copy2(java_file, destination)
                print(f"Copied: {java_file.name} (ID: {number}) -> {destination.name}")
                copied_count += 1

    print("Process completed successfully!")
    print(f"Total copied: {copied_count} | Skipped (already exist): {skipped_count}")


if __name__ == "__main__":
    target_path = sys.argv[1] if len(sys.argv) > 1 else "."
    process_repositories(target_path)

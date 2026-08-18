from pathlib import Path
import re
import shutil
import sys


def clean_repo_name(raw_name: str) -> str:
    """Removes trailing -master or _master suffix from the repository name."""
    return re.sub(r"[-_]master$", "", raw_name, flags=re.IGNORECASE)


def extract_exercise_id(java_file: Path, base_dir: Path) -> str:
    """Extracts the exercise ID (EXACTLY 4 digits) from the filename or parent directories.
    
    Stops searching parents once it reaches the base repository directory.
    """
    # 1. Check the file name stem first (e.g., p1000.java, 1000_Solution.java)
    matches = re.findall(r"(?:^|[^\d])(\d{4})(?:[^\d]|$)", java_file.stem)
    if matches:
        return matches[0]

    # 2. Traverse parent directories up to (and including) base_dir
    for parent in [java_file.parent] + list(java_file.parents):
        parent_matches = re.findall(r"(?:^|[^\d])(\d{4})(?:[^\d]|$)", parent.name)
        if parent_matches:
            return parent_matches[0]
            
        # Stop traversing higher than the repository root
        if parent.resolve() == base_dir.resolve():
            break

    # 3. Final fallback: First sequence of exactly 4 digits in the file stem
    digits = re.findall(r"\b\d{4}\b", java_file.stem)
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
        # Extract ID from file path (checks file stem first, then parent directory hierarchy up to base_dir)
        number_str = extract_exercise_id(java_file, base_dir)

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

from pathlib import Path
import re
import shutil
import sys


def clean_repo_name(raw_name: str) -> str:
    return re.sub(r"[-_]master$", "", raw_name, flags=re.IGNORECASE)


def extract_digits(filename_stem: str) -> str:
    digits = re.findall(r"\d+", filename_stem)
    return "".join(digits) if digits else ""


def process_repositories(base_path: str = ".") -> None:
    base_dir = Path(base_path).resolve()

    # Clean repo name by removing -master / _master
    raw_repo_name = base_dir.name
    repo_name = clean_repo_name(raw_repo_name)

    print(
        f"Starting scan in repository: {raw_repo_name} -> Using name: {repo_name}"
    )

    # Define output directory (e.g., URI-Online-Judge-Solutions_Java)
    output_dir = base_dir.parent / f"{repo_name}_Java"

    # Search recursively for all .java files inside the directory
    for java_file in base_dir.rglob("*.java"):
        # Extract digits from the filename
        number_str = extract_digits(java_file.stem)

        if number_str:
            number = int(number_str)
            
            # Filtra apenas exercícios no intervalo de 1000 a 3505
            if 1000 <= number <= 3505:
                # Ensure the output directory exists
                output_dir.mkdir(parents=True, exist_ok=True)

                # Build new filename
                new_file_name = f"S_{number}_{repo_name}{java_file.suffix}"
                destination = output_dir / new_file_name

                # Copy file to output directory
                shutil.copy2(java_file, destination)
                print(f"Copied: {java_file.name} -> {destination.name}")

    print("Process completed successfully!")


if __name__ == "__main__":
    target_path = sys.argv[1] if len(sys.argv) > 1 else "."
    process_repositories(target_path)

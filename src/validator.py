import os
import subprocess

BASE_DIR = os.path.dirname(os.path.abspath(__file__))


def load_test_cases(file_path):
    if not os.path.exists(file_path):
        return []

    with open(file_path, "r", encoding="utf-8") as f:
        content = f.read().strip()

    # Split by blank line
    cases = content.split("\n\n")
    return [case.strip() for case in cases if case.strip()]


def run_python_file(file_path, test_input):
    try:
        result = subprocess.run(
            ["python", file_path],
            input=test_input,
            text=True,
            capture_output=True,
            timeout=5
        )

        return result.stdout.strip()

    except subprocess.TimeoutExpired:
        return "TIMEOUT"
    except Exception as e:
        return f"ERROR: {e}"


def validate_problem(code):
    problem_file = os.path.join(BASE_DIR, "Iniciante", f"{code}.py")
    input_file = os.path.join(BASE_DIR, "llm_outputs", f"{code}_input.txt")
    output_file = os.path.join(BASE_DIR, "llm_outputs", f"{code}_output.txt")

    inputs = load_test_cases(input_file)
    expected_outputs = load_test_cases(output_file)

    mismatches = []
    print(f"Number of test cases for problem {code}: {len(inputs)}")
    if len(inputs) != len(expected_outputs):
        print(f"[{code}] Mismatch in number of test cases!")
        return [{
            "type": "count_mismatch",
            "message": "Input/output count mismatch",
            "input_count": len(inputs),
            "output_count": len(expected_outputs)
        }]

    for i, (test_input, expected_output) in enumerate(zip(inputs, expected_outputs), start=1):
        actual_output = run_python_file(problem_file, test_input)

        if actual_output != expected_output:
            mismatches.append({
                "test_number": i,
                "input": test_input,
                "expected": expected_output,
                "actual": actual_output
            })

    return mismatches


def main():
    # llm_output_dir = os.path.join(BASE_DIR, "llm_outputs")
    llm_output_dir = os.path.join(BASE_DIR, "llm_outputs_after_vacation")

    all_mismatches = {}

    for file in os.listdir(llm_output_dir):
        if file.endswith("_input.txt"):
            code = file.replace("_input.txt", "")

            if code != "3173":
                continue

            print(f"Processing file: {file} for code {code}")
    
            # if int(code) < 3253 or int(code) > 3255:
            #     continue

            mismatches = validate_problem(code)

            if mismatches:
                all_mismatches[code] = mismatches
            else:
                print(f"Problem {code} passed all test cases!")

    print("\n" + "=" * 60)
    print("VALIDATION SUMMARY")
    print("=" * 60)

    if not all_mismatches:
        print("All problems passed successfully ✅")
        return

    for code, mismatches in all_mismatches.items():
        print(f"\nProblem {code} failed {len(mismatches)} test(s):")

        for m in mismatches:
            print("\n---")

            if not isinstance(m, dict):
                print("Unexpected error format:")
                print(m)
                continue

            if m.get("type") == "count_mismatch":
                print("Count mismatch error:")
                print(f"Inputs: {m['input_count']}")
                print(f"Outputs: {m['output_count']}")
                continue

            # print(f"Test #{code} teve um problema")
            print(f"Test #{m.get('test_number', '?')}")
            print("Input:")
            print(m.get("input", "N/A"))
            print("Expected:")
            print(m.get("expected", "N/A"))
            print("Actual:")
            print(m.get("actual", "N/A"))


if __name__ == "__main__":
    main()

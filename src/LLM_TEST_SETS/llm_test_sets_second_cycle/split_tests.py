import json
import os
import re


def process(txt_file):
    numbers = [
        1150, 2861, 2147, 1036, 1848, 2313
    ]
    
    with open(txt_file, 'r', encoding='utf-8') as f:
        content = f.read()

    content = re.sub(r',\s*(\]|\})', r'\1', content)
    cases = json.loads(content)

    basename = os.path.basename(txt_file)
    match = re.match(r'^(\d+)_', basename)
    if not match:
        print(f"Skipping {txt_file}: filename doesn't start with a number.")
        return

    number = int(match.group(1))
    if number not in numbers:
        print(f"Skipping {txt_file}: number {number} not in list.")
        return

    input_file  = f"{number}_input.txt"
    output_file = f"{number}_output.txt"

    inputs  = []
    outputs = []

    for case in cases:
        inputs.append(case["input"].replace("\\n", "\n"))
        outputs.append(case["output"].replace("\\n", "\n"))

    with open(input_file, 'w', encoding='utf-8') as f:
        f.write("\n\n".join(inputs))

    with open(output_file, 'w', encoding='utf-8') as f:
        f.write("\n\n".join(outputs))

    print(f"Written: {input_file}")
    print(f"Written: {output_file}")

if __name__ == "__main__":
    # Find all files matching <number>_input_output.txt in the current directory
    candidates = [
        f for f in os.listdir(".")
        if re.match(r'^\d+_input_output\.txt$', f)
    ]

    if not candidates:
        print("No files matching <number>_input_output.txt found.")
    else:
        for filename in sorted(candidates):
            process(filename)
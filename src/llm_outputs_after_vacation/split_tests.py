import json
import sys
import os
import re
 
def process(txt_file):
    with open(txt_file, 'r', encoding='utf-8') as f:
        content = f.read()
 
    # Remove trailing commas before ] or } (common LLM output issue)
    content = re.sub(r',\s*(\]|\})', r'\1', content)
 
    cases = json.loads(content)
 
    base = os.path.splitext(os.path.basename(txt_file))[0]
    input_file  = f"{base}_input.txt"
    output_file = f"{base}_output.txt"
 
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
    if len(sys.argv) != 2:
        print("Usage: python split_tests.py <file.txt>")
        sys.exit(1)
    process(sys.argv[1])
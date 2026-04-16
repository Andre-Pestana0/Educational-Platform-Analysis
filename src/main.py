import os
import json
from dotenv import load_dotenv
from openai import OpenAI, RateLimitError


BASE_DIR = os.path.dirname(os.path.abspath(__file__))

def load_description(code):
    file_path = os.path.join(
        BASE_DIR,
        "all_exercises_descriptions",
        f"{code}.txt"
    )

    if not os.path.exists(file_path):
        print(f"File not found for code {code}: {file_path}")
        return ""

    with open(file_path, "r", encoding="utf-8") as file_description:
        content = file_description.read()
        return content


def load_base_prompt(prompt_name: str) -> str:
    file_path = os.path.join(
        BASE_DIR,
        f"{prompt_name}.txt"
    )
    
    if not os.path.exists(file_path):
        print(f"File not found for base prompt: {file_path}")
        return ""

    with open(file_path, "r", encoding="utf-8") as file_description:
        content = file_description.read()
        return content
    
def load_exercise(code):
    file_path = os.path.join(
        BASE_DIR,
        "Iniciante",
        f"{code}.py"
    )

    if not os.path.exists(file_path):
        print(f"File not found for code {code}: {file_path}")
        return ""

    with open(file_path, "r", encoding="utf-8") as file_exercise:
        content = file_exercise.read()
        return content

def clean_json_response(response: str) -> str:
    response = response.strip()

    # Remove accidental markdown fences
    if response.startswith("```"):
        parts = response.split("```")
        if len(parts) >= 2:
            response = parts[1]

    return response.strip()

def parse_llm_json(response: str):
    try:
        cleaned = clean_json_response(response)
        data = json.loads(cleaned)

        if not isinstance(data, list):
            raise ValueError("Response is not a list")

        inputs = []
        outputs = []

        for item in data:
            if "input" not in item or "output" not in item:
                raise ValueError("Missing keys in JSON object")

            inputs.append(item["input"])
            outputs.append(item["output"])

        if len(inputs) == 0:
            raise ValueError("No test cases generated")

        return inputs, outputs

    except Exception as e:
        print("JSON parsing failed:", e)
        print("Raw response:\n", response)
        return None, None


def save_io_files(code, inputs, outputs):
    output_dir = os.path.join(BASE_DIR, "llm_outputs")
    os.makedirs(output_dir, exist_ok=True)

    input_path = os.path.join(output_dir, f"{code}_input.txt")
    output_path = os.path.join(output_dir, f"{code}_output.txt")

    # Save all inputs separated by blank line
    with open(input_path, "w", encoding="utf-8") as f:
        for inp in inputs:
            f.write(inp + "\n\n")

    # Save all outputs separated by blank line
    with open(output_path, "w", encoding="utf-8") as f:
        for out in outputs:
            f.write(out + "\n\n")

def save_io_files_simplified(code, text_to_save):
    output_dir = os.path.join(BASE_DIR, "llm_outputs")
    os.makedirs(output_dir, exist_ok=True)

    input_path = os.path.join(output_dir, f"{code}_input_output.txt")

    # Save inputs and outputs together in a single file
    with open(input_path, "w", encoding="utf-8") as f:
        f.write(text_to_save)

            
def save_pre_post_conditions(code, conditions):
    output_dir = os.path.join(BASE_DIR, "pre_pos_conditions")
    os.makedirs(output_dir, exist_ok=True)

    condition_path = os.path.join(output_dir, f"{code}_condition.txt")

    with open(condition_path, "w", encoding="utf-8") as f:
        f.write(conditions)

def save_formal_proof(code, proof):
    output_dir = os.path.join(BASE_DIR, "Proof_2.0")
    os.makedirs(output_dir, exist_ok=True)

    proof_path = os.path.join(output_dir, f"{code}_proof.txt")

    with open(proof_path, "w", encoding="utf-8") as f:
        f.write(proof)


def generate_examples(prompt: str) -> str:
    api_key = os.getenv("OPENROUTER_API_KEY")
    client = OpenAI(
        base_url="https://openrouter.ai/api/v1",
        api_key=api_key,
    )

    completion = None

    for attempt in range(5):
        try:
            print("Attempting API call...")
            completion = client.chat.completions.create(
                model="openai/gpt-oss-120b",
                temperature=0.7, 
                messages=[
                    {
                    "role": "user",
                    "content": prompt
                    }
                ],
            )
            break
        except RateLimitError as e:
            print(f"Attempt {attempt + 1} failed: {e}")
            if attempt < 4:
                print("Retrying...")
            else:
                print("All attempts failed.")
                return ""
        except Exception as e:
            print(f"An error occurred: {e}")
            return ""
    print("API call successful.")
    response = completion.choices[0].message.content
    return response
    
def save_output(code, output, output_dir):
    output_dir = os.path.join(BASE_DIR, "llm_outputs")
    os.makedirs(output_dir, exist_ok=True)
    output_path = os.path.join(output_dir, f"{code}.txt")
    with open(output_path, "w", encoding="utf-8") as output_file:
        output_file.write(output)

 
def code_logic(code_list):
    # base_prompt = load_base_prompt("prompt")
    base_prompt = load_base_prompt("prompt_simple_version")

    for code in code_list:
        description = load_description(code)

        if not description:
            continue

        full_prompt = f"{base_prompt}\n\n{description}"

        llm_response = generate_examples(full_prompt)
        
        print(f"Got response, which is:\n{llm_response}")
        # print(f"This is the raw LLM response for code {code}:\n{llm_response}\n")

        # inputs, outputs = parse_llm_json(llm_response)

        # if inputs is None:
        #     print(f"Skipping {code} due to invalid JSON.\n")
        #     continue

        print(f"Code: {code}")
        # print(f"Generated {len(inputs)} test cases.")
        print("-" * 40)

        save_io_files_simplified(code, llm_response)
        # save_io_files(code, inputs, outputs)

def conditions_generation_logic(code_list):
    base_prompt = load_base_prompt("condition_generation_prompt")

    for code in code_list:
        description = load_description(code)

        if not description:
            continue

        full_prompt = f"{base_prompt}\n\n{description}"

        llm_response = generate_examples(full_prompt)


        print(f"Code: {code}")
        print(f"Generated pre & post conditions.")
        print("-" * 40)

        save_pre_post_conditions(code, llm_response)

def load_conditions(code):
    file_path = os.path.join(
        BASE_DIR,
        "pre_pos_conditions",
        f"{code}_condition.txt"
    )

    with open(file_path, "r", encoding="utf-8") as file_condition:
        content = file_condition.read()
        return content
    
    
def formal_specification_validation_logic(code_list):
    base_prompt = load_base_prompt("final_verification_prompt")

    for code in code_list:
        exercise = load_exercise(code)

        if not exercise:
            continue

        try:
            loaded_conditions = load_conditions(code)

            full_prompt = f"{base_prompt}\n\n{exercise} \n\n With the conditions:{loaded_conditions}"

            llm_response = generate_examples(full_prompt)
            print(f"Code: {code}")
            print(f"Generated formal proof.")
            print("-" * 40)

            save_formal_proof(code, llm_response)

        except Exception as e:
            print(f"Error loading conditions for code {code}: {e}")
            continue

    
def main():
    exercises_to_generate_solutions = [1188]
    
    missing_conditions_group = [
        1181
    ]
    
    missing_proofs_group = [
        1181
    ]
    
    # iniciante_codes = [
    #     1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009,
    #     1010, 1011, 1012, 1013, 1014, 1015, 1016, 1017, 1018, 1019,
    #     1020, 1021, 1035, 1036, 1037, 1038, 1040, 1041, 1042, 1043,
    #     1044, 1045, 1046, 1047, 1048, 1049, 1050, 1051, 1052, 1059,
    #     1060, 1061, 1064, 1065, 1066, 1067, 1070, 1071, 1072, 1073,
    #     1074, 1075, 1078, 1079, 1080, 1094, 1095, 1096, 1097, 1098,
    #     1099, 1101, 1113, 1114, 1115, 1116, 1117, 1118, 1131, 1132,
    #     1133, 1134, 1142, 1143, 1144, 1145, 1146, 1149, 1150, 1153,
    #     1154, 1155, 1156, 1157, 1158, 1159, 1164, 1165, 1172, 1173,
    #     1174, 1176, 1177, 1178, 1180, 1181, 1182, 1183, 1185, 1186,
    #     1187, 1188, 1189, 1190, 1262, 1478, 1534, 1541, 1557, 1564,
    #     1589, 1759, 1789, 1827, 1828, 1837, 1848, 1858, 1864, 1865,
    #     1866, 1871, 1914, 1924, 1930, 1933, 1957, 1960, 1963, 1973,
    #     1985, 2003, 2006, 2028, 2029, 2031, 2059, 2060, 2061, 2140,
    #     2146, 2147, 2152, 2159, 2161, 2162, 2164, 2166, 2167, 2168,
    #     2172, 2176, 2221, 2234, 2235, 2310, 2313, 2344, 2483, 2543,
    #     2581, 2587, 2670, 2702, 2708, 2717, 2728, 2747, 2748, 2750,
    #     2752, 2753, 2754, 2756, 2763, 2774, 2779, 2780, 2786, 2787,
    #     2791, 2861, 2862, 2879, 2896, 2936, 2950, 2987, 3040, 3046,
    #     3047, 3055, 3091, 3140, 3142, 3145, 3146, 3147, 3157, 3161,
    #     3170, 3173, 3174, 3209, 3250, 3252, 3253, 3255, 3256
    # ]

    # code_logic(exercises_to_generate_solutions)
    conditions_generation_logic(missing_conditions_group)
    formal_specification_validation_logic(missing_proofs_group)

    
if __name__ == "__main__":
    load_dotenv()
    main()

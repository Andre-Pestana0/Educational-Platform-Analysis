import os
import json
from dotenv import load_dotenv
from openai import OpenAI, RateLimitError


BASE_DIR = os.path.dirname(os.path.abspath(__file__))

def load_description(code): 
    counter = 0  
    folders = [
            "exercises_ad-hoc_descriptions", 
            "exercises_string_descriptions", 
            "exercises_data-structures_descriptions", 
            "exercises_math_descriptions", 
            "exercises_data-structures_descriptions" 
        ]

    for each_folder in folders:
        file_path = os.path.join(
            BASE_DIR,
            "all_exercises_descriptions",
            each_folder,
            f"{code}.txt"
        )

        if counter >= len(folders):
            print(f"Description file not found for code {code} in any folder.")
            return ""

        if not os.path.exists(file_path):
            print(f"File not found for code {code}: {file_path}")
            counter += 1
            continue

        if os.path.exists(file_path):
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
    
    exercises_folders = ["Iniciante", "Ad-HOC", "Data-Structures & Libraries", "Mathematics", "Strings"]
    
    for folder in exercises_folders:
        file_path = os.path.join(
            BASE_DIR,
            folder,
            f"{code}.py"
        )

        if not os.path.exists(file_path):
            print(f"File not found for code {code} in folder {folder}: {file_path}")
            continue

        if os.path.exists(file_path):
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
    output_dir = os.path.join(BASE_DIR, "llm_outputs_after_vacation")
    os.makedirs(output_dir, exist_ok=True)

    input_path = os.path.join(output_dir, f"{code}_input_output.txt")

    # Save inputs and outputs together in a single file
    with open(input_path, "w", encoding="utf-8") as f:
        f.write(text_to_save)

            
def save_pre_post_conditions(code, conditions):
    output_dir = os.path.join(BASE_DIR, "pre_pos_conditions_new")
    os.makedirs(output_dir, exist_ok=True)

    condition_path = os.path.join(output_dir, f"{code}_condition.txt")

    with open(condition_path, "w", encoding="utf-8") as f:
        f.write(conditions)

def save_formal_proof(code, proof):
    output_dir = os.path.join(BASE_DIR, "Proof_new")
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
    base_prompt = load_base_prompt("test_generation_prompt_refined")

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
        "pre_pos_conditions_new",
        # "pre_pos_conditions",
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
    exercises_to_generate_solutions = [1024, 2839, 3248,1893]
    
    missing_conditions_group = [
        1024, 2839, 3248,1893
    ]
   
    missing_proofs_group = [
        3248
    ]

    # code_logic(exercises_to_generate_solutions)
    # conditions_generation_logic(missing_conditions_group)
    formal_specification_validation_logic(missing_proofs_group)

    
if __name__ == "__main__":
    load_dotenv()
    main()

# Instructions

This repository contains a study conducted on an online educational platform.

## Folder Structure

Below is a brief explanation of the purpose and contents of each folder.

| Folder | Content |
|---|---|
| `challenges_implementation` | Contains all gathered challenge implementations in Python. |
| `challenges_specification` | Contains all gathered problem specifications. |
| `LLM_TEST_SETS` | Contains all generated test cases. |
| `PRE_POS` | Contains all generated preconditions and postconditions. |
| `PROMPTS` | Contains all prompts and prompt variations used throughout the study. |
| `PROOF` | Contains all generated formal proofs. |

---

## Main Files

Below is a brief explanation of the purpose of the main files in this repository.

| File | Content |
|---|---|
| `main.py` | Main logic responsible for generating test cases, preconditions, postconditions, and formal proofs. |
| `validator.py` | Main logic used to validate the correctness of generated test cases against the expected outputs of the problem implementations. |
| `selenium_script.py` | Main logic used to verify whether the gathered implementations are accepted by the educational platform. |
| `descriptions_script.py` | Main logic responsible for gathering exercise descriptions from the platform. |

---

## Workflow

### `main.py`

The workflow performed in this file includes:

- Test case generation  
- Precondition and postcondition generation  
- Formal proof generation  

Before execution, make sure to define the challenge codes in the following variables:

- `exercises_to_generate_solutions`
- `exercises_to_generate_conditions`
- `exercises_to_generate_proofs`

### `validator.py`

Before execution, make sure to define the challenge codes to be validated in the following variable:

- `validation_list`

### `selenium_script.py` & `descriptions_script.py`

Before running these files, ensure that all required information is properly configured in your `.env` file.

After configuring the required environment variables:

- In `selenium_script.py`, include the exercises you want to validate in the `main()` function.
- In `descriptions_script.py`, include the exercise codes you want to extract in the `exercise_codes` variable.

#### Attention

For `descriptions_script.py`, the extracted descriptions should **always be manually verified**.

Current limitations of the extraction process include:

- Images are **not extracted**, meaning a manual description of the image may still be required.
- If a section contains **multiple paragraphs**, **only the first paragraph** will be extracted.

##### Example

**Description:**

This is only an example. This paragraph would still be copied without issues.

This line would **not** be copied with the current implementation.

---

## Running the Files

With the current implementation, there is no need to provide additional arguments.

To execute any file, simply run:

```bash
python <file_name>.py
```
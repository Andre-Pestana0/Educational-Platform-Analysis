from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import time
import os
from dotenv import load_dotenv
import os

load_dotenv()

BASE_DIR = "."  # or the path where the folders live
# FOLDERS = ["Iniciante"]
# FOLDERS = ["Strings"]
FOLDERS = ["Ad-HOC"]
# FOLDERS = ["Iniciante", "Strings", "Ad-HOC", "Mathematics"]
# FOLDERS = ["Iniciante", "Strings", "Ad-HOC", "Mathematics"]
# FOLDERS = ["Iniciante", "Strings", "Ad-HOC", "Mathematics"]

def collect_exercises():
    exercises = []

    for folder in FOLDERS:
        folder_path = os.path.join(BASE_DIR, folder)

        if not os.path.isdir(folder_path):
            continue

        for file in os.listdir(folder_path):
            if file.endswith(".py"):
                exercise_number = file.replace(".py", "")
                file_path = os.path.join(folder_path, file)

                exercises.append({
                    "folder": folder,
                    "exercise": exercise_number,
                    "path": file_path
                })

    return exercises

def beecrowd_example(driver, exercise_number, solution_code):
    driver.get(f"https://judge.beecrowd.com/en/problems/view/{exercise_number}")
    
    time.sleep(2)

    while True:
        try:
            text_input = driver.find_element(By.CLASS_NAME, "ace_text-input")
            time.sleep(2)
            # Clear editor
            text_input.send_keys(Keys.CONTROL, "a")
            text_input.send_keys(Keys.DELETE)

            # Paste solution
            # text_input.send_keys(solution_code)
            driver.execute_script("""
            var editor = ace.edit(document.querySelector('.ace_editor'));
            editor.setValue(arguments[0]);
            editor.clearSelection();
            """, solution_code)

            time.sleep(25)
    
            submit_button = driver.find_element(
                By.CSS_SELECTOR,
                ".send-green.g-recaptcha"
            )
            driver.execute_script("arguments[0].click();", submit_button)
            time.sleep(3)
            break
        except Exception as e:
            print(f"Something went wrong for exercise {exercise_number}")
            # print(f"Something went wrong for exercise {exercise_number}, this is the error: {e}")
            time.sleep(2)

    time.sleep(10)
    
    #Checks continously for result answer
    while True:
        try:
            result_answer = driver.find_element(By.CSS_SELECTOR, ".answer")
            if result_answer.text in ["Accepted", "Runtime error", "Wrong answer", "Wrong answer (100%)"]:
                print(f"Result for exercise {exercise_number}: {result_answer.text}")
                break
            else:
                time.sleep(2)
        except Exception as e:
            print(f"Error reading result for exercise {exercise_number}")
            # print(f"Error reading result for exercise {exercise_number}: {e}")
            time.sleep(2)

    return result_answer.text


def main():
    exercises = collect_exercises()
    wrong_exercises = []
    timer = 0

    driver = webdriver.Firefox()

    # Login once
    driver.get(os.getenv("beecrowd_url"))
    time.sleep(3)

    driver.find_element(By.NAME, "email").send_keys(os.getenv("beecrowd_username"))
    password = driver.find_element(By.NAME, "password")
    password.send_keys(os.getenv("beecrowd_password"))
    password.send_keys(Keys.ENTER)

    time.sleep(5)

    for ex in exercises:
        with open(ex["path"], "r", encoding="utf-8") as f:
            code = f.read()
        if int(ex["exercise"]) > 0 and int(ex["exercise"]) < 4000:
            try:
                result = beecrowd_example(
                    driver,
                    ex["exercise"],
                    code
                )

                if result and result in ["Accepted", "Runtime error", "Wrong answer"]:
                    wrong_exercises.append(ex)
                else: 
                    print(f"Exercise {ex['exercise']} passed!")
                
            except Exception as e:
                print(f"Error processing exercise {ex['exercise']}")
                # print(f"Error processing exercise {ex['exercise']}: {e}")
                continue

    driver.quit()
    print("\nWrong exercises:")
    for w in wrong_exercises:
        print(f"{w['folder']} / {w['exercise']}")
    

if __name__ == "__main__":
    main()
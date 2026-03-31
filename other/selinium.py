from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.keys import Keys
import time
import os
from dotenv import load_dotenv

load_dotenv()

def accept_cookies(driver):
    try:
        wait = WebDriverWait(driver, 10)

        # Switch to cookie iframe (if it exists)
        iframe = wait.until(
            EC.presence_of_element_located((By.XPATH, "//iframe"))
        )
        driver.switch_to.frame(iframe)

        # Try clicking the accept button
        accept_button = wait.until(
            EC.element_to_be_clickable((
                By.XPATH,
                "//button[contains(., 'Continue with Recommended Cookies') or contains(., 'Accept All') or contains(., 'I Agree')]"
            ))
        )
        accept_button.click()

        print("Cookies accepted 🍪")

        # Switch back to main page
        driver.switch_to.default_content()

    except Exception as e:
        print("No cookie banner found (or already accepted)")
        driver.switch_to.default_content()


def selinum_example():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/web-form.html")
    title = driver.title
    print(f"Page title is: {title}")
    driver.implicitly_wait(0.5)
    text_box = driver.find_element(by=By.NAME, value="my-text")
    submit_button = driver.find_element(by=By.CSS_SELECTOR, value="button")
    text_box.send_keys("Selenium")
    submit_button.click()
    message = driver.find_element(by=By.ID, value="message")
    text = message.text
    print(f"Message is: {text}")
    driver.quit()
    
def my_example():
    #Initate the driver on Firefox
    driver = webdriver.Firefox()
    
    #Go to Youtube
    driver.get("https://www.youtube.com/")
    accept_cookies(driver)
    time.sleep(3) 
    driver.implicitly_wait(5)
    
    #get the search bar element
    youtube_search_bar = driver.find_element(by= By.NAME, value="search_query")
    
    #write a query on the bar and press enter
    youtube_search_bar.send_keys("Selinium tutorial in Python")
    youtube_search_bar.send_keys(Keys.ENTER)
    
    time.sleep(15)      
    driver.quit()
    
    
def beecrowd_example():
    
    #Go to website
    driver = webdriver.Firefox()
    driver.get(os.getenv("beecrowd_url"))
    
    time.sleep(3)

    #Get input elements
    username_input = driver.find_element(by=By.NAME, value="email")
    password_input = driver.find_element(by=By.NAME, value="password")
    
    #Write username & password
    username_input.send_keys(os.getenv("beecrowd_username"))
    password_input.send_keys(os.getenv("beecrowd_password"))
    password_input.send_keys(Keys.ENTER)
    
    time.sleep(5)
    
    #Exercises loop
    driver.get(os.getenv("base_link"))
    
    time.sleep(5)
    text_input = driver.find_element(by=By.CLASS_NAME, value="ace_text-input")

    #Delete existing code
    text_input.send_keys(Keys.CONTROL, "a")
    text_input.send_keys(Keys.DELETE)
    
    #Write solution
    text_input.send_keys("print('Hello, World!')")
    time.sleep(2)
    
    submit_button = driver.find_element(
        By.CSS_SELECTOR,
        ".send-green.g-recaptcha"
    )
    driver.execute_script("arguments[0].click();", submit_button)
    
    #Checks if the result is incorrect so I can take it for inspection
    time.sleep(3)
    result_answer = driver.find_element(
        By.CSS_SELECTOR,
        ".answer.a-6"
    )
    
    if "Wrong answer" in result_answer.text:
        print("The answer is incorrect")

    
    time.sleep(100) 
    driver.quit()

    
if __name__ == "__main__":
    # my_example()
    beecrowd_example()
import os
import requests
import cloudscraper
from bs4 import BeautifulSoup

# List of exercise codes
exercise_codes = [
    1024, 1120, 1168, 1222, 1234, 1235, 1237, 1238, 1239, 1241,
    1248, 1253, 1254, 1255, 1257, 1262, 1263, 1272, 1273, 1276,
    1277, 1278, 1287, 1305, 1332, 1367, 1448, 1516, 1551, 1556,
    1581, 1586, 1607, 1629, 1664, 1803, 1868, 1873, 2025, 2049,
    2062, 2108, 2137, 2150, 2157, 2253, 2292, 2557, 2587, 2588,
    2591, 2690, 2691, 2692, 2697, 2714, 2722, 2815, 2866, 2880,
    2954, 3141
]
# Folder to store each exercise file
output_folder = "new_exercises_string_descriptions"
os.makedirs(output_folder, exist_ok=True)

for code in exercise_codes:
    url = f"https://resources.beecrowd.com/repository/UOJ_{code}_en.html"
    print(f"Processing exercise {code}...")

    try:
        # Fetch HTML
        scraper = cloudscraper.create_scraper()
        response = scraper.get(url, timeout=15)
        response.raise_for_status()
        html_content = response.text

        # Parse HTML
        soup = BeautifulSoup(html_content, "html.parser")

        # Extract description
        description_tag = soup.select_one(".description p")
        description = description_tag.get_text(strip=True) if description_tag else "N/A"

        # Extract input
        input_tag = soup.select_one(".input p")
        input_text = input_tag.get_text(strip=True) if input_tag else "N/A"

        # Extract output
        output_tag = soup.select_one(".output p")
        output_text = output_tag.get_text(strip=True) if output_tag else "N/A"

        # Extract examples
        examples = []
        tables = soup.find_all("table")
        for table in tables:
            tbody = table.find("tbody")
            if not tbody:
                continue
            for row in tbody.find_all("tr"):
                cells = row.find_all("td")
                if len(cells) >= 2:
                    input_example = cells[0].get_text(separator="\n", strip=True)
                    output_example = cells[1].get_text(separator="\n", strip=True)
                    examples.append((input_example, output_example))

        # Build formatted content
        lines = []
        lines.append(f"Description: {description}\n")
        lines.append(f"Input: {input_text}\n")
        lines.append(f"Output: {output_text}\n")
        lines.append("Examples:")
        for i, (inp, outp) in enumerate(examples, start=1):
            lines.append(f"- Example {i}:")
            lines.append(f"  Input:\n{inp}")
            lines.append(f"  Output:\n{outp}\n")
        lines.append("--\n")  # separator

        # Write to individual file
        file_path = os.path.join(output_folder, f"{code}.txt")
        with open(file_path, "w", encoding="utf-8") as f:
            f.write("\n".join(lines))

        print(f"Saved exercise {code} to {file_path} ✅")

    except requests.RequestException as e:
        print(f"Failed to fetch exercise {code}: {e}")

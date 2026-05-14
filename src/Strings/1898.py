import re

def clean(s):
    # Keep only digits and at most one dot
    s = re.sub(r'[^\d.]', '', s)
    return s

def truncate_decimals(s):
    if '.' in s:
        index = s.index('.')
        after = s[index:]  # includes the dot
        if len(after) > 3:
            s = s[:index + 3]
    return s

line1 = clean(input())
line2 = clean(input())

# Extract CPF (first 11 digits) from value1
if len(line1) <= 11:
    cpf = line1
    valor1 = ""
else:
    cpf = line1[:11]
    valor1 = line1[11:]

valor1 = truncate_decimals(valor1)
valor2 = truncate_decimals(line2)

v1 = float(valor1) if valor1 else 0.0
v2 = float(valor2)

total = v1 + v2

print(f"cpf {cpf}")
print(f"{total:.2f}")
N = int(input())
words = input().split()

result = []
for word in words:
    if len(word) == 3:
        if word.startswith("OB") or (word.startswith("UR") and not word.endswith("I")):
            word = word[:2] + "I"
    result.append(word)

print(" ".join(result))
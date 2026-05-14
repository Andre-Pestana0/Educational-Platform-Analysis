import sys

for line in sys.stdin:
    expression = line.rstrip('\n')
    open_count = 0
    wrong = False

    for char in expression:
        if char == ')':
            if open_count == 0:
                wrong = True
                break
            open_count -= 1
        elif char == '(':
            open_count += 1

    if wrong or open_count > 0:
        print("incorrect")
    else:
        print("correct")
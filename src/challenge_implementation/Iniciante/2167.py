medida = int(input())
rpm = list(map(int, input().split()))

cont = 0

for i in range(medida - 1):
    if rpm[i + 1] < rpm[i]:
        cont = i + 2  # índice em base 1
        break

print(cont)
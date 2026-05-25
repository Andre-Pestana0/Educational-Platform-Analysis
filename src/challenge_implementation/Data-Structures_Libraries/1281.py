import sys

data = sys.stdin.read().split()
idx = 0

def next_token():
    global idx
    token = data[idx]
    idx += 1
    return token

N = int(next_token())

for _ in range(N):
    total = 0.0
    M = int(next_token())

    products = {}
    for _ in range(M):
        name = next_token()
        price = float(next_token())
        products[name.lower()] = price

    P = int(next_token())
    for _ in range(P):
        name = next_token()
        quantity = int(next_token())
        if name.lower() in products:
            total += products[name.lower()] * quantity

    print(f"R$ {total:.2f}")
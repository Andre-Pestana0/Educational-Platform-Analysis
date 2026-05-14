import sys

data = sys.stdin.read().split()
idx = 0

def next_token():
    global idx
    token = data[idx]
    idx += 1
    return token

while idx < len(data):
    N = int(next_token())
    G = int(next_token())
    points = 0
    losses = []

    for _ in range(N):
        S = int(next_token())
        R = int(next_token())

        if S > R:
            points += 3
        elif S == R:
            if G > 0:
                G -= 1
                points += 3
            else:
                points += 1
        else:
            losses.append(R - S + 1)

    losses.sort()
    for goals_needed in losses:
        if G >= goals_needed:
            G -= goals_needed
            points += 3
            if G == 0:
                break
        elif G - goals_needed == -1:
            points += 1
            break
        else:
            break

    print(points)
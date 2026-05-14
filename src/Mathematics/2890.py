import sys

MOD = 1000007

def pow2(a):
    return (a * a) % MOD

def pow4(a):
    return (a * a * a * a) % MOD

def extended_gcd(a, b):
    if b == 0:
        return a, 1, 0
    d, xx, yy = extended_gcd(b, a % b)
    x = yy
    y = xx - (a // b) * yy
    return d, x, y

def inverso(a):
    _, x, _ = extended_gcd(a, MOD)
    return x % MOD

inv12 = inverso(12)

for line in sys.stdin:
    n = int(line.strip())
    if n == 0:
        break
    result = ((11 * pow2(n) + pow4(n)) % MOD) * inv12 % MOD
    print(result)
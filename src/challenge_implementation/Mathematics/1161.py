import sys
from math import factorial

for line in sys.stdin:
    line = line.strip()
    if not line:
        continue
    M, N = map(int, line.split())
    print(factorial(M) + factorial(N))
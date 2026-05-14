import sys
from functools import lru_cache

def count_ones(n):
    @lru_cache(maxsize=None)
    def go(i, acc, top):
        if i == -1:
            return acc
        bit = (n >> i) & 1
        # path: place a 0 at position i
        new_top = (not bit) and top
        ans = go(i - 1, acc, new_top)
        # path: place a 1 at position i (only if allowed)
        if not top or bit:
            ans += go(i - 1, acc + 1, bool(bit) and top)
        return ans

    go.cache_clear()
    return go(55, 0, True)

for line in sys.stdin:
    line = line.strip()
    if not line:
        continue
    a, b = map(int, line.split())
    ans = count_ones(b) - count_ones(a - 1)
    print(ans)
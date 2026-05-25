class SuffixAutomaton:
    def __init__(self):
        self.next = []
        self.link = []
        self.length = []
        self.next.append({})
        self.link.append(-1)
        self.length.append(0)
        self.last = 0

    def extend(self, c):
        cur = len(self.next)
        self.next.append({})
        self.length.append(self.length[self.last] + 1)
        self.link.append(0)

        p = self.last
        while p != -1 and c not in self.next[p]:
            self.next[p][c] = cur
            p = self.link[p]

        if p == -1:
            self.link[cur] = 0
        else:
            q = self.next[p][c]
            if self.length[p] + 1 == self.length[q]:
                self.link[cur] = q
            else:
                clone = len(self.next)
                self.next.append(self.next[q].copy())
                self.length.append(self.length[p] + 1)
                self.link.append(self.link[q])

                while p != -1 and self.next[p].get(c) == q:
                    self.next[p][c] = clone
                    p = self.link[p]

                self.link[q] = self.link[cur] = clone

        self.last = cur


while True:
    try:
        s1 = input()
        s2 = input()

        if len(s1) > len(s2):
            s1, s2 = s2, s1

        sam = SuffixAutomaton()
        for ch in s1:
            sam.extend(ch)

        v = 0
        l = 0
        ans = 0

        for ch in s2:
            if ch in sam.next[v]:
                v = sam.next[v][ch]
                l += 1
            else:
                while v != -1 and ch not in sam.next[v]:
                    v = sam.link[v]
                if v == -1:
                    v = 0
                    l = 0
                    continue
                l = sam.length[v] + 1
                v = sam.next[v][ch]

            if l > ans:
                ans = l

        print(ans)

    except EOFError:
        break
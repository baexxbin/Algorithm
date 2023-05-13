import sys
from collections import deque

input = sys.stdin.readline


def hv_bfs(node):
    que = deque()
    que.append(node)
    visited = []
    cnt = 0

    while que:
        cur = que.popleft()
        for nxt in heavy_marble[cur]:
            if nxt not in visited:
                que.append(nxt)
                visited.append(nxt)
                cnt += 1
    return cnt


def lt_bfs(node):
    que = deque()
    que.append(node)
    visited = []
    cnt = 0

    while que:
        cur = que.popleft()
        for nxt in light_marble[cur]:
            if nxt not in visited:
                que.append(nxt)
                visited.append(nxt)
                cnt += 1
    return cnt


if __name__ == '__main__':
    n, m = map(int, input().split())

    heavy_marble = [set() for i in range(n + 1)]
    light_marble = [set() for i in range(n + 1)]
    for _ in range(1, m + 1):
        h, l = map(int, input().split())
        heavy_marble[l].add(h)
        light_marble[h].add(l)

    mid = (n + 1) // 2
    ans = 0

    for i in range(1, n + 1):
        if hv_bfs(i) >= mid:
            ans += 1
        if lt_bfs(i) >= mid:
            ans += 1
    print(ans)

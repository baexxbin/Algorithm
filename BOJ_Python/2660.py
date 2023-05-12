import sys
from collections import deque

input = sys.stdin.readline


def bfs(x):
    visited = [0] * (n + 1)
    que = deque()
    que.append(x)
    visited[x] = 1

    while que:
        cur = que.popleft()
        for nxt in adj[cur]:
            if visited[nxt] == 0:
                visited[nxt] = visited[cur]+1
                que.append(nxt)

    return max(visited)


if __name__ == '__main__':
    n = int(input())
    adj = [[] for _ in range(n + 1)]

    while True:
        u, v = map(int, input().split())
        if u == -1 and v == -1:
            break
        adj[u].append(v)
        adj[v].append(u)

    ans = [float('inf')]*(n+1)
    for i in range(1, n + 1):
        ans[i] = bfs(i)

    mn = min(ans)
    print(mn-1, ans.count(mn))
    print(' '.join(str(i) for i in range(1, n+1) if ans[i]==mn))
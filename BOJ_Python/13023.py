import sys
from collections import deque

input = sys.stdin.readline


# dfs 깊이가 5인 경우 찾기 (ABCDE)

def dfs(node, depth):
    global ans
    if depth == 5:
        ans = True
        return

    visited[node] = True
    for nxt in adj[node]:
        if not visited[nxt]:
            dfs(nxt, depth+1)
    visited[node] = False


if __name__ == '__main__':
    N, M = map(int, input().split())
    adj = [[] for _ in range(N)]
    visited = [False]*N
    ans = False

    for _ in range(M):
        u, v = map(int, input().split())
        adj[u].append(v)
        adj[v].append(u)

    for i in range(N):
        dfs(i, 1)
        if ans:
            break
    print(1) if ans else print(0)

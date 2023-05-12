import sys
from collections import deque

input = sys.stdin.readline


def bfs(node, group):
    que = deque()
    que.append(node)
    visited[node] = group

    while que:
        cur = que.popleft()
        for nxt in adj[cur]:
            if visited[nxt] == -1:
                que.append(nxt)
                visited[nxt] = (visited[cur] ^ 1)
                continue
            if visited[nxt] == visited[cur]:
                return True
    return False


# 이분 그래프 : 모든 인접한 정점이 서로 다른 색
if __name__ == '__main__':
    k = int(input())

    for _ in range(k):
        v, e = map(int, input().split())
        adj = [[] for _ in range(v + 1)]
        for _ in range(e):
            u, w = map(int, input().split())
            adj[u].append(w)
            adj[w].append(u)

        # -1 : 방문안함, 0: 0그룹, 1: 1그룹
        visited = [-1] * (v + 1)
        for i in range(1, v + 1):
            if visited[i] == -1:
                if bfs(i, 0):
                    print("NO")
                    break
        else:
            print("YES")

from collections import deque

from collections import deque


def bfs(x, adj, n):
    visited = [-1] * (n + 1)

    que = deque()
    que.append(x)
    visited[x] = 0

    while que:
        cur = que.popleft()

        for nx in adj[cur]:
            if visited[nx] == -1:
                visited[nx] = visited[cur] + 1
                que.append(nx)
    return visited


def solution(n, roads, sources, destination):
    adj = [[] for _ in range(n + 1)]

    for u, v in roads:
        adj[u].append(v)
        adj[v].append(u)

    res = []
    dist = bfs(destination, adj, n)
    for x in sources:
        res.append(dist[x])

    return res


if __name__ == '__main__':
    n = 3
    roads = [[1, 2], [2, 3]]
    sources = [2, 3]
    destination = 1
    print(solution(n, roads, sources, destination))
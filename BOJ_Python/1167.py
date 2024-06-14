import sys
from collections import deque

input = sys.stdin.readline

# 트리의 정점 구하기
# 1. 임의의 정점x로부터 가장 먼 정점y 구하기
# 2. 정점y로부터 가장 먼 정점z 구하기
# 3. y점점과 z정점을 잇는 경로가 트리의 지름

def bfs(start):
    que = deque()
    que.append((start,0))
    visited = [False]*(N+1)
    visited[start] = True
    res = [0,0]

    while que:
        node, dist = que.popleft()
        for nxt_n, nxt_d in adj[node]:
            if visited[nxt_n]:
                continue
            total_d = dist+nxt_d
            que.append((nxt_n, total_d))
            visited[nxt_n] = True
            if res[1] < total_d:
                res[0], res[1] = nxt_n, total_d
    return res


if __name__ == '__main__':
    N = int(input())
    adj = [[] for _ in range(N+1)]

    for i in range(N):
        tmp = list(map(int, input().split()))
        node = tmp[0]
        idx = 1
        while tmp[idx] !=-1:
            n, v = tmp[idx], tmp[idx+1]
            adj[node].append((tmp[idx], tmp[idx+1]))
            idx+=2

    y, _ = bfs(1)
    print(bfs(y)[1])

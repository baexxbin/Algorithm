import heapq
import sys

input = sys.stdin.readline

if __name__ == '__main__':
    N, M = map(int, input().split())
    indgree = [0] * (N + 1)
    adj = [[] for _ in range(N + 1)]

    for i in range(M):
        u, v = map(int, input().split())
        adj[u].append(v)
        indgree[v] += 1

    heap = []
    for i in range(1, N + 1):
        if indgree[i] == 0:
            heapq.heappush(heap, i)

    ans = []
    while heap:
        cur = heapq.heappop(heap)
        ans.append(cur)
        for nxt in adj[cur]:
            indgree[nxt] -= 1
            if indgree[nxt] == 0:
                heapq.heappush(heap, nxt)

    print(' '.join(map(str, ans)))
import heapq
# 한 정점으로부터 가장 멀리 떨어져있는 정점 구하기 >> 크루스칼

def solution(n, edge):
    INF = float('inf')
    dist = [INF]*(n+1)
    heap = []

    adj = [[] for _ in range(n+1)]
    # 간선 정리
    for (u, v) in edge:
        adj[u].append(v)
        adj[v].append(u)

    heapq.heappush(heap, (0, 1))    # dist, idx
    dist[1] = 0

    while heap:
        d, cur = heapq.heappop(heap)
        for nxt in adj[cur]:
            if d+1 < dist[nxt]:
                dist[nxt] = d+1
                heapq.heappush(heap, (dist[nxt], nxt))

    mx = max(dist[2:])
    ans = 0
    for i in range(2, n+1):
        if dist[i]==mx:
            ans+=1
    return ans



if __name__ == '__main__':
    n = 6
    vertex = [[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]
    print(solution(n, vertex))
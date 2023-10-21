from heapq import heappush, heappop


def dijkstra(start):
    global adj, distance
    heap = []
    distance[start] = 0
    heappush(heap, (distance[start], start))

    while heap:
        cur_cost, cur_node = heappop(heap)

        if cur_cost != distance[cur_node]:
            continue
        for nxt_cost, nxt_node in adj[cur_node]:
            if distance[nxt_node] <= cur_cost+nxt_cost:
                continue
            distance[nxt_node] = cur_cost+nxt_cost
            heappush(heap, (distance[nxt_node], nxt_node))

def solution(N, road, K):
    global adj, distance
    adj = [[] for _ in range(N+1)]
    for u,v,c in road:
        adj[u].append((c,v))
        adj[v].append((c,u))

    distance = [float('INF')]*(N+1)
    dijkstra(1)
    print(distance)
    ans = 0
    for cost in distance:
        if cost <= K:
            ans+=1
    print(ans)


N = 5
road = [[1,2,1],[2,3,3],[5,2,2],[1,4,2],[5,3,1],[5,4,2]]
K = 3
solution(N, road, K)
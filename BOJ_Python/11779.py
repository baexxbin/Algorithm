import heapq
import sys

input = sys.stdin.readline

def dijkstra(start):
    heap = []
    distance[start] = 0
    heapq.heappush(heap, (distance[start], start))

    while heap:
        cur_cost, cur_node = heapq.heappop(heap)
        if cur_cost != distance[cur_node]:
            continue
        for nxt_cost, nxt_node in adj[cur_node]:
            if distance[nxt_node] > cur_cost+nxt_cost:
                distance[nxt_node] = cur_cost+nxt_cost
                pre_node[nxt_node] = cur_node
                heapq.heappush(heap, (distance[nxt_node], nxt_node))


if __name__ == '__main__':
    n = int(input())
    m = int(input())
    adj = [[] for _ in range(n+1)]
    pre_node = [0]*(n+1)
    for _ in range(m):
        s, e, v = map(int, input().split())
        adj[s].append((v, e))
    start, end = map(int, input().split())

    distance = [float('inf') for _ in range(n+1)]
    dijkstra(start)

    path = [end]
    now = end
    while now != start:
        now = pre_node[now]
        path.append(now)
    path.reverse()

    print(distance[end])
    print(len(path))
    print(' '.join(map(str, path)))
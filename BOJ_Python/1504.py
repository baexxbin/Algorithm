import heapq
import sys

input = sys.stdin.readline
INF = float('inf')


def dijkstra(start):
    distance = [INF] * (N + 1)
    heap = []
    heapq.heappush(heap, (0, start))
    distance[start] = 0

    while heap:
        dist, idx = heapq.heappop(heap)  # 가장 비용 작은애 꺼내기

        if dist > distance[idx]:  # 이전에 넣은 값 등 기존 비용보다 비용이 클 경우 패쓰
            continue

        for nxt, nd in adj[idx]:  # 현재 정점과 연결된 이웃 정점 비용 갱신
            cost = dist + nd
            if cost < distance[nxt]:  # 새로운 비용 cost가 기존의 비용 distance[nxt]보다 작으면 갱신
                distance[nxt] = cost
                heapq.heappush(heap, [cost, nxt])

    return distance


if __name__ == '__main__':
    N, E = map(int, input().split())
    adj = [[] for _ in range(N + 1)]

    for _ in range(E):
        a, b, c = map(int, input().split())
        adj[a].append([b, c])
        adj[b].append([a, c])
    v1, v2 = map(int, input().split())

    start = dijkstra(1)
    position1 = dijkstra(v1)
    position2 = dijkstra(v2)

    # start-v1-v2-end와 start-v2-v1-end 중 최단 거리 구하기
    ans = min(start[v1] + position1[v2] + position2[N], start[v2] + position2[v1] + position1[N])
    print(-1) if ans >= INF else print(ans)

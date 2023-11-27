import sys

input = sys.stdin.readline
INF = float('INF')
def bellman_ford(start):
    dist[start] = 0

    for i in range(N):
        for j in range(M):
            cur, nxt, cost = adj[j][0], adj[j][1], adj[j][2]
            if dist[cur] != INF and dist[nxt] > dist[cur]+cost:
                dist[nxt] = dist[cur]+cost
                if i==N-1:
                    return True
    return False


if __name__ == '__main__':
    N, M = map(int, input().split())

    adj = []
    dist = [INF]*(N+1)
    for _ in range(M):
        a, b, c = map(int, input().split())
        adj.append((a,b,c))

    if bellman_ford(1): # 음수간선 존재
        print(-1)
    else:
        for i in range(2, N+1):
            print(-1) if dist[i] == INF else print(dist[i])


# 최송비용 구하기 >> 다익스트라(당장의 최선: 그리디), 벨만포드(모든 경우의 수 탐색), 플로이드 와샬(n^3)
# 음수시간 존재 >> 벨만포드(모든 경우의 수 탐색)

# 벨만포드
# 매 라운드 마다 모든 '간선'확인 (n-1)개 확인
    # 이때 n까지 확인했을때도 비용이 줄어든다면 음수간선이 존재
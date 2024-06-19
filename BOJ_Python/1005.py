import sys
from collections import deque

input = sys.stdin.readline

# 특정 건물을 짓기 위해 다른 건물 완성되어야함 >> 위상정렬

if __name__ == '__main__':
    T = int(input())

    for _ in range(T):
        N, K = map(int, input().split())
        cost = [0] + list(map(int, input().split()))
        adj = [[] for _ in range(N+1)]
        indegree = [0 for _ in range(N+1)]
        for _ in range(K):
            u, v = map(int, input().split())
            adj[u].append(v)
            indegree[v] += 1
        target = int(input())

        dp = [0]*(N+1)
        indegree[0] = -1

        que = deque()
        for i in range(1, N+1):
            if indegree[i]==0:
                que.append(i)
                dp[i] = cost[i]

        while que:
            node = que.popleft()
            for nxt in adj[node]:
                indegree[nxt] -= 1
                dp[nxt] = max(dp[nxt], dp[node]+cost[nxt])  # 동시에 짓는 건물은 큰 값으로 진행
                if indegree[nxt]==0:
                    que.append(nxt)

            if indegree[target]==0:     # 위상에선 방문여부 indegree=0으로 알 수 있음
                print(dp[target])
                break

# 시간 복잡도
# N=1000, K=100000 >> O(N*K)이상은 피해야함
# 위상정렬 시간복잡도 O(N+K), 비순환 유향그래프에서 사용




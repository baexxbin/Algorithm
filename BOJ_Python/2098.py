import sys

input = sys.stdin.readline


def dfs(cur, visited):
    if visited == (1 << N) - 1:
        return W[cur][0] if W[cur][0] else INF

    # if dp[x][visited] != INF:       # 메모이제이션 활용
    #     return dp[x][visited]

    if (cur, visited) in dp:
        return dp[(cur, visited)]

    mn = INF
    for nxt in range(1, N):
        if W[cur][nxt] == 0 or visited & (1 << nxt):
            continue

        cost = dfs(nxt, visited | (1 << nxt)) + W[cur][nxt]
        mn = min(mn, cost)
        # dp[cur][visited] = min(dp[cur][visited], dfs(nxt, visited | (1<<nxt))+W[cur][nxt])

    dp[(cur, visited)] = mn
    return mn
    # return dp[cur][visited]


if __name__ == '__main__':
    N = int(input())
    W = [list(map(int, input().split())) for _ in range(N)]

    INF = float('inf')
    # dp = [[INF] * (1 << N) for _ in range(N)]
    dp = {}
    print(dfs(0, 1))

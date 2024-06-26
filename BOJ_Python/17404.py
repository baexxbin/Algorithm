import sys

input = sys.stdin.readline


if __name__ == '__main__':
    N = int(input())
    cost = [list(map(int, input().split())) for _ in range(N)]
    INF = sys.maxsize

    ans = INF
    for i in range(3):
        dp = [[INF] * 3 for _ in range(N)]

        # 초기값 설정 (i색을 제일 작은 값으로 고정)
        dp[0][i] = cost[0][i]

        for j in range(1, N):
            dp[j][0] = min(dp[j - 1][1], dp[j - 1][2]) + cost[j][0]
            dp[j][1] = min(dp[j - 1][0], dp[j - 1][2]) + cost[j][1]
            dp[j][2] = min(dp[j - 1][0], dp[j - 1][1]) + cost[j][2]

        dp[N-1][i] = INF    # 마지막 집이 첫번째랑 같이 않도록 설정
        ans = min(ans, min(dp[-1]))
    print(ans)
import sys

input = sys.stdin.readline

# dp[K][N] : K개의 수로 N을 만들 수 있는 경우의 수
#            K-1까지의 수로 N이 되는 경우를, 모두 더한 수(각각에 K만 더해주면 K개로 N 만들어짐)
# dp[K][N] = dp[K-1][N] + dp[K][N-1]
# dp[K][N-1] : K까지 수로 N만든 경우, (K 사용 안함)
# dp[K-1][N] : K-1까지 수로 N으로 나타낸 경우, (K 사용하고 나머지로 합만들기)

if __name__ == '__main__':
    N, K = map(int, input().split())

    dp = [[0]*(N+1) for _ in range(K+1)]
    dp[0][0] = 1

    for i in range(1, K+1):
        for j in range(N+1):
            dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000000
    print(dp[K][N])

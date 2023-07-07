import sys

input = sys.stdin.readline

if __name__ == '__main__':
    N = int(input())

    # 1. 테이블 정의 : dp[자리수(n)][마지막 위치 숫자]
    dp = [[0]*10 for _ in range(N+1)]

    # 3. 초기값
    dp[1][0] = 0
    for i in range(1, 10):
        dp[1][i] = 1

    # 2. 점화식
    for i in range(2, N+1):
        dp[i][0] = dp[i-1][1] % 1000000000                              #   0) dp[n][i] = dp[n-1][i+1]
        for j in range(1,9):                                            #   1~8) dp[n][i] = dp[n-1][i-1] + dp[n-1][i+1]
            dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000
        dp[i][9] = dp[i-1][8] % 1000000000                              #   9) dp[n][i] = dp[n-1][i-1]

    print(sum(dp[N]) % 1000000000)

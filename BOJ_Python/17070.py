import sys

input = sys.stdin.readline


if __name__ == '__main__':
    n = int(input())
    board = [list(map(int, input().split())) for _ in range(n)]

    # 가로,세로,대각선 각 부분이 dp값을 갖도록
    dp = [[[0]*3 for _ in range(n)] for _ in range(n)]
    dp[0][1][0] = 1

    # 0행 초기화
    for j in range(2,n):
        if board[0][j] == 0:
            dp[0][j][0] = dp[0][j-1][0]

    for i in range(1,n):
        for j in range(1,n):
            if board[i][j] == 0:
                dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2]
                dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2]
            if board[i][j] == 0 and board[i-1][j] == board[i][j-1] == 0:
                dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2]
    print(sum([dp[n-1][n-1][i] for i in range(3)]))

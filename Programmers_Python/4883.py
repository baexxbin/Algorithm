import sys

input = sys.stdin.readline

if __name__ == '__main__':
    k = 0
    while True:
        k += 1
        N = int(input())
        if N == 0: break
        board = [list(map(int, input().split())) for _ in range(N)]

        dp = [[0] * 3 for _ in range(N)]

        # 초기값 설정
        dp[0][0] = 10001
        dp[0][1] = board[0][1]
        dp[0][2] = board[0][1] + board[0][2]

        for i in range(1, N):
            dp[i][0] = board[i][0] + min(dp[i - 1][0], dp[i - 1][1])  # 어느위치에서 올 수 있는지
            dp[i][1] = board[i][1] + min(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2], dp[i][0])  # 옆에서 오는것도 체크
            dp[i][2] = board[i][2] + min(dp[i - 1][1], dp[i - 1][2], dp[i][1])

        print(f'{k}. {dp[N - 1][1]}')

import sys

input = sys.stdin.readline

# dp
# 1. n번째를 선택했을 때 최적값 *
# 2. n번째까지 고려했을 때 최적값(n번째 선택하지 않은경우까 고려)

# 1. 테이블 정의하기  >> dp[i][n] n열에서 i행 스티커를 마지막으로 골랐을때 최대가치
# 2. 점화식 찾기
# 3. 초기값 정의하기 >> dp[i][0], dp[i][1] 기존 스티커 값으로 초기화

if __name__ == '__main__':
    T = int(input())
    for _ in range(T):
        N = int(input())
        board = [list(map(int, input().split())) for _ in range(2)]
        dp = [[0] * N for _ in range(2)]

        dp[0][0] = board[0][0]
        dp[1][0] = board[1][0]
        if N==1:
            print(max(dp[0][0], dp[1][0]))
            continue

        dp[0][1] = board[1][0]+board[0][1]
        dp[1][1] = board[0][0]+board[1][1]

        if N==2:
            print(max(dp[0][1], dp[1][1]))
            continue

        for i in range(2, N):
            dp[0][i] = max(dp[1][i-2], dp[1][i-1]) + board[0][i]
            dp[1][i] = max(dp[0][i-2], dp[0][i-1]) + board[1][i]

        print(max(dp[0][-1], dp[1][-1]))
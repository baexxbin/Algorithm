import sys

input = sys.stdin.readline

if __name__ == '__main__':
    n = int(input())
    glass = [int(input()) for _ in range(n)]

    # 테이블 정의 dp[i][j]
    # i:현재 와인, j:현재 와인을 몇번째 마시는 중인지 (0: 안마심, 1: 현재랑 그그 전, 2: 현재랑 직전)
    dp = [[0]*3 for _ in range(n)]

    # 초기값
    dp[0][1] = dp[0][2] = glass[0]

    # 점화식
    for i in range(1, n):
        dp[i][0] = max(dp[i-1])
        dp[i][1] = dp[i-1][0] + glass[i]
        dp[i][2] = dp[i-1][1] + glass[i]

    print(max(dp[-1]))







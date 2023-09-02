import sys

input = sys.stdin.readline

if __name__ == '__main__':
    N, M = map(int, input().split())

    matrix = [list(map(int, input().strip())) for _ in range(N)]
    dp = [[0]*(M+1) for _ in range(N+1)]

    # 점화식: dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])+1
    # dp[i][j]: 사각형의 오른쪽 아래 꼭짓점을 가리킬때, 사각형의 한 변의 길이

    ans = 0
    for i in range(1, N+1):             # dp의 크기가 (N+1)*(M+1)임으로 범위도 동일
        for j in range(1, M+1):
            if matrix[i-1][j-1]==1:     # matrix는 N*M임으로 -1해주기
                dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])+1
                ans = max(ans, dp[i][j])

    print(ans**2)
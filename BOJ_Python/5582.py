import sys

input = sys.stdin.readline


if __name__ == '__main__':
    s1 = input().strip()
    s2 = input().strip()

    mx = 0
    dp = [[0]*(len(s2)+1) for _ in range(len(s1)+1)]
    for i in range(1, len(s1)+1):
        for j in range(1, len(s2)+1):
            if s1[i-1]==s2[j-1]:
                dp[i][j] = dp[i-1][j-1]+1
        mx = max(mx, max(dp[i]))

    print(mx)

# 두 문자열의 공통부분을 2차원배열로 나타낼 수 있는것이 핵심
# 두 문자열이 연속적으로 같을 때 대각선 방향으로 1씩 값이 늘어남
    # dp[i][j] = dp[i-1][j-1]+1
    # 첫 행과 열의 dp값을 해결하기 위해 dp[0][0] = dp[-1][-1]+1 (인덱스 오류), 길이 +1+1씩 해줌
# 문자열 마지막에 개행문자있음 꼭 strip()해주기!
import sys

input = sys.stdin.readline

if __name__ == '__main__':
    n = int(input())
    A = list(map(int, input().split()))
    dp = list([0]*21 for _ in range(n-1))

    # 초기 값
    dp[0][A[0]] = 1

    # 점화식 : d[i][j] = j, i번째 수까지의 조합으로 j값을 만들 수 있는 경우의 수
    #        d[i][j] = d[i-1][j-A[[i]] + d[i-1][j+A[i]]
    for i in range(1, n-1):
        for j in range(21):
            if j - A[i] >= 0:
                dp[i][j] += dp[i-1][j - A[i]]
            if j + A[i] <= 20:
                dp[i][j] += dp[i-1][j + A[i]]

    # 원하는 마지막 값 A[-1]은 dp[n-1]의 값으로 이루어짐 (배열 0부터 시작, n-2)
    print(dp[n-2][A[-1]])

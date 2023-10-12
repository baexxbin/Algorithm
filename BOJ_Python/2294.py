import sys

input = sys.stdin.readline

if __name__ == '__main__':
    N, K = map(int, input().split())
    coin = [int(input()) for _ in range(N)]

    dp = [10001 for _ in range(K+1)]
    dp[0] = 0

    for c in coin:
        for i in range(c, K+1):
            dp[i] = min(dp[i], dp[i-c]+1)

    print(-1) if dp[K] == 10001 else print(dp[K])

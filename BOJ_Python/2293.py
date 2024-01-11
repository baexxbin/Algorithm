import sys

input = sys.stdin.readline

if __name__ == '__main__':
    n, k = map(int, input().split())
    coins = []
    for _ in range(n):
        coins.append(int(input()))

    dp = [0]*(k+1)
    dp[0] = 1
    for c in coins:
        for i in range(c, k+1):
            dp[i] += dp[i-c]
    print(dp[k])
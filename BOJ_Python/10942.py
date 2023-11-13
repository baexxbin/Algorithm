import sys

input = sys.stdin.readline

# 13:22 - 14:05

if __name__ == '__main__':
    N = int(input())
    nums = list(map(int, input().split()))
    M = int(input())

    dp = [[0]*N for _ in range(N)]

    # 자기 자신은 회문
    for i in range(N):
        dp[i][i] = 1

    # 바로 연속된 두수는 회문
    for i in range(N-1):
        if nums[i]==nums[i+1]:
            dp[i][i+1] = 1

    # i,j가 같을때 그 사이(dp[i+1][j-1])가 회문이면 회문
    for ln in range(2, N):
        for i in range(N-ln):
            j = i+ln
            if nums[i]==nums[j] and dp[i+1][j-1]==1:
                dp[i][j] = 1

    for _ in range(M):
        i, j = map(int, input().split())
        print(dp[i-1][j-1])

# 모든 부분 문자에대해 O(N^2) 회문검사O(N)하면 총 O(N3)으로 시간초과
# dp로 O(N^2)
    # dp는 여러개의 하위문제를 문제를 먼저 풀고 결과를 쌓아올려 문제를 해결함
    # >> 회문 조건을 만족하는 작은 하위조건들 우선 생각해보기
    # 마지막i,j조건은 dp[i-1][j+1]가 완성되어있어야 성립됨
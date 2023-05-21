import sys

input = sys.stdin.readline

# n(모두 안겹치고 연결) - m(연결할 수 있는 가장 많은 전깃줄 개수) : 최소로 빼야할 수
# 왼쪽기준으로 정렬했을 때, 오른쪽 수가 오름차순을 거스르면 겹치는 선. 따라서 최장 길이 수열(m) 구하기
if __name__ == '__main__':
    n = int(input())
    wire = [list(map(int, input().split())) for _ in range(n)]
    wire.sort(key=lambda x: x[0])
    dp = [1]*501

    for i in range(n):
        for j in range(i):
            if wire[i][1] > wire[j][1]:
                dp[i] = max(dp[i], dp[j]+1)
    print(n-max(dp))



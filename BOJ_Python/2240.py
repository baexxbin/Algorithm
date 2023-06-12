import sys

input = sys.stdin.readline


if __name__ == '__main__':
    t, w = map(int, input().split())
    plum = [int(input()) for _ in range(t)]
    dp = [[0]*(w+1) for _ in range(t+1)]    # dp[t][w] : t시간에 자두가 움직인 횟수w

    for i in range(1, t+1):
        if plum[i-1] == 1:                  # 처음위치1에서 안움직이는 경우
            dp[i][0] = dp[i-1][0] +1

        for j in range(1, w+1):             # j만큼 움직였을 때
            if plum[i-1] == (j%2)+1:        # 자두위치 == 현재위치, (움직인 횟수(j%2) 홀수(1): 2번, 짝수(0): 1번)
                dp[i][j] = max(dp[i-1][j-1], dp[i-1][j])+1      # 이전시간에, 지금 위치랑 이전위치 중 사과 많이 받은값에+1
                continue
            dp[i][j] = max(dp[i-1][j-1], dp[i-1][j])
    print(max(dp[t]))

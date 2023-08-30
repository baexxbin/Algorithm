import sys

input = sys.stdin.readline

# 7570 줄 세우기

# 최소로 이동(맨 앞, 맨 뒤)시켜 정렬시키기
# 방법1: x, x-1보다 앞에 있으면 이동시키기 (작은 수 부터 돌면서) >> 시간초과
# 방법2: 가장 긴 증가하는 부분 수열 이용 >> 이미 순서대로 있는 수는 움직일 필요없음, N-(순서대로 있는 수의 갯수) = 움직여야할 수

if __name__ == '__main__':
    N = int(input())
    nums = list(map(int, input().split()))

    dp = [0]*(N+1)      # 가장 긴 부분 수열의 길(x포함 1씩 증가)

    for num in nums:    # nums배열 처음부터 보면서 확인
        dp[num] = dp[num-1]+1
    LIS = max(dp)
    print(N-LIS)


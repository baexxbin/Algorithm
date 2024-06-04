import sys

input = sys.stdin.readline

if __name__ == '__main__':
    N = int(input())
    arr = list(map(int, input().split()))

    dp = [1]*(N)        # LIS 길이 저장

    for i in range(N):
        for j in range(i):
            if arr[i] > arr[j]:
                dp[i] = max(dp[i], dp[j]+1)

    mxIdx = max(dp)     # 가장 큰 LIS의 값이자 인덱스
    print(mxIdx)

    res = []
    for i in range(N-1, -1, -1):
        if dp[i] == mxIdx:
            res.append(arr[i])
            mxIdx-=1    # 그 다음으로 와야할 값의 인덱스(LIS값) 찾음
    res.reverse()
    print(*res)
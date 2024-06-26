import sys

input = sys.stdin.readline

# 길이 자체로 저장하면 편하지만 시간초과 남

if __name__ == '__main__':
    A = [0] + list(input().strip())
    B = [0] + list(input().strip())

    l1 = len(A)
    l2 = len(B)
    dp = [[0]*l2 for _ in range(l1)]

    for i in range(1, l1):
        for j in range(1, l2):
            if A[i]==B[j]:
                dp[i][j] = dp[i-1][j-1]+1
            else:
                dp[i][j] = max(dp[i][j-1], dp[i-1][j])

    ans1 = dp[-1][-1]
    print(ans1)

    if ans1!=0:
        i, j = l1-1, l2-1
        lcs = []
        while i>0 and j>0:
            if A[i] == B[j]:
                lcs.append(A[i])
                i-=1
                j-=1
            elif dp[i-1][j] >= dp[i][j-1]:      # LCS값이 큰 쪽으로 이동
                i-=1
            else:
                j-=1
        lcs.reverse()
        print(''.join(lcs))



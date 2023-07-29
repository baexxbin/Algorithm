import sys

input = sys.stdin.readline

if __name__ == '__main__':
    N = int(input())
    lst = list(map(int, input().split()))

    st, ed = 0, N - 1
    tmp = float('inf')
    ans = float('inf')
    while st < ed:
        cur = lst[st] + lst[ed]
        if cur < 0:
            st += 1
        else:
            ed -= 1

        if abs(cur) < tmp:
            tmp = abs(cur)
            ans = cur
    print(ans)

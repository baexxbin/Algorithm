import sys

input = sys.stdin.readline

if __name__ == '__main__':
    n, m = map(int, input().split())
    lst = list(map(int, input().split()))

    window = sum(lst[:m])
    mx = window
    for i in range(m, n):
        window += lst[i] - lst[i-m]
        mx = max(mx, window)
    print(mx)

# 슬라이딩 윈도우
# 이전 윈도우의 합의 재사용으로 효율적
# 추가적인 공간을 사용하지 않아 효율적
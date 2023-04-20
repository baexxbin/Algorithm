import sys

input = sys.stdin.readline


if __name__ == '__main__':
    n = int(input())
    a, b, c = map(int, input().split())
    dp_mx = [a, b, c]
    dp_mn = [a, b, c]
    for _ in range(n - 1):
        floor = list(map(int, input().split()))
        # 최대값
        tmp = tuple(dp_mx)
        dp_mx[0] = max(tmp[0], tmp[1]) + floor[0]
        dp_mx[1] = max(tmp) + floor[1]
        dp_mx[2] = max(tmp[1], tmp[2]) + floor[2]

        # 최소값
        tmp = tuple(dp_mn)
        dp_mn[0] = min(tmp[0], tmp[1]) + floor[0]
        dp_mn[1] = min(tmp) + floor[1]
        dp_mn[2] = min(tmp[1], tmp[2]) + floor[2]

    print(max(dp_mx), min(dp_mn))
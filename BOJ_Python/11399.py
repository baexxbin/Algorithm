import sys

input = sys.stdin.readline


if __name__ == '__main__':
    N = int(input())
    cost = list(map(int, input().split()))
    cost.sort()

    ans = 0
    tmp = 0
    for c in cost:
        tmp += c
        ans += tmp
    print(ans)
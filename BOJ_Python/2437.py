import sys

input = sys.stdin.readline


if __name__ == '__main__':
    N = int(input())
    weight = list(map(int, input().split()))
    weight.sort()

    target = 1
    for num in weight:
        if target < num:
            break
        target+=num
    print(target)

# 이어지지 않는 구간 합 구간을 구해야함

# 조합 또는 dfs 사용하려면(2^N), N 조건이 20이하여야함.
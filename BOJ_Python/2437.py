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

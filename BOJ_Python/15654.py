import sys

input = sys.stdin.readline


def backtracking(depth):
    if depth == M:
        print(' '.join(map(str, tmp)))
        return

    for i in range(N):
        if nums[i] in tmp:
            continue
        tmp.append(nums[i])
        backtracking(depth + 1)
        tmp.pop()


if __name__ == '__main__':
    N, M = map(int, input().split())
    nums = sorted(list(map(int, input().split())))

    tmp = []
    backtracking(0)

import sys

input = sys.stdin.readline


def backtracking(start, depth):
    if depth == M:
        print(' '.join(map(str, tmp)))
        return

    for i in range(start, len(nums)):
        tmp.append(nums[i])
        backtracking(i, depth + 1)
        tmp.pop()


if __name__ == '__main__':
    N, M = map(int, input().split())
    nums = sorted(set(list(map(int, input().split()))))

    tmp = []
    backtracking(0, 0)

# 이 N과 M(8)과 같은 문제로 주어지는 nums의 수들이 중복된다는 차이만 있다.
# 중복되는 결과 수열들을 없애기 위해, 수열을 만드는 nums를 set을 통해 중복 수를 걸러준다.

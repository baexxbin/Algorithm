import sys

input = sys.stdin.readline


def countOdd(nums):     # 홀수 갯수 카운트
    tmp = 0
    for n in nums:
        if n in '13579':
            tmp += 1
    return tmp


def divide(nums, cnt):
    cnt += countOdd(nums)

    if len(nums) == 1:
        ans.append(cnt)
        return

    elif len(nums) == 2:
        divide(str(int(nums[0]) + int(nums[1])), cnt)

    else:
        for i in range(1, len(nums) - 1):  # 최소 2개는 남겨놔야 3등분할 수 있음
            for j in range(i + 1, len(nums)):
                divide(str(int(nums[:i]) + int(nums[i:j]) + int(nums[j:])), cnt)


if __name__ == '__main__':
    num = input().strip()
    ans = []
    divide(num, 0)
    print(min(ans), max(ans))
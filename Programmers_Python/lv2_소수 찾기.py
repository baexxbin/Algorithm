from itertools import permutations


# 1. numbers르 만들 수 있는 모든 순열 찾기
# 2. 순열의 각 숫자가 소수인지 판단

def isPrime(x):
    for i in range(2, int(x ** (1 / 2)) + 1):
        if x % i == 0:
            return False
    return True


def solution(numbers):
    nums = [n for n in numbers]

    # numbers로 만들수 있는 숫자의 순열 구하기
    tmp = []
    for i in range(1, len(nums) + 1):
        tmp += list(permutations(nums, i))  # 순열을 이용해서 부분집합 구하기
    sub = [int("".join(x)) for x in tmp]    # 문자열로 구한 부분집합들을 합쳐서 int로 변환

    sub = set(sub)      # 중복 값 제거

    cnt = 0
    for n in sub:
        if n < 2:       # 0,1은 소수 아니므로 패쓰
            continue

        if isPrime(n):  # 각 조합안의 숫자가 소수인지 판별하기
            cnt += 1
    return cnt

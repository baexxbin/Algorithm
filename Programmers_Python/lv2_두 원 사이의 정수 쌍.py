import math


def solution(r1, r2):
    cnt = 0
    for x in range(1, r2 + 1):
        y_mx = math.floor(math.sqrt(r2 ** 2 - x ** 2))
        y_mn = 0 if x >= r1 else math.ceil(math.sqrt(abs(r1 ** 2 - x ** 2)))
        cnt += y_mx - y_mn + 1

    return cnt * 4

# 1사분면의 점들을 구해서 *4해주기
# x값이 1부터 r2까지 돌면서 진행
    # x값이 주어졌을때 해당 y좌표 구하기
    # 이때 해당되는 점들은 최대y값(큰원r2)에서 최소y값(작은원r1, 근데 x가 작은원 지름보다 크면 y값은 항상 0임 << x축 값)
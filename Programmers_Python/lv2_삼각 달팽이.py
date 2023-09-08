import sys

sys.setrecursionlimit(10 ** 6)      # 재귀깊이를 늘려주기 위해 추가!

# [0]*1 ~ [0]*n으로 달팽이 배열 초기화
# 1. 각 배열의 첫 부분에 num 넣기
# 2. 마지막 배열(finish)이면 배열 다 채우기
# 3. 마지막 배열 전(finish-1)부터 거꾸로 올라가면서 num 넣기
# 4. 반복

def recursive(depth, idx):
    global arr, finish, num

    if depth == finish:                     # 2. 마지막 배열(finish)이면 배열 다 채우기
        for i in range(idx, depth + 1):     # 숫자 쫘르륵 채우기
            if arr[depth][i] == 0:
                arr[depth][i] = num
                num += 1
        return

    arr[depth][idx] = num                   # 1. 각 배열의 첫 부분에 num 넣기
    num += 1
    recursive(depth + 1, idx)

    if depth != idx and arr[depth][depth - idx] == 0:   # 3. 마지막 배열 전(finish-1)부터 거꾸로 올라가면서 num 넣기
        arr[depth][depth - idx] = num
        num += 1


def solution(n):
    global arr, finish, num

    # 전체 수의 갯수 (등차수열의 합)
    endNum = n * (n + 1) // 2

    # [0]*1 ~[0]*n까지로 초기화된 달팽이 배열 선언
    arr = []
    for i in range(1, n + 1):
        arr.append([0] * i)

    finish = n - 1  # 숫자를 다 채워야하는 마지막 배열의 번호

    num = 1         # 배열에 들어갈 숫자
    idx = 0         # 배열에서 num 넣을 인덱스

    # 4. 반복
    for depth in range(n):          # depth는 재귀를 시작하는 배열의 인덱스
        if arr[depth][idx] != 0:    # 새롭게 재귀를 시작해야하는데, arr[depth][idx]의 값이 이미 채워져있으면 다음 depth부터 시작
            continue

        recursive(depth, idx)       # idx번째에 num을 넣는 재귀 시작
        idx += 1
        finish -= 1

    ans = []
    for row in arr:     # 정답 출력 포맷 맞추기
        ans += row
    return ans



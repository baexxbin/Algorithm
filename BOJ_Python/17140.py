import sys
from collections import defaultdict

input = sys.stdin.readline


# R 또는 C 연산 진행
#   - 어떤 연산 수행할 지 선택: 현재 배열의 행, 열의 길이 필요
#       - for문으로 배열 돌면서 각 요소 카운트++
# 연산 수행
#   - 각 줄에 해당되는 배열을 (수, 갯수) 형태로 만들기
#   - (수, 갯수) 수를 기준으로 정렬
#       - 1) 개수: 오름차순, 2) 수: 오름차순
#   - 줄의 최대 길이 업데이트 및 0채우고 정렬된 값 원래 배열에 넣기
#
# * 열 연산
#   - 방법1: for문 j,i 순으로 진행
#   - 방법2: 배열돌려서 R연산처럼 진행 후 다시 배열 돌리기, zip이용 (선택)
# * 배열 크기 100초과 처리

# * 놓쳤던 부분: cur_R, cur_C로 각 최대값을 calculate에서 반환받아 업데이트해주는 형식으로 작성했었는데,
#             이렇게 되면 현재 배열의 길이가 아닌 과거의 값과 합쳐진 길이로 비교를 하게된다. 따라서 바로 len으로 현재 배열의 행,열 길이를 얻어온다.

def calculate(arr):
    new_matrix = []

    # R연산 수행
    for row in arr:
        dic = defaultdict(int)
        for k in row:
            if k == 0:      # *주의* 단지 빈자리를 채워준 0은 계산하지 않음
                continue
            dic[k] += 1
        tmp = sorted(dic.items(), key=lambda x: (x[1], x[0]))  # 딕셔너리 value값 정렬 후 key값 정렬
        new_matrix.append([x for pair in tmp for x in pair])

    # 현재 가장 긴 행 업데이트 및 100 이상일 경우 컷
    mx_R = max(new_matrix, key=len)
    if len(mx_R) > 100:
        mx_R = mx_R[:100]
    mx_len = len(mx_R)

    # 빈칸 0으로 채우기
    for i in range(len(new_matrix)):
        if len(new_matrix[i]) < mx_len:
            new_matrix[i] += [0] * (mx_len - len(new_matrix[i]))

    return new_matrix


def solution():
    global matrix
    time = 0

    while time <= 100:
        if 0 <= r < len(matrix) and 0 <= c < len(matrix[0]) and matrix[r][c] == k:  # *주의* 배열의 크기가 r,c보다 작으면 안됨
            return time

        # 수행할 연산 선택
        if len(matrix) >= len(matrix[0]):
            new_matrix = calculate(matrix)
        else:
            new_matrix = calculate(list(zip(*matrix)))  # 행 열 변환 배열 넘기기
            new_matrix = list(zip(*new_matrix))  # matrix 언패킹하기

        matrix = new_matrix
        time += 1

    return -1


if __name__ == '__main__':
    r, c, k = map(int, input().split())
    r -= 1
    c -= 1
    matrix = [list(map(int, input().split())) for _ in range(3)]

    print(solution())

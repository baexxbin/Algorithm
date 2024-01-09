import sys

input = sys.stdin.readline


def calOne(arr):  # 상하반전
    n = len(arr)
    tmp = []
    for i in range(n - 1, -1, -1):
        tmp.append(arr[i])
    return tmp


def calTwo(arr):
    n = len(arr)
    tmp = []
    for i in range(n):
        tmp.append(list(reversed(arr[i])))
    return tmp


def calThree(arr):
    n = len(arr)
    m = len(arr[0])
    tmp = [[0] * n for _ in range(m)]  # 새로운 배열은 행과 열의 크기가 바뀜
    for i in range(n):  # 참고하는 원래 배열(arr)은 이전의 행열을 바탕으로 for문 돌림
        for j in range(m):
            tmp[j][n - i - 1] = arr[i][j]  # 새로운 배열의 열에서 빼야하는 값은 새로운 배열의 열(N)크기
    return tmp


def calFour(arr):
    n = len(arr)
    m = len(arr[0])
    tmp = [[0] * n for _ in range(m)]
    for i in range(n):
        for j in range(m):
            tmp[m - j - 1][i] = arr[i][j]  # tmp의 행 값은 원래 배열 arr의 열(m)을 바탕으로 만듦, 이전 arr의 열값은 현재 tmp의 행(m)값임
    return tmp


def calGroup(arr, num):
    n = len(arr)
    m = len(arr[0])
    # 꼭짓점 잡고 각 꼭짓점으로 시작해서 서브배열 만들기
    sn = n // 2
    sm = m // 2
    vertex = [(0, 0), (0, sm), (sn, 0), (sn, sm)]   # 1-2-4-3 형태(숫자는 문제에 주어진 배열 분할 구혁 기준)
    tmp = []
    for x, y in vertex:
        part = [[0] * sm for _ in range(sn)]
        for i in range(sn):
            for j in range(sm):
                part[i][j] = arr[x + i][y + j]
        tmp.append(part)

    # 만든 서브배열들 순서에 맞게 조합
    nw_tmp = [[0] * m for _ in range(n)]
    if num == 5:
        v5 = [2, 0, 3, 1]
        k = 0
        for x, y in vertex:
            for i in range(sn):
                for j in range(sm):
                    nw_tmp[x + i][y + j] = tmp[v5[k]][i][j]     # 서브배열 합칠때는 분리할때와 반대로 nw_tmp에 꼭짓점을 잡고 각 서브배열 값을 넣어줌
            k += 1
    else:
        v6 = [1,3,0,2]
        k = 0
        for x, y in vertex:
            for i in range(sn):
                for j in range(sm):
                    nw_tmp[x + i][y + j] = tmp[v6[k]][i][j]
            k += 1
    return nw_tmp


if __name__ == '__main__':
    N, M, R = map(int, input().split())
    matrix = list(list(map(int, input().split())) for _ in range(N))
    R_lst = list(map(int, input().split()))

    for num in R_lst:
        if num == 1:
            matrix = calOne(matrix)
        elif num == 2:
            matrix = calTwo(matrix)
        elif num == 3:
            matrix = calThree(matrix)
        elif num == 4:
            matrix = calFour(matrix)
        else:
            matrix = calGroup(matrix, num)

    # 결과 배열 출력
    for row in matrix:
        print(*row)

# *주의할 점*
# - reversed
#   reversed한 배열은 reveres객체로 반환 됨. 따라서 list로 변환해주기

# - 서브배열
#   문제에서 나눈 서브배열의 순서 1-2-3-4와 코드에서 꼭짓점을 기준으로 나눈 서브배열 순서 1-2-4-3으로 순서가 다름
#   따라서 문제의 배열 순서에 맞도록 배열 넣어주기

#   서브배열 붙일때는 분리할때랑 반대로 nw_tmp에 꼭짓점값들을 더해서 현재 작은 행x,열y 값 넣어주기

# - 행과 열 (N,M)
#   해당 문제는 연산을 여러번 수행하는 문제임. 따라서 배열의 크기가 3,4번 연산일때 뿐 아니라 다른 곳에서도 변형된 크기의 배열로 연산해야함
#   따라서 N,M은 초기 값을 사용하는 것이아닌, 매개변수로 받은 배열의 행과 열을 다시 구해서 진행
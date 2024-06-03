from itertools import combinations


def calculation(eq1, eq2):
    x1, y1, c1 = eq1  # 직선1
    x2, y2, c2 = eq2  # 직선2

    # 기울기가 깉아 해가 없는 경우
    if x1 * y2 == y1 * x2:
        return

    # 두 직선의 해
    x = (y1 * c2 - c1 * y2) / (x1 * y2 - y1 * x2)
    y = (c1 * x2 - x1 * c2) / (x1 * y2 - y1 * x2)

    # 두 직선의 해 x, y가 모두 정수라면 반환
    if x == int(x) and y == int(y):
        return [int(x), int(y)]


def solution(lines):
    points = []
    # 모든 선들의 교점 확인
    for eq1, eq2 in combinations(lines, 2):
        point = calculation(eq1, eq2)
        if point: points.append(point)


    # 그림의 시작점과 마지막점 찾기
    w1, w2 = min(points, key=lambda x: x[0])[0], max(points, key=lambda x: x[0])[0] + 1
    h1, h2 = min(points, key=lambda x: x[1])[1], max(points, key=lambda x: x[1])[1] + 1

    # 별을 포함하는 최소한의 크기 배열 생성
    answer = [['.'] * (w2 - w1) for _ in range((h2 - h1))]

    # 그림의 시작점을 기준으로 교점 위치 "*" 변환
    for x, y in points:
        answer[y - h1][x - w1] = '*'

    # 배열이 증가하는 방향(아래로 증가)은 좌표값이 증가하는 방향(위로 증가)과 반대
    answer.reverse()

    return [''.join(a) for a in answer]


if __name__ == '__main__':
    line = [[2, -1, 4], [-2, -1, 4], [0, -1, 1], [5, -8, -12], [5, 8, 12]]
    res = ["....*....", ".........", ".........", "*.......*", ".........", ".........", ".........", ".........", "*.......*"]
    print(solution(line)==res)


# def cd2arrIdx(M, N, x, y):
#     r = M // 2 - y
#     c = x + N // 2
#     return r, c
#
#
# def solution(line):
#     # 직선 쌍의 정수 교점 구하기
#     cd = []
#     for a in range(len(line) - 1):
#         for b in range(a + 1, len(line)):
#             denominator = line[a][0] * line[b][1] - line[a][1] * line[b][0]
#             if denominator ==0: continue
#             x = (line[a][1] * line[b][2] - line[a][2] * line[b][1]) / denominator
#             y = (line[a][2] * line[b][0] - line[a][0] * line[b][2]) / denominator
#             if x == int(x) and y == int(y):
#                 cd.append((int(x), int(y)))
#
#     print(cd)
#     # 교점 2차원 배열로 변환
#     mxx, mxy = min(1000, max(cd, key=lambda x: x[0])[0]), min(1000, max(cd, key=lambda x: x[1])[1])
#     mnx, mny = min(cd, key=lambda x: x[0])[0], min(cd, key=lambda x: x[1])[1]
#
#     # 전체 배열 크기 구하기
#     M, N = mxy - mny + 1, mxx - mnx + 1
#
#     # 좌표 변환 및 배열 만들기
#     board = [['.' for _ in range(N)] for _ in range(M)]
#     for x, y in cd:
#         r, c = cd2arrIdx(M, N, x, y)
#         board[r][c] = '*'
#
#     for i in range(len(board)):
#         board[i] = ''.join(board[i])
#
#     return board

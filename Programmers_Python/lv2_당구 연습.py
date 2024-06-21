# 선대칭이동 개념 사용
def solution(m, n, startX, startY, balls):
    ans = []
    for bx, by in balls:
        # 각 벽을 맞았을때 최단거리 값
        candidate = []
        up = (startX-bx)**2 + (startY-(2*n-by))**2
        left = (startX+bx)**2 + (startY-by)**2
        down = (startX-bx)**2 + (startY+by)**2
        right = (startX-(2*m-bx))**2 + (startY-by)**2

        # 각 벽에 맞출 수 없는 경우 예외처리
        if not (startX == bx and by > startY):
            candidate.append(up)
        if not (startY==by and bx < startX):
            candidate.append(left)
        if not (startX==bx and by < startY):
            candidate.append(down)
        if not (startY==by and bx > startX):
            candidate.append(right)
        ans.append(min(candidate))

    return ans


if __name__ == '__main__':
    m = 10
    n = 10
    startX = 3
    startY = 7
    balls = [[7, 7], [2, 7], [7, 3]]

    print(solution(m, n, startX, startY, balls))
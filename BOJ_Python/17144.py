import sys

input = sys.stdin.readline


def diffuse():
    dust = [[0] * c for _ in range(r)]
    for i in range(r):
        for j in range(c):
            if board[i][j] > 0:
                tmp = 0
                for k in range(4):
                    nx, ny = i + dx[k], j + dy[k]
                    if 0 <= nx < r and 0 <= ny < c and board[nx][ny] != -1:
                        dust[nx][ny] += board[i][j]//5
                        tmp += board[i][j]//5
                board[i][j] -= tmp

    for i in range(r):
        for j in range(c):
            board[i][j] += dust[i][j]


def upClean():
    dir = 1
    before = 0
    x, y = upRobot, 1
    while True:
        nx, ny = x+dx[dir], y+dy[dir]
        if nx == r or ny == c or nx == -1 or ny == -1:
            dir = (dir-1)%4
            continue
        if x == upRobot and y == 0:
            break
        board[x][y], before = before, board[x][y]
        x, y = nx, ny


def downClean():
    dir = 1                 # 처음 오른쪽 시작
    before = 0              # 먼지 날아가기 전 값
    x, y = downRobot, 1     # 처음 먼지 시작 위치
    while True:
        nx, ny = x + dx[dir], y+dy[dir]
        if nx==r or ny==c or nx==-1 or ny==-1:          # x,y값이 모서리일 경우 방향 바꾸기
            dir = (dir+1)%4
            continue
        if x == downRobot and y == 0:                   # 한바퀴 다 돌고오면 반복문 탈출
            break
        board[x][y], before = before, board[x][y]       # 현재에는 이전값, 새로운 이전값은 현재 값으로 업데이트
        x, y = nx, ny                                   # 위치 업데이트


if __name__ == '__main__':
    r, c, t = map(int, input().split())
    board = [list(map(int,input().split())) for _ in range(r)]
    upRobot, downRobot = 0, 0

    # 상, 우, 좌 ,하
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]

    # 공기 청정기 찾기
    for j in range(r):
        if board[j][0] == -1:
            upRobot = j
            downRobot = j+1
            break

    for _ in range(t):
        diffuse()
        upClean()
        downClean()

    ans = 2
    for i in range(r):
        ans += sum(board[i])
    print(ans)
import sys
from collections import deque

input = sys.stdin.readline


# 1. 상어 잡기 (i열에서 가장 가까운 행)
# 2. 상어 이동
#   각각 자기 속력이랑 방향만큼 이동 (벽에선 뱡향 틀기)
#   같은 칸에 있는 상어는 큰 상어한테 잡아먹힘
# 3. 마지막 열에 도착할때까지 반복


def catch(col, board):
    shark = 0
    for i in range(R):
        if board[i][col]:
            shark = board[i][col][0][2]
            board[i][col] = []
            break
    return shark, board


def move(board):
    tmp = [[[] for _ in range(C)] for _ in range(R)]
    trans = [1, 0, 3, 2]
    for i in range(R):
        for j in range(C):
            if board[i][j]:
                x,y = i, j
                s, d, z = board[i][j][0]
                distance = s
                while distance:
                    x,y = x+dx[d], y+dy[d]
                    if 0<=x<R and 0<=y<C:
                        distance-=1
                    else:
                        x, y = x-dx[d], y-dy[d]
                        d = trans[d]
                tmp[x][y].append([s,d,z])

    # 상어 중복 시 잡아먹기
    for i in range(R):
        for j in range(C):
            if len(tmp[i][j]) > 1:
                tmp[i][j].sort(key=lambda x:x[2], reverse=True)
                s, d, z = tmp[i][j][0]
                tmp[i][j] = [[s, d, z]]
    return tmp


if __name__ == '__main__':
    R, C, M = map(int, input().split())
    board = [[[] for _ in range(C)] for _ in range(R)]
    for _ in range(M):
        r, c, s, d, z = map(int, input().split())
        board[r-1][c-1].append([s,d-1,z])

    dx = [-1, 1, 0, 0]  # 상하우좌
    dy = [0, 0, 1, -1]

    ans = 0
    for j in range(C):
        eat, board = catch(j, board)
        ans+=eat
        board = move(board)
    print(ans)


# 처음 생각한 큐로 풀려면 현재상어큐, 죽은상어큐 두개로 풀기
# 이때 크기가 모두 다른 점 이용하기
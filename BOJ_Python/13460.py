import sys
from collections import deque

input = sys.stdin.readline


def tilt(x, y, k):
    move = 0
    while board[x + dx[k]][y + dy[k]] != '#' and board[x][y] != 'O':
        x += dx[k]
        y += dy[k]
        move += 1
    return x, y, board[x][y] == 'O', move


def bfs(idx):
    ri, rj, bi, bj = idx
    que = deque()
    que.append((ri, rj, bi, bj, 0))
    visited = set()
    visited.add((ri, rj, bi, bj))

    while que:
        rx, ry, bx, by, cnt = que.popleft()
        if cnt > 10:
            return -1

        for k in range(4):
            nrx, nry, isGoalR, r_move = tilt(rx, ry, k)
            nbx, nby, isGoalB, b_move = tilt(bx, by, k)

            if isGoalB:  # 파란구슬 들어가는 경우는 필요없으니 패쓰
                continue
            if isGoalR:
                return cnt + 1
            if (nrx, nry) == (nbx, nby):  # 구슬 같은 위치에 있을 수 없음 >> 이동거리를 통한 처리
                if r_move > b_move:
                    nrx -= dx[k]
                    nry -= dy[k]
                else:
                    nbx -= dx[k]
                    nby -= dy[k]
            if (nrx, nry, nbx, nby) not in visited:  # set을 통한 visited 처리
                visited.add((nrx, nry, nbx, nby))
                que.append((nrx, nry, nbx, nby, cnt + 1))
    return -1


if __name__ == '__main__':
    N, M = map(int, input().split())

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    board = []
    idx = [0, 0, 0, 0]

    for row in range(N):
        tmp = list(map(str, input().strip()))
        if 'R' in tmp:
            idx[0], idx[1] = row, tmp.index('R')
        if 'B' in tmp:
            idx[2], idx[3] = row, tmp.index('B')

        board.append(tmp)
    ans = bfs(idx)

    # 배열 범위를 벗어나지만, 기울기 수를  10번, 11번일 경우 예외처리
    print(-1) if ans > 10 else print(ans)

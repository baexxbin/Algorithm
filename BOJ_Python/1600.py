import sys
from collections import deque

input = sys.stdin.readline


def bfs():
    que = deque()
    que.append((0,0,0))
    visited[0][0][0] = 1

    while que:
        cx, cy, ck = que.popleft()

        if cx == h - 1 and cy == w - 1:
            return visited[cx][cy][ck]-1

        for i in range(4):
            nx, ny = cx + dx[i], cy + dy[i]
            if 0 <= nx < h and 0 <= ny < w:
                if board[nx][ny] == 0 and visited[nx][ny][ck] == 0:
                    que.append((nx, ny, ck))
                    visited[nx][ny][ck] = visited[cx][cy][ck] + 1

        if ck < k:
            for i in range(8):
                nx, ny = cx + hx[i], cy + hy[i]
                if 0 <= nx < h and 0 <= ny < w:
                    if board[nx][ny] == 0 and visited[nx][ny][ck+1] == 0:
                        que.append((nx, ny, ck+1))
                        visited[nx][ny][ck+1] = visited[cx][cy][ck] + 1

    return -1


if __name__ == '__main__':
    k = int(input())
    w, h = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(h)]

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    # 말 이동 방향
    hx = [-2, -2, 2, 2, -1, 1, -1, 1]  # 상1, 상2, 하1, 하2, 좌1, 좌2, 우1, 우2
    hy = [-1, 1, -1, 1, -2, -2, 2, 2]

    visited = [[[0]*(k+1) for _ in range(w)] for _ in range(h)]
    print(bfs())
import sys
from collections import deque

input = sys.stdin.readline

dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

def makeBridge(i, j):
    global mn
    visited = [[False] * N for _ in range(N)]
    que = deque()
    que.append((i, j, 0))
    idx = board[i][j]
    visited[i][j] = True

    while que:
        y, x, d = que.popleft()
        for k in range(4):
            ny, nx = y + dy[k], x + dx[k]
            if not (0 <= ny < N and 0 <= nx < N):
                continue
            if not visited[ny][nx] and board[ny][nx] != idx:
                visited[ny][nx] = True
                if board[ny][nx]==0:
                    que.append((ny, nx, d+1))
                else:
                    mn = min(mn, d)


def check_island(i, j, idx):
    que = deque()
    que.append((i, j))

    board[i][j] = idx

    while que:
        y, x = que.popleft()
        for k in range(4):
            ny, nx = y+dy[k], x+dx[k]
            if not (0<=ny<N and 0<=nx<N):
                continue
            if board[ny][nx] == 1:
                board[ny][nx] = idx
                que.append((ny, nx))



if __name__ == '__main__':
    N = int(input())
    board = [list(map(int, input().split())) for _ in range(N)]


    # 각 섬 체크하기
    idx = 2
    for i in range(N):
        for j in range(N):
            if board[i][j] == 1:
                check_island(i, j, idx)
                idx+=1

    # 다리 잇기 (바다일때만 이동, 다른 숫자만나면 끝)
    mn = float('inf')
    for i in range(N):
        for j in range(N):
            if board[i][j] > 1:
                makeBridge(i, j)
    print(mn)
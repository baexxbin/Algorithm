import copy
import sys
from collections import deque

input = sys.stdin.readline


def spreadVirus():
    global ans
    que = deque()
    virus = copy.deepcopy(board)

    for i in range(n):
        for j in range(m):
            if virus[i][j] == 2:
                que.append((i, j))
    while que:
        x, y = que.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < n and 0 <= ny < m and virus[nx][ny] == 0:
                que.append((nx, ny))
                virus[nx][ny] = 2

    cnt = 0
    for i in range(n):
        cnt += virus[i].count(0)
    ans = max(ans, cnt)


def dfs(cnt):
    if cnt == 3:
        spreadVirus()
        return

    for i in range(n):
        for j in range(m):
            if board[i][j] == 0:
                board[i][j] = 1
                dfs(cnt + 1)
                board[i][j] = 0


if __name__ == '__main__':
    n, m = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(n)]
    ans = 0

    dx = [1, -1, 0, 0]
    dy = [0, 0, -1, 1]

    # dfs를 통해 벽 세우기
    dfs(0)

    # 벽이 3개일 경우 bfs로 바이러스 퍼트리기
    print(ans)

# 파이썬으로 돌렸을 때 시간초과..
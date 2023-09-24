from collections import deque

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
n, m = 0, 0
board, visited = [], []


def bfs(i, j):
    que = deque()
    que.append((i, j))
    visited[i][j] = True
    cnt = int(board[i][j])

    while que:
        x, y = que.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if not (0 <= nx < n and 0 <= ny < m):
                continue

            if board[nx][ny] != 'X' and not visited[nx][ny]:
                que.append((nx, ny))
                visited[nx][ny] = True
                cnt += int(board[nx][ny])

    return cnt


def solution(maps):
    global n, m, board, visited
    n, m = len(maps), len(maps[0])
    board = [list(row) for row in maps]
    visited = [[False] * m for _ in range(n)]

    ans = []
    for i in range(n):
        for j in range(m):
            if board[i][j] != 'X' and not visited[i][j]:
                ans.append(bfs(i, j))

    print(sorted(ans)) if ans else print([-1])


maps =["XXX","XXX","XXX"]
# maps = ["X591X", "X1X5X", "X231X", "1XXX1"]
solution(maps)

# 아주 기본적인 bfs문제
# solution에서는 전역변수를 global로 지정해줬는데 왜 bfs()에서는 global로 지정하지 않아도 이용가능한걸까..?



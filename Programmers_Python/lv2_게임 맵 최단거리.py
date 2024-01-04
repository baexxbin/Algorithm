from collections import deque

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def solution(maps):
    n = len(maps)
    m = len(maps[0])

    visited = [[-1 for _ in range(m)] for _ in range(n)]

    que = deque()
    que.append((0, 0))
    visited[0][0] = 0

    cnt = -1
    while que:
        x, y = que.popleft()
        if x == n - 1 and y == m - 1:
            cnt = visited[x][y] + 1
            break

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if not (0 <= nx < n and 0 <= ny < m):
                continue
            if maps[nx][ny] == 1 and visited[nx][ny] == -1:
                que.append((nx, ny))
                visited[nx][ny] = visited[x][y] + 1
    return cnt

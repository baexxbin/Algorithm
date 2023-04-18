import sys
from collections import deque

input = sys.stdin.readline

# 현재 풀이는 빈칸(0)을 기준으로 bfs탐색한 방법
# 상어(1)를 기준으로 bfs를 실행해 칸들의 거리를 업데이트 한 후, 가장 큰 값을 찾는 방식이 훨씬 빠름

def bfs(x, y):
    visited = [[-1] * m for _ in range(n)]
    que = deque()
    que.append((x, y))
    visited[x][y] = 0

    while que:
        x, y = que.popleft()
        for i in range(8):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < n and 0 <= ny < m and visited[nx][ny]==-1:
                if matrix[nx][ny] == 1:
                    return visited[x][y]+1
                que.append((nx, ny))
                visited[nx][ny] = visited[x][y]+1   # 방문한 깊이에 따른 거리 업데이트
    return visited[x][y]


if __name__ == '__main__':
    n, m = map(int, input().split())
    matrix = [list(map(int, input().split())) for _ in range(n)]

    # 상하좌우대각선
    dx = [-1, 0, 1, -1, 1, -1, 0, 1]
    dy = [1, 1, 1, 0, 0, -1, -1, -1]

    mx = float('-INF')
    for i in range(n):
        for j in range(m):
            if matrix[i][j] == 0:
                mx = max(mx, bfs(i, j))
    print(mx)

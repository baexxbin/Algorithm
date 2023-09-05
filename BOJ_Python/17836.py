import sys
from collections import deque

input = sys.stdin.readline


# 1) 검 >> 그람 (최단거리) + 그람 >> 공주(최단거리, 벽X)
# 2) 검 >> 공주 (최단거리)
# min((1), (2))

def bfs(i, j):
    que = deque()
    que.append((i, j))
    visited = [[-1] * M for _ in range(N)]
    visited[i][j] = 0       # 시작점도 방문처리하기!
    gram = float('inf')

    while que:
        x, y = que.popleft()

        if matrix[x][y] == 2:
            gram = visited[x][y] + (N-1-x) + (M-1-y)       # 그램까지 거리 + (공주-그램)거리

        if x == N - 1 and y == M - 1:
            return min(visited[x][y], gram)

        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if not (0 <= nx < N and 0 <= ny < M):
                continue
            if matrix[nx][ny] != 1 and visited[nx][ny] == -1:
                que.append((nx, ny))
                visited[nx][ny] = visited[x][y] + 1

    return gram


if __name__ == '__main__':
    N, M, T = map(int, input().split())
    matrix = [list(map(int, input().split())) for _ in range(N)]

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    result = bfs(0, 0)
    print("Fail") if result > T else print(result)

# 그냥 점과 점간의 거리 구할때는 bfs안하고 (x2-x1)+(y2-y1)하면 된다
# 문제 조건 잘 확인하기! (T시간 초과하지 않기)
# 시작점 방문처리도 잊지않기!

import sys
from collections import deque

input = sys.stdin.readline


# 벽 안뿌수기: 비용0, 벽 뿌수기: 비용1
# 비용0을 que에 먼저넣어 최대한 벽 안뿌수고 가기
def bfs(i, j):
    que = deque()
    que.append((i, j))
    dist[i][j] = 0

    while que:
        x, y = que.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M and dist[nx][ny] == -1:
                if matrix[nx][ny] == 0:  # 벽 안 뿌셨을 경우
                    dist[nx][ny] = dist[x][y]
                    que.appendleft((nx, ny))
                    continue
                que.append((nx, ny))  # 벽 뿌셨을 경우
                dist[nx][ny] = dist[x][y] + 1


if __name__ == '__main__':
    M, N = map(int, input().split())  # 열, 행 순으로 입력
    matrix = [list(map(int, input().strip())) for _ in range(N)]

    dist = [[-1] * M for _ in range(N)]   # bfs특징, 최소거리 구하기
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    bfs(0, 0)
    print(dist[N - 1][M - 1])

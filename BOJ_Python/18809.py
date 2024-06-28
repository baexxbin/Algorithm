import sys
from collections import deque
from itertools import combinations

input = sys.stdin.readline

# 배양액 경우의 수는 두번의 경우의 수로 구성
# 1. 배양액 가능한 위치에 배양액 뿌릴 경우의 수
# 2. 1번의 경우의 수에서 색 지정

dy = [1, -1, 0, 0]
dx = [0, 0, 1, -1]

def bfs():
    flower = 0
    while que:
        x, y, bx, by, time, color = que.popleft()
        if visited[bx][by] == 1:  # 이전 위치에 꽃이 피었을 경우 패스
            continue
        if visited[x][y]:
            if visited[x][y] == (time, -color):
                visited[x][y] = 1
                flower += 1
            continue

        visited[x][y] = (time, color)
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < N and 0 <= ny < M and board[nx][ny]:
                if not visited[nx][ny]:
                    que.append((nx, ny, x, y, time + 1, color))
    return flower


if __name__ == '__main__':
    N, M, G, R = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(N)]

    # 배양액 뿌릴 위치 후보들을 찾음
    candidate = []
    for i in range(N):
        for j in range(M):
            if board[i][j] == 2:
                candidate.append((i, j))

    ans = 0
    # 모든 가능한 배양액 배치 경우의 수를 탐색
    for GRlst in combinations(candidate, G + R):
        for Glst in combinations(GRlst, G):
            visited = [[0] * M for _ in range(N)]
            que = deque()

            # 초록 배양액 먼저 큐에 추가
            for x, y in Glst:
                que.append((x, y, x, y, 1, 1))  # 현위치, 이전위치, 시간, 색상
                visited[x][y] = 1

            # 빨간 배양액 큐에 추가
            for x, y in GRlst:
                if not visited[x][y]:
                    que.append((x, y, x, y, 1, -1))

            # BFS 수행하여 최대 꽃의 개수를 구함
            visited = [[0] * M for _ in range(N)]
            ans = max(ans, bfs())

    print(ans)

# 단순 bfs 아님, 최단거리가 무조건 최소비용이 안됨
# 그럼 모든 경우의 수 구하고 그때 최소비용 구해야함

from collections import deque

dx = [-1, 1, 0, 0]  # 상,하,우,좌
dy = [0, 0, 1, -1]


# 상-하, 좌-우로가는 경우는 없음.. 이건 왔던길 되돌아가는거임


def bfs(board):
    n = len(board)
    visited = [[[float('inf')] * 4 for _ in range(n)] for _ in range(n)]
    que = deque()

    # (0,0) 시작 초기화
    for i in range(4):
        visited[0][0][i] = 0
        que.append((0, 0, i, 0))

    while que:
        x, y, d, cost = que.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if not (0 <= nx < n and 0 <= ny < n) or board[nx][ny] == 1:
                continue

            new_cost = cost  # cost for문도는 4번동안 사용되니까 new_cost값으로..
            if d == i:
                new_cost += 100
            else:
                new_cost += 600
            if new_cost < visited[nx][ny][i]:
                visited[nx][ny][i] = new_cost
                que.append((nx, ny, i, new_cost))
    return min(visited[n - 1][n - 1])


def solution(board):
    return bfs(board)

# dfs로 다 돌면 시간 너무오래걸림.
# bfs로 돌되 각 방향에 따른 최소값 판단하기 (visited배열 3차원)
# 큐에 넣을때는, 앞으로 갈 방향의 cost가 새로운 cost 보다 작을 경우(방문X+최소)
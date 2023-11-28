from collections import deque

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

INF = float('inf')


def solution(board):
    # 시작 위치 찾기
    N, M = len(board), len(board[0])
    que = deque()
    visited = [[INF] * M for _ in range(N)]

    for i, row in enumerate(board):
        if 'R' in row:
            j = row.find('R')
            que.append((i, j))
            visited[i][j] = 0
            break

    while que:
        x, y = que.popleft()

        if board[x][y] == 'G':          # 현재 위치가 도착지점이라면 값 반환
            print(visited[x][y])

        for k in range(4):
            nx, ny = x, y

            while 0<=nx+dx[k]<N and 0<=ny+dy[k]<M and board[nx+dx[k]][ny+dy[k]] != 'D':     # 벽이나 장애물 만날때까지 이동
                nx+=dx[k]
                ny+=dy[k]

            if visited[nx][ny] > visited[x][y]+1:       # 미끄러져 도착한 위치에 처음오거나 이전보다 빠르게 왔다면 visites갱신
                visited[nx][ny] = visited[x][y]+1
                que.append((nx, ny))                    # 해당 위치 큐 넣기

    print(-1)


board = ["...D..R", ".D.G...", "....D.D", "D....D.", "..D...."]
solution(board)

# 최소이동횟수 구하기 bfs

# 벽은 벽에 해당하는 현재 위치, 장애물은 그 전 위치를 반환해야하므로 nx+dx[i]형태로 이전위치를 얻을 수 있도록 함
import sys
from collections import deque

input = sys.stdin.readline

# bfs로 외부 공기 이동시키면서 어떤 치즈지역이 공기를 2번이상 만나면 치즈를 공기로 바꾸기

def air_bfs(i, j):
    que = deque([(i, j)])
    air[i][j] = 1

    while que:
        y, x = que.popleft()
        for k in range(4):
            ny, nx = y+dy[k], x+dx[k]
            if not (0<=ny<N and 0<=nx<M):
                continue

            if board[ny][nx]==0 and air[ny][nx]==0:
                air[ny][nx] = 1
                que.append((ny, nx))
            elif board[ny][nx]==1:          # 치즈가 공기 2번 이상 만나면 벽으로 바꾸기
                air[ny][nx] += 1
                if air[ny][nx] >=2:
                    board[ny][nx] = 0


if __name__ == '__main__':
    N, M = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(N)]

    dy = [-1, 1, 0, 0]
    dx = [0, 0, -1, 1]

    time = 0
    while True:
        air = [[0]*M for _ in range(N)]
        air_bfs(0,0)
        time+=1

        # 현재 공기 갯수 세기
        cnt = 0
        for i in range(N):
            for j in range(M):
                if board[i][j] == 0:
                    cnt+=1
        if cnt==N*M:
            break

    print(time)
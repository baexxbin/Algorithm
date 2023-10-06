import sys
from collections import deque

input = sys.stdin.readline


# 13:09 - 14:29

# 치즈 가장자리 구하기
# 치즈 구멍과 맞댄 가장자리 구하기
# 외부공기 -1, 치즈1, 구멍0으로 초기화
# 치즈를 돌면서 주위가 외부공기와 2개이상 만나면 가장자리로 체크
# 가장 자길 모두 체크한 후, 가장자리 녹음(구멍 공기도 해결)

def bfs(i, j):
    que = deque()
    que.append((i, j))
    board[i][j] = -1

    while que:
        x, y = que.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if not (0 <= nx < N and 0 <= ny < M):
                continue
            if board[nx][ny] == 0:
                board[nx][ny] = -1
                que.append((nx, ny))


def checkCheese(i, j):
    que = deque()
    que.append((i, j))
    visited[i][j] = True
    cnt = 1

    while que:
        x, y = que.popleft()
        tmp = 0
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if not (0 <= nx < N and 0 <= ny < M):
                continue
            if board[nx][ny] == -1:
                tmp += 1
            elif board[nx][ny] == 1 and not visited[nx][ny]:
                que.append((nx, ny))
                visited[nx][ny] = True
                cnt += 1

        if tmp >= 1:
            edges.append((x, y))

    return cnt


if __name__ == '__main__':
    N, M = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(N)]

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    # 외부공기(-1) 초기화
    bfs(0, 0)
    ans = 0
    time = 0
    while True:
        # 치즈돌면서 가장자리 체크
        visited = [[False] * M for _ in range(N)]
        edges = deque()
        cheese = 0
        for i in range(N):
            for j in range(M):
                if board[i][j] == 1 and not visited[i][j]:
                    cheese += checkCheese(i, j)

        # 가장자리 녹이기
        while edges:
            x, y = edges.popleft()
            board[x][y] = -1

        # 공기만난 구멍 공기로 바꾸기
        for i in range(N):
            for j in range(M):
                if board[i][j]==0:
                    for k in range(4):
                        ni, nj = i+dx[k], j+dy[k]
                        if board[ni][nj]==-1:
                            bfs(i, j)
                            break


        if cheese == 0:
            break
        else:
            ans = cheese
        time += 1

    print(time)
    print(ans)

# 공기만난 구멍을 바꾸는 코드가 깔끔하지 않아서 마음에 안들었는데, bfs를 한번만 사용하는 코드로 리펙토링 가능
# 현재 치즈 가장자리를 체크하면서 치즈 수 세는 bfs, 공기를 -1로 초기화하는 bfs두개를 사용했음

# 공기인 (0,0)에서 시작하는 bfs를 돌려 공기(0)이면 큐에 넣으며 계속 진행하고,
# 치즈(1)일경우, 공기 탐색 중 만난 치즈로 가장자리가 되며 cheese변수에 없어질 가장자리를 삽입한다
# main에서는 종료시까지 앞서만든 bfs를 반복 실행하고, 종료 시 cheese[-2]출력
# cheese[-2] : 마지막 전의 없어진 가장자리 값 >> 모두 없어지기 전 치즈개수

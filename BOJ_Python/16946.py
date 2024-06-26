import sys
from collections import deque

input = sys.stdin.readline

def bfs(i, j, idx):
    que = deque()
    que.append((i, j))
    visited[i][j] = idx

    cnt = 1
    while que:
        x, y = que.popleft()
        for k in range(4):
            nx, ny = x+dx[k], y+dy[k]
            if not(0<=nx<N and 0<=ny<M):
                continue
            if board[nx][ny]==0 and visited[nx][ny]==0:
                que.append((nx, ny))
                visited[nx][ny] = idx
                cnt+=1
    return cnt


def cntZero(x, y):
    cnt = 1     # 자기 자신 위치 값
    group = set()
    for i in range(4):      # 상하좌우의 0그룹 값
        nx, ny = x+dx[i], y+dy[i]
        if not (0 <= nx < N and 0 <= ny < M):
            continue
        if visited[nx][ny] > 0:     # 0그룹일경우 set으로 추가(중복X)
            group.add(visited[nx][ny])

    for idx in group:       # 0그룹의 0갯수 더하기
        cnt += info[idx]

    return cnt%10


if __name__ == '__main__':
    N, M = map(int, input().split())
    board = [list(map(int, input().strip())) for _ in range(N)]
    visited = [[0] * M for _ in range(N)]   # 0그룹 표시
    ans = [[0]*M for _ in range(N)]

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    # 각 0그룹의 0갯수 구하기
    idx = 1
    info = {}
    for i in range(N):
        for j in range(M):
            if board[i][j] == 0 and visited[i][j] == 0:
                info[idx] = bfs(i, j, idx)
                idx+=1

    # 1에 인접한 그룹들의 0값 더해주기
    for i in range(N):
        for j in range(M):
            if board[i][j]==1:
                ans[i][j] = cntZero(i, j)

    # 문자열로 출력
    for row in ans:
        print("".join(map(str, row)))
import sys
from collections import deque

input = sys.stdin.readline


def bfs():
    que = deque()
    que.append((0,0,0))
    check[0][0][0] = 1

    while que:
        x, y, z = que.popleft()

        if x==N-1 and y==M-1:
            return check[x][y][z]

        for i in range(4):
            nx, ny = x+dx[i], y+dy[i]
            if 0<=nx<N and 0<=ny<M:
                if matrix[nx][ny]==0 and check[nx][ny][z]==0:   # 벽 안뚫고 감
                    check[nx][ny][z] = check[x][y][z]+1
                    que.append((nx, ny, z))
                elif matrix[nx][ny]==1 and z < K and check[nx][ny][z+1]==0:   # 벽 뚫고 감
                    check[nx][ny][z+1] = check[x][y][z]+1
                    que.append((nx,ny,z+1))
    return -1


if __name__ == '__main__':
    N, M, K = map(int, input().split())
    matrix = [list(map(int, input().strip())) for _ in range(N)]
    check = [[[0]*(K+1) for _ in range(M)] for _ in range(N)]

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    print(bfs())


# 벽 뚫고 갈 때 방문처리 잊지말기
# 조건
# 1) 벽
# 2) 뿌신 벽의 수가 K보다 작아야함
# 3)check[nx][ny][z+1]==0, 이전에 간 곳 아님!
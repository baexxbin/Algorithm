import sys
from collections import deque
from itertools import combinations

input = sys.stdin.readline
MAX_VAL = float('inf')

# 바이러스 퍼트리기 (bfs)
def bfs(startVirus, space):
    que = deque()

    visited = [[-1] * N for _ in range(N)]
    for v in startVirus:
        visited[v[0]][v[1]] = 0
        que.append(v)

    while que:
        x, y = que.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < N and 0 <= ny < N:
                if board[nx][ny] == 0 and visited[nx][ny] == -1:
                    que.append((nx, ny))
                    visited[nx][ny] = visited[x][y] + 1
                    space -= 1

    return visited[x][y] if space == 0 else MAX_VAL  # 바이러스 다 퍼지는데 총 걸린시간은 가장 마지막에 채워진 칸의 visited 값


if __name__ == '__main__':
    N, M = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(N)]

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]


    # 바이러스 놓을 수 있는 위치, 채워야하는 공간 저장
    virusIdx = []
    empty = 0
    for i in range(N):
        for j in range(N):
            if board[i][j] == 0:
                empty += 1
            elif board[i][j] == 2:
                virusIdx.append((i, j))
                empty += 1
                board[i][j] = 0

    # 처음 바이러스 놓는 공간 empty에서 제거하기
    empty -= M

    ans = MAX_VAL

    for virus in combinations(virusIdx, M):     # 바이러스 놓을 수 있는 조합
        ans = min(ans, bfs(virus, empty))

    print(-1) if ans==MAX_VAL else print(ans)
    # -1와 최소값이 같이 나오는 경우, -1이 아닌 최소값을 출력해야함
    # bfs에서는 -1를 MAX_VAL로 반환하고 마지막 최종 ans에서도 값이 MAX_VAL이라면 그때 -1 반환
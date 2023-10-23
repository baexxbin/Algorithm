import sys
from collections import deque

input = sys.stdin.readline
MAX = float('inf')


# 최단 경로 구하기(bfs)
# 벽 뿌시기 : k만큼 벽 생성

if __name__ == '__main__':
    N, M, K = map(int, input().split())
    board = [list(map(int, input().strip())) for _ in range(N)]

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    visited = [[[MAX] * (K + 1) for _ in range(M)] for _ in range(N)]

    que = deque([(0, 0, 1, K)])  # x, y, 시간, K
    visited[0][0][K] = 0  # 0,0에 뿌실 수있는 K채운 후 시작
    res = MAX

    while que:
        x, y, t, k = que.popleft()

        if (x, y) == (N - 1, M - 1):
            res = min(res, t)
            continue

        day = t % 2
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < N and 0 <= ny < M:
                # 0으로 갈 수 있음 (벽 안뿌심)
                if board[nx][ny] == 0 and visited[nx][ny][k] > t:
                    visited[nx][ny][k] = t
                    que.append((nx, ny, t + 1, k))

                # 벽뿌실 수 있음 + visited 0인 상태
                if board[nx][ny] == 1 and k and visited[nx][ny][k - 1] > t:
                    if day:  # 낮이라서 뿌시고 바로감
                        visited[nx][ny][k - 1] = t
                        que.append((nx, ny, t + 1, k - 1))
                    else:  # 밤이라서 하루 기다렸다가 뿌시고 감 (나중에 큐에서 나왔을때 뿌심)
                        que.append((x, y, t + 1, k))

    print(res) if res < MAX else print(-1)

# 초기 구현이랑 다른 점
# 벽 뿌수고싶은데 밤인경우
#   >> 초기엔 큐에 넘길때 이미 벽을 뿌신걸로 표현했는데, 벽 안뿌시고 시간만 업데이트하고 큐 넘김
# 밤,낮 처리와 시간처리
#   >> 초기: 밤, 낮처리를 큐에 넣어서 처리를 하고 visited를 시간으로 처리함
#   >> 수정: day는 큐가 아닌 공통으로 처리 그리고 시간t기반으로 처리, 시간은 t로 큐에 넣을때마다 증가해주기
#   * 큐마다 가지고 있는 시간이 다다르다, 같은위치여도 벽뿌신 유무에 따라 시간이 다를 수 있음 >> 목표점에 먼저 도달했다고 답이 아님 >> 큐에선 먼저 안나올 수 있지만 더 짧은 시간 가질 수 있음
#   * 벽부실 수 있는 갯수k, visited초기화는 큰 값에서 줄여나가는 방향으로 수정

# bfs함수로 분리해서 작성하면 시간초과남
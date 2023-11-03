import sys
from collections import deque, defaultdict

input = sys.stdin.readline
#
# # 14:55 -
# def bfs(cow1, cow2):
#     board = copy.deepcopy(farm)
#     que = deque()
#     que.append(cow1)
#
#     board[cow1[0]][cow1[1]] = 0
#
#     while que:
#         x, y = que.popleft()
#
#         if x==cow2[0] and y==cow2[1]:
#             return False
#
#         for i in range(4):
#             nx, ny = x+dx[i], y+dy[i]
#             if not(0<=nx<N and 0<=ny<N):
#                 continue
#             if board[nx][ny]==-1 and not (nx,ny) in road[(x,y)]:
#                 que.append((nx,ny))
#                 board[nx][ny] = 0
#     return True
#
#
# if __name__ == '__main__':
#     N, K, R = map(int, input().split())
#
#     dx = [-1, 1, 0, 0]
#     dy = [0, 0, -1, 1]
#     farm = [[-1]*N for _ in range(N)]
#     road = defaultdict(list)
#
#     for _ in range(R):
#         x1, y1, x2, y2 = map(int, input().split())
#         road[(x1-1,y1-1)].append((x2-1, y2-1))
#         road[(x2-1,y2-1)].append((x1-1, y1-1))
#
#     cows = []
#     for _ in range(K):
#         x, y = map(int, input().split())
#         cows.append((x-1, y-1))
#
#     cnt = 0
#     for i in range(K):
#         for j in range(i+1, K):
#             if bfs(cows[i], cows[j]):
#                 cnt+=1
#
#     print(cnt)


def bfs(cow, idx):
    global res
    visited = [[0]*N for _ in range(N)]
    no_road = [[0]*N for _ in range(N)]
    que = deque()
    que.append(cow)
    visited[cow[0]][cow[1]] = 1

    while que:
        x, y = que.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if not(0<=nx<N and 0<=ny<N):
                continue
            if visited[nx][ny]==0 and not (nx,ny) in road[(x,y)]:
                if board[nx][ny] == 1:      # 다른 소 만남
                    no_road[nx][ny] = 1     # 길로 안갔을때 다른 소 만났다고 표시

                que.append((nx,ny))
                visited[nx][ny] = 1

    for i in range(idx+1, K):       # 현재 소 말고 다른 소 위치 확인하면서
        cx, cy = cows[i]
        if not no_road[cx][cy]:     # 길로 안갔을때 다른소 못만났으면 res++
            res+=1



if __name__ == '__main__':
    N, K, R = map(int, input().split())

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    board = [[0] * N for _ in range(N)]
    road = defaultdict(list)

    for _ in range(R):
        x1, y1, x2, y2 = map(int, input().split())
        road[(x1-1,y1-1)].append((x2-1, y2-1))
        road[(x2-1,y2-1)].append((x1-1, y1-1))

    cows = []
    for _ in range(K):
        x, y = map(int, input().split())
        board[x-1][y-1] = 1
        cows.append((x-1, y-1))

    res = 0
    for idx, cow in enumerate(cows):
        bfs(cow, idx)

    print(res)


# 초기 구현한 주석 친 코드는 pypy로 돌리면 통과함
# 두번째 코드는 소한마리 당  bfs를 한 번 진행해서(길로 안가는 방법으로), 그때 다른 소들을 만났는지 안만났는지 체크하는 반면
# 첫번째 코드는 (소1, 소2) 모든 조합에 대해 bfs를 진행하므로 시간이 오래걸리고 중복발생 (소1 bfs한번만 돌려도 다른 소들 체크할 수 있는데, 짝지은 소마다 bfs다 돌림)
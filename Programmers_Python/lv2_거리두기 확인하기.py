from collections import deque

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def bfs(board, i, j):
    que = deque()
    que.append((i,j))
    visited = [[-1]*5 for _ in range(5)]
    visited[i][j] = 0

    while que:
        x, y = que.popleft()
        for k in range(4):
            nx, ny = x+dx[k], y+dy[k]
            if not(0<=nx<5 and 0<=ny<5) or visited[nx][ny] != -1:
                continue
            if board[nx][ny]=='O':
                que.append((nx, ny))
                visited[nx][ny] = visited[x][y]+1
            elif board[nx][ny]=='P' and visited[x][y] <= 1:
                return False
    return True


def solution(places):

    res = []
    for room in places:
        # P사람들 위치 파악
        people = []
        for i in range(5):
            for j in range(5):
                if room[i][j]=='P':
                    people.append((i,j))

        flag = True
        for x,y in people:
            if not bfs(room,x,y):
                flag = False
                break
        res.append(1) if flag else res.append(0)

    print(res)


places = [["POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"], ["POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"], ["PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"], ["OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"], ["PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"]]
solution(places)

# 맨하튼 거리는 bfs에서 거리측정하는 내용+1
# P만났을때 맨하튼 거리 조건이 만족해도 더 이상 탐색하지 않음
    # 해당 P에서 이어 탐색하는 것은 나중에 해당P 차례에서 진행
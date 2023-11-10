from collections import deque

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
def bfs(i, j, maps, target):
    que = deque()
    que.append((i, j))
    visited = [[-1] * len(maps[0]) for _ in range(len(maps))]
    visited[i][j] = 0
    N, M = len(maps), len(maps[0])

    while que:
        x, y = que.popleft()
        for k in range(4):
            nx, ny = x+dx[k], y+dy[k]
            if not(0<=nx<N and 0<=ny<M):
                continue
            if maps[nx][ny]==target and visited[nx][ny]==-1:
                return nx, ny, visited[x][y]+1
            if maps[nx][ny]!='X' and visited[nx][ny]==-1:
                que.append((nx, ny))
                visited[nx][ny] = visited[x][y]+1
    return i, j, -1




def solution(maps):
    # S찾기
    start = [0,0]
    for i, val in enumerate(maps):
        if val.find('S')!=-1:
            start[0], start[1] = i, val.find('S')
            break

    lx, ly, cnt = bfs(start[0], start[1], maps, 'L')
    if cnt ==-1:
        return cnt
    ex, ey, cnt2 = bfs(lx,ly, maps, 'E')
    print(cnt2) if cnt2==-1 else print(cnt+cnt2)



maps = ["LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"]
solution(maps)
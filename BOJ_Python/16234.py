import sys
from collections import deque

input = sys.stdin.readline


def bfs(i, j):
    que = deque([(i, j)])
    tmp = [(i,j)]
    visited[i][j] = True

    # bfs로 열어야할 국경선 찾기
    while que:
        x, y = que.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < n and 0 <= ny < n and not visited[nx][ny]:
                if L <= abs(land[x][y] - land[nx][ny]) <= R:
                    que.append((nx, ny))
                    tmp.append((nx, ny))
                    visited[nx][ny] = True

    print(visited)
    return tmp


def movePeople(peoples):
    for people in peoples:
        tmp = sum(land[x][y] for x, y in people) // len(people)
        for x, y in people:
            land[x][y] = tmp


if __name__ == '__main__':
    n, L, R = map(int, input().split())
    land = [list(map(int, input().split())) for _ in range(n)]

    dx = [1, -1, 0, 0]
    dy = [0, 0, -1, 1]
    ans = 0

    while True:
        visited = [[False] * n for _ in range(n)]
        peoples = []    # 하루동안 일어날 인구이동 국가모임
        for i in range(n):
            for j in range(n):
                if not visited[i][j]:
                    people = bfs(i, j)
                    print(people)
                    if len(people) > 1:  # 연합이 형성되는 경우만 추가, 길이가 1인 경우 bfs를 돌린 자기자신만을 의미
                        peoples.append(people)
        if not peoples:
            break

        ans += 1
        movePeople(peoples)     # 인구 이동

    print(ans)

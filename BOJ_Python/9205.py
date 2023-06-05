import sys
from collections import deque

input = sys.stdin.readline

def bfs():
    que = deque()
    que.append((home[0], home[1]))

    while que:
        x, y = que.popleft()

        # 현재위치에서 페스티벌 직통으로 갈 수 있는지 확인
        if abs(x-festival[0]) + abs(y-festival[1]) <= 1000:
            print("happy")
            return

        # 현재 위치에서 갈 수 있는 편의점 체크
        # 거리가 1000이하라 갈 수 있으면 가고, 큐에 넣어서 현재위치(x,y)업데이트 됨
        for i in range(n):
            if not visited[i]:
                nx, ny = stores[i]
                if abs(x-nx) + abs(y-ny) <= 1000:
                    visited[i] = True
                    que.append((nx, ny))
    print("sad")
    return


if __name__ == '__main__':
    t = int(input())

    for _ in range(t):
        n = int(input())
        home = list(map(int, input().split()))
        stores = []
        for _ in range(n):
            x, y = map(int, input().split())
            stores.append((x, y))
        festival = list(map(int, input().split()))
        visited = [False]*n
        bfs()
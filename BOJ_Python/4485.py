import heapq
import sys

input = sys.stdin.readline


def dijkstra():
    que = []
    heapq.heappush(que, (matrix[0][0], 0, 0))
    distance[0][0] = 0

    while que:
        cost, x, y = heapq.heappop(que)

        if x==N-1 and y==N-1:
            print(f'Problem {idx}: {cost}')
            break

        for i in range(4):
            nx, ny = x+dx[i], y+dy[i]
            if 0<=nx<N and 0<=ny<N:
                newCost = matrix[nx][ny]+cost
                if newCost < distance[nx][ny]:
                    distance[nx][ny] = newCost
                    heapq.heappush(que, (newCost, nx, ny))


if __name__ == '__main__':
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    INF = float('inf')

    idx = 1
    while True:
        N = int(input())
        if N == 0:
            break

        matrix = [list(map(int, input().split())) for _ in range(N)]
        distance = [[INF] * N for _ in range(N)]
        dijkstra()
        idx += 1
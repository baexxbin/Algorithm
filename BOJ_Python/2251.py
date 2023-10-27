import sys
from collections import deque

input = sys.stdin.readline

# 각 물통에 담긴 상황에 따라 경우의 수 구하기
# bfs로 부울 수 있는 물의 경우 붓기

def pour(x,y):
    if not visited[x][y]:
        visited[x][y] = True
        que.append((x, y))


def bfs():

    while que:
        x, y = que.popleft()    # x:A물통 양, y:B물통 양, z:C물통 양
        z = C-x-y

        if x==0:
            ans.append(z)

        # x->y
        water = min(x, B-y)         # A를 비우거나 B를 꽉채워야함(이때 물이 남거나 넘치지 않도록 가능한 수)
        pour(x-water, y+water)

        # x->z
        water = min(x, C-z)
        pour(x-water, y)            # pour에는 x,y값을 넘겨줌(z값X)

        # y->x
        water = min(y, A - x)
        pour(x + water, y - water)

        # y->z
        water = min(y, C - z)
        pour(x, y - water)

        # z->x
        water = min(z, A-x)
        pour(x+water, y)

        # z->y
        water = min(z, B-y)
        pour(x, y+water)


if __name__ == '__main__':
    A, B, C = map(int, input().split())

    visited = [[False]*(B+1) for _ in range(A+1)]
    que = deque()
    que.append((0, 0))
    visited[0][0] = True
    ans = []

    bfs()

    ans.sort()
    for i in ans:
        print(i, end=' ')

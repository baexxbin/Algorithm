import sys
from collections import deque

input = sys.stdin.readline


if __name__ == '__main__':
    A, B, C = map(int, input().split())
    TOTAL = A+B+C

    if TOTAL%3!=0:
        print(0)
        exit()

    visited = [[False]*TOTAL for _ in range(TOTAL)]

    que = deque()
    que.append((A,B))
    visited[A][B] = True

    while que:
        a, b = que.popleft()
        c = TOTAL-a-b
        if a==b==c:
            print(1)
            exit()

        for X,Y in [(a,b), (a,c), (b,c)]:
            if X==Y:        # 위에서 a==b==c같은지 체크함, 이경우는 하나만 값이 다른 경우, 따라서 중복 값 피하기 위함
                continue
            if X > Y:
                X, Y = Y, X
            X, Y = X+X, Y-X
            mn = min(X, Y, TOTAL-(X+Y))
            mx = max(X,Y, TOTAL-(X+Y))
            if visited[mn][mx]:
                continue
            que.append((mn,mx))
            visited[mn][mx] = True
    print(0)

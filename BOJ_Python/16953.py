import sys
from collections import deque

input = sys.stdin.readline


def bfs(a, b):
    que = deque()
    que.append((a, 1))
    visited.append(a)

    while que:
        x, cnt = que.popleft()
        if x == b:
            return cnt
        for i in range(2):
            nx = x * 2 if i == 0 else x * 10 + 1
            if nx in visited or nx > b:
                continue
            que.append((nx, cnt + 1))   # 현재 값에 대한 cnt 같이 추가
    return -1


if __name__ == '__main__':
    a, b = map(int, input().split())
    visited = []
    cnt = 0

    print(bfs(a, b))
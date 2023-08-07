import sys
from collections import deque

input = sys.stdin.readline


def move(cur):
    ans = []
    tmp = cur
    for _ in range(visited[cur] + 1):
        ans.append(tmp)
        tmp = check[tmp]
    print(' '.join(map(str, ans[::-1])))


def bfs(x):
    que = deque()
    que.append(x)

    while que:
        cx = que.popleft()
        if cx == K:
            print(visited[cx])
            move(cx)
            return

        for nx in (cx + 1, cx - 1, cx * 2):
            if 0 <= nx <= 100000 and visited[nx] == 0:
                visited[nx] = visited[cx] + 1
                check[nx] = cx  # 현재노드의 부모 노드 체크
                que.append(nx)


if __name__ == '__main__':
    N, K = map(int, input().split())

    visited = [0] * 100001
    check = [0] * 100001

    bfs(N)

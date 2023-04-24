import sys
from collections import deque, defaultdict

input = sys.stdin.readline

# bfs는 간선의 가중치가 동일해야 사용가능
# 0-1 bfs 사용 : 가중치간 0인 정점은 큐의 맨 앞에 넣음

def bfs(n):
    que = deque()
    que.append(n)
    visited[n] = 0

    while visited[k] == -1:
        x = que.popleft()
        if x == k:
            return

        for i in range(3):
            nx = x + dx[i] if i != 0 else x * dx[i]

            if 0 <= nx <= 100000 and visited[nx] == -1:
                if i == 0:                      # dx살펴볼때 순간이동(2)를 제일먼저 살핌 (while문 조건으로 인해 같은 순서일때 큐 순서 반영안됨)
                    visited[nx] = visited[x]
                    que.appendleft(nx)          # 가중치(0)이므로 큐의 맨 앞에 넣음
                    continue

                visited[nx] = visited[x] + 1
                que.append(nx)                  # 가중치(1)은 큐 뒤에 넣음


if __name__ == '__main__':
    n, k = map(int, input().split())
    visited = [-1]*100001
    dx = [2, -1, 1]
    bfs(n)

    print(visited[k])

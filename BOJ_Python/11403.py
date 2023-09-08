import sys
from collections import deque

input = sys.stdin.readline

# 경로 찾기

# 풀이 설계
# 주어진 adj값은 그래프의 인접행렬로, 이를 통해 방향 그래프 만들기
# 각 번호별 이어진 정점으로 가는 길 체크

def bfs(node):
    que = deque()
    que.append(node)
    visited = [0]*N
    # visited[node] = 1     # 연결된 정점을 체크하는 것이므로 처음에 자기자신 체크안하기!

    while que:
        cur = que.popleft()
        for nxt in range(N):
            if adj[cur][nxt]==1 and visited[nxt]==0:
                que.append(nxt)
                visited[nxt] = 1
    return visited


if __name__ == '__main__':
    N = int(input())
    adj = [list(map(int, input().split())) for _ in range(N)]

    ans = []
    for i in range(N):
        ans.append(bfs(i))

    for row in ans:
        print(' '.join(map(str, row)))
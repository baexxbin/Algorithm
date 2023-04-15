import sys
from collections import defaultdict, deque

input = sys.stdin.readline

# 위상정렬
if __name__ == '__main__':
    ingress = defaultdict(int)      # 자신에게 들어오는 간선의 수
    adj = defaultdict(list)
    n, m = map(int, input().split())

    for _ in range(m):
        u, v = map(int, input().split())
        adj[u].append(v)             # 그래프 만들기
        ingress[v] += 1              # 간선 업데이트

    que = deque()
    for i in range(1, 1+n):          # 간선이 0인 제일 앞에 올 정점 넣어 que초기화
        if ingress[i]==0:
            que.append(i)

    while que:
        cur = que.popleft()
        print(cur, end=' ')

        for i in adj[cur]:           # 출력한 정점에 연결된 정점의 ingress 줄이기
            ingress[i] -= 1
            if ingress[i] ==0:       # 간선==0인 정점 생기면 que에 넣기
                que.append(i)
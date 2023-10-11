from collections import deque


def bfs(node):
    global adj

    que = deque()
    que.append(node)
    visited = [False] * (n + 1)
    visited[node] = True

    cnt = 1
    while que:
        cur = que.popleft()
        for nxt in adj[cur]:
            if not visited[nxt]:
                que.append(nxt)
                visited[nxt] = True
                cnt += 1

    return cnt


def solution(n, wires):
    global adj
    # 그래프 형태 생성
    adj = [[] for _ in range(n + 1)]
    for u, v in wires:
        adj[u].append(v)
        adj[v].append(u)

    # wires돌면서 연결끊고, 그때 차이 계산하기
    ans = []
    for u, v in wires:
        adj[u].remove(v)  # 간선 끊기 위해 임시 제거
        adj[v].remove(u)

        cnt_u = bfs(u)

        adj[u].append(v)  # 임시 간선 다시 넣기
        adj[v].append(u)

        ans.append(abs((n - cnt_u) - cnt_u))

    print(min(ans))


n = 7
wires = [[1,2],[2,7],[3,7],[3,4],[4,5],[6,7]]
solution(n, wires)

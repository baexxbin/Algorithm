from collections import defaultdict


# 주어진 티켓(경로) 모두 이용하기, 알파벳 빠른 순으로
# u: [v, False] 형태로 저장, 알파벳 순 정렬

def dfs(cur, adj, path, cnt):

    if len(path) == cnt + 1:    # 종료 조건
        return path

    for i in range(len(adj[cur])):
        nxt, visited = adj[cur][i]
        if not visited:
            adj[cur][i][1] = True
            ret = dfs(nxt, adj, path+[nxt], cnt)
            if ret:
                return ret
            adj[cur][i][1] = False      # 유효하지 않은 탐색이라면 백트래킹
    return None


def solution(tickets):
    adj = defaultdict(list)
    for v, u in tickets:
        adj[v].append([u, False])

    # 키에 대한 값 알파벳 순 정렬
    for i in adj:
        adj[i].sort(key=lambda x: x[0])

    path = ['ICN']
    cnt = len(tickets)
    return dfs('ICN', adj, path, cnt)


if __name__ == '__main__':
    tickets = [["ICN", "SFO"], ["ICN", "ATL"], ["SFO", "ATL"], ["ATL", "ICN"], ["ATL", "SFO"]]
    print(solution(tickets))

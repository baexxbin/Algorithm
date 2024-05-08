import sys
sys.setrecursionlimit(10**9)

input = sys.stdin.readline

def dp_solve(num):
    visited[num] = True
    dp[num][0] = 0          # 리프까지 내려갔을때 각각 얼리어답터일때 아닐때로 설정, 루트노드 초기값
    dp[num][1] = 1

    for i in graph[num]:    # 자신의 자식노드들을 돌면서 최적의 값 구하기
        if not visited[i]:
            dp_solve(i)
            dp[num][0] += dp[i][1]
            dp[num][1] += min(dp[i][0], dp[i][1])


if __name__ == '__main__':
    N = int(input())
    graph = [[] for _ in range(N+1)]
    visited = [False]*(N+1)

    for _ in range(N-1):
        u, v = map(int, input().split())
        graph[u].append(v)
        graph[v].append(u)

    # [정점 번호][얼리 어답터 여부]
    dp = [[0, 0] for _ in range(N+1)]
    dp_solve(1)
    print(min(dp[1][0], dp[1][1]))


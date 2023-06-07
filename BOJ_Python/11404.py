import sys

input = sys.stdin.readline

if __name__ == '__main__':
    n = int(input())
    m = int(input())

    # adj 값 초기화
    adj = [[float('INF')]*(n+1) for _ in range(n+1)]
    for i in range(m):
        a, b, c = map(int, input().split())
        adj[a][b] = min(adj[a][b], c)

    # 플로이드-와샬 알고리즘
    for k in range(1, n+1):
        for i in range(1, n+1):
            for j in range(1, n+1):
                if i==j:
                    adj[i][j] = 0
                    continue
                adj[i][j] = min(adj[i][j], adj[i][k]+adj[k][j])

    # 출력
    for row in adj[1:]:
        for col in row[1:]:
            if col == float('INF'):
                print(0, end=' ')
                continue
            print(col, end=' ')
        print()
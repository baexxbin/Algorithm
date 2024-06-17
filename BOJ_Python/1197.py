import sys

input = sys.stdin.readline


def find(x):
    if x != parents[x]:
        parents[x] = find(parents[x])
    return parents[x]


def union(x, y):
    if x > y:   # 랭크가 큰 트리에 작은 트리 붙이기
        parents[y] = x
    else:
        parents[x] = y


if __name__ == '__main__':
    V, E = map(int, input().split())
    edges = []

    for _ in range(E):
        u, v, cost = map(int, input().split())
        edges.append((cost, u, v))

    edges.sort()

    parents = [i for i in range(V+1)]
    rank = [0 for _ in range(V+1)]

    ans = 0
    for cost, u, v in edges:
        p_u = find(u)
        p_v = find(v)
        if p_u!=p_v:
            union(p_u, p_v)
            ans+=cost

    print(ans)

# 크루스칼 경로 압축: find할때 노드들의 부모를 루트로 갱신 시켜주는 것
# 크루스칼 랭크: 랭크(트리 높이 관련 정보), 탐색 효율 높이기 위해 트리 높이 작게 유지
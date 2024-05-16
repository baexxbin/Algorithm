import sys

input = sys.stdin.readline


def find(x):
    if x != parent[x]:
        parent[x] = find(parent[x])
    return parent[x]


def union(x, y):
    if x < y:
        parent[y] = x
    else:
        parent[x] = y


if __name__ == '__main__':
    N = int(input())
    road_lst = list()   # (비용, 논1, 논2)

    for i in range(N):
        wc = int(input())
        road_lst.append((wc, i+1, 0))   # 우물 뚫는것(수원)도 0번 인덱스로 하나의 논으로 치기 >> road_lst 전체로 크루스칼

    for i in range(N):
        rc = list(map(int, input().split()))
        for j in range(i+1, N):
            road_lst.append((rc[j], i+1, j+1))

    road_lst.sort()
    parent = list(range(N+1))

    ans = 0
    cnt = N                     # 아직 연결되지 않은 논 개수
    for cost, a, b in road_lst:
        if not cnt:             # 모든 논 연결되었을 경우 종료
            break
        pa = find(a)
        pb = find(b)
        if pa != pb:
            cnt-=1
            union(pa, pb)
            ans+=cost
    print(ans)

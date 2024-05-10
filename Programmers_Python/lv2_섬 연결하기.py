def find(x, parent):
    if x != parent[x]:
        parent[x] = find(parent[x], parent)
    return parent[x]

def union(x, y, parent):
    rootX = find(x, parent)
    rootY = find(y, parent)
    if rootX != rootY:
        parent[rootY] = rootX

def findParent(u,v, parent):        # 최상위 루트부모 찾아야함
    rootU = find(u, parent)
    rootV = find(v, parent)
    if rootU==rootV:
        return True
    return False

# find를 거치며 재귀를 거쳐간 원소들의 parent값이 루트값으로 업데이트 됨. >> 그래서 더 효율적으로 루트부모를 찾을 수 있는 것.
# 그런데 바로 부모값끼리 비교하지 않고 findParent를 이용하는 경우는, parent배열의 값이 항상 루트 부모를 가르킨다고 확신할 수 없기때문임

def solution(n, costs):
    costs.sort(key=lambda x: x[2])
    parent = [i for i in range(n)]

    ans = 0
    for u,v,c in costs:
        if not findParent(u, v, parent):
            union(u, v, parent)
            ans+=c
    return ans

# def solution(n, costs):
#     costs.sort(key=lambda x: x[2])
#     # parent =[i for i in range(n)]
#
#     group = set(costs[0][0])
#     ans = 0
#     while len(group) != n:
#         for c in costs:
#             if c[0] in group and c[1] in group:
#                 continue
#             if c[0] in group or c[1] in group:
#                 group.update([c[0], c[1]])
#                 ans += c[2]
#                 break


if __name__ == '__main__':
    n = 4
    costs = [[0,1,1],[0,2,2],[1,2,5],[1,3,1],[2,3,8]]
    print(solution(n, costs))
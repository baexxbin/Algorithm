# 각 그래프를 판단하기 위해 나가는 간선, 들어오는 간선 파악
# 생성한 정점 = in:0, out 2이상
# 막대 그래프(마지막 노드) = in:1, out:0
# 8자 그래프(중간 노드) = in:2, out:2
# 도넛 그래프: 나머지 그래프 뺀 수
from collections import defaultdict


def solution(edges):
    ans = [0,0,0,0]
    dic = defaultdict(lambda: [0, 0])

    for u, v in edges:
        dic[u][0] += 1  # u에서 나가는 out
        dic[v][1] += 1  # v로 들어오는 in

    for node, edge in dic.items():
        if edge[0] >=2 and edge[1] ==0:
            ans[0]=node
        elif edge[0] >=2 and edge[1] >=2:
            ans[3]+=1
        elif edge[0]==0 and edge[1] >= 1:
            ans[2]+=1
    total = dic[ans[0]][0]
    ans[1] = total - ans[2] - ans[3]
    return ans


if __name__ == '__main__':
    edges = [[2, 3], [4, 3], [1, 1], [2, 1]]
    print(solution(edges))
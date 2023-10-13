import sys

input = sys.stdin.readline

def dfs(target):
    parents[target] = -2        # 삭제하는 노드 -2로 변경
    for node in range(N):
        if parents[node]==target:       # 부모노드가 target인 노드 삭제(=target의 자식노드 재귀로 삭제)
            dfs(node)


if __name__ == '__main__':
    N = int(input())
    parents = list(map(int, input().split()))
    target = int(input())

    dfs(target)

    cnt = 0
    for node in range(N):
        if parents[node] != -2 and node not in parents:
            cnt+=1
    print(cnt)




# 처음에 생각한거에 비해 굉장히 쉽게 풀리는 문제였다
# 주어진 부모노드를 기반으로 트리를 구성하려고했는데, 굳이 트리를 만들지 않고 부모노드 정보로만 문제를 해결할 수 있었다.

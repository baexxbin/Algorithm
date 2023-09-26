import sys
sys.setrecursionlimit(10**6)

input = sys.stdin.readline

# 11:20 - 11:45 +a(오류해결)

def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])
    return parent[x]

def union(a, b):
    a = find(a)
    b = find(b)

    if a < b:
        parent[b] = a
    else:
        parent[a] = b


if __name__ == '__main__':
    n, m = map(int, input().split())
    parent = [i for i in range(n+1)]

    for _ in range(m):
        cm, a, b = map(int, input().split())

        if cm:
            print('YES') if find(a)==find(b) else print('NO')
            continue

        union(a, b)


# ** 경로 압축 **

# (기존코드)
# def find(x):
#     if parent[x] != x:
#         return find(parent[x])
#     return x

# >> x의 부모에 해당하는 값의 부모를 계속 찾아나간다
# 선형적으로 연결되기에(편중현상) O(n)이 걸린다.


# (경로 압축한 코드)

# def find(x):
#     if parent[x] != x:
#         return parent[x] = find(parent[x])
#     return x
# Find()를 호출하면서 루트값으로 바꾸기에 더 효율적으로 찾을 수 있다
# 트리형태가 만들어짐으로 O(logN)
# *주의* 파이썬에서는 return에 대입연산자=를 사용할 수 없으므로 위


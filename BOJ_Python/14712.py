import sys

input = sys.stdin.readline


# 넴모 하나씩 놓아보면서 백트래킹 (2*2 안되게 만들어가기)
# 좌표를 이동하면서 넴모 놓았을때(놓은 수 있을 경우), 안놓았을때 dfs 진행
# 좌표는 1-base로 시작, +1로 추가된 행과 열은 네모 판의 크기를 확장한 것이 아닌, 네모 배치의 조건을 만족시키기 위한 가상의 행과 열!

def dfs(depth):
    global ans
    if depth == N * M:  # 판 끝까지 탐색
        ans += 1
        return

    x = depth // M + 1
    y = depth % M + 1

    dfs(depth+1)

    if check[x-1][y]==0 or check[x][y-1]==0 or check[x-1][y-1]==0:
        check[x][y] = 1
        dfs(depth+1)
        check[x][y] = 0


if __name__ == '__main__':
    N, M = map(int, input().split())

    check = [[0] * (M + 1) for _ in range(N + 1)]
    ans = 0
    dfs(0)
    print(ans)

# x,y 값 구하기 위해 둘 다 '열'로 나누고 나머지 연산
# x,y를 구하는 배열 칸의 값은 0부터 시작
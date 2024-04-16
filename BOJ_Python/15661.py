import sys

input = sys.stdin.readline


def dfs(team, idx):
    global mn
    if team == N // 2:
        a, b = 0, 0
        for i in range(N):
            for j in range(N):
                if visited[i] and visited[j]:
                    a += board[i][j]
                elif not visited[i] and not visited[j]:
                    b += board[i][j]
        mn = min(mn, abs(a-b))
        return

    for i in range(idx, N):
        if not visited[i]:
            visited[i] = True
            dfs(team+1, i+1)
            visited[i] = False


if __name__ == '__main__':
    N = int(input())
    board = [list(map(int, input().split())) for _ in range(N)]
    visited = [False]*N
    mn = float('inf')

    dfs(0,0)
    print(mn)



    # 시간초과 코드
    # # 조합으로 팀 구성
    # nums = [i for i in range(N)]
    # mn = float('inf')
    # for k in range(1, N//2+1):
    #     combi = list(combinations(nums, k))
    #
    #     # 각 팀 능력치 구하기
    #     for cb in combi:
    #         Sa, Sb = 0, 0
    #         for i in range(N):
    #             for j in range(i, N):
    #                 if i in cb and j in cb:
    #                     Sa += board[i][j]+board[j][i]
    #                 elif i not in cb and j not in cb:
    #                     Sb += board[i][j]+board[j][i]
    #         mn = min(abs(Sa-Sb), mn)
    # print(mn)

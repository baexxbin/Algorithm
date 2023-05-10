import sys

input = sys.stdin.readline


# dfs를 돌면서 하나씩 다 깨본다
def dfs(idx, cnt):
    global mx

    # 마지막 계란이거나 계란을 다 깼을 경우 return
    if idx == n:
        mx = max(mx, cnt)
        return

    # 현재 든 계란이 깨진 계란일 경우 넘어가기
    if eggs[idx][0] <= 0 or cnt == n - 1:
        dfs(idx + 1, cnt)
        return

    # 현재 계란(idx)에서 깨볼 수 있는 모든 계란 깨보기
    tmp = cnt
    for i in range(n):
        if i == idx or eggs[i][0] <= 0:
            continue

        eggs[idx][0] -= eggs[i][1]  # 계란 깨기
        eggs[i][0] -= eggs[idx][1]
        if eggs[idx][0] <= 0:
            cnt += 1
        if eggs[i][0] <= 0:
            cnt += 1
        dfs(idx + 1, cnt)
        eggs[idx][0] += eggs[i][1]  # 계란 돌려놓기
        eggs[i][0] += eggs[idx][1]
        cnt = tmp                   # 깬 계란 수도 돌려놓기


if __name__ == '__main__':
    n = int(input())
    eggs = [list(map(int, input().split())) for _ in range(n)]
    mx = float('-INF')

    dfs(0, 0)
    print(mx)

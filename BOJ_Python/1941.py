import sys
from collections import deque

input = sys.stdin.readline


# 뽑은 7명이 서로 연결되어있는지 확인
def bfs(group):
    que = deque()
    visited = [False if i in group else True for i in range(25)]  # group에 해당하는 학생 False로 표시
    que.append((group[0] // 5, group[0] % 5))
    visited[group[0]] = True

    check = 1
    while que:
        x, y = que.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            idx = (nx * 5) + ny
            if 0 <= nx < 5 and 0 <= ny < 5 and not visited[idx]:  # group에 해당하는 학생이 방문하지 않았다면
                visited[idx] = True
                check += 1
                que.append((nx, ny))
    if check == 7:
        return 1
    return 0


# start: 학생 인덱스 , collect: group 모은 수 , Ycnt: 임도연 파 카운트
def dfs(start, collect, Ycnt):
    global ans

    if Ycnt >= 4:
        return

    if collect == 7:
        ans += bfs(group)
        return

    for i in range(start, 25):
        group.append(i)
        dfs(i + 1, collect + 1, Ycnt+(board[i // 5][i % 5] == 'Y'))
        group.pop()


if __name__ == '__main__':
    board = [list(input().strip()) for _ in range(5)]
    students = [i for i in range(25)]
    ans = 0
    group = []

    dx = [1, -1, 0, 0]
    dy = [0, 0, -1, 1]

    # 조합으로 7명뽑기
    dfs(0, 0, 0)
    print(ans)

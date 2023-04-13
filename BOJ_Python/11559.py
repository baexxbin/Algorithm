import sys
import copy
from collections import deque

input = sys.stdin.readline


def bfs(i, j):
    global visited
    tmp = copy.deepcopy(visited)

    que = deque()
    que.append((i, j))
    visited[i][j] = True
    cnt = 1

    while que:
        x, y = que.popleft()

        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < 12 and 0 <= ny < 6:
                if board[nx][ny] == board[i][j] and not visited[nx][ny]:
                    que.append((nx, ny))
                    visited[nx][ny] = True
                    cnt += 1
    if cnt < 4:
        visited = tmp
    return cnt


def update_pop():
    global flag
    for i in range(12):
        for j in range(6):
            pop[i][j] = pop[i][j] or visited[i][j]
    flag = True


if __name__ == '__main__':
    board = []
    dx = [0, 0, -1, 1]
    dy = [1, -1, 0, 0]
    answer = 0
    for _ in range(12):
        board.append(list(input().strip()))

    flag = True
    while flag:
        # bfs 돌면서 연결된 푸요 찾기
        flag = False
        visited = [[False] * 6 for _ in range(12)]
        pop = [[False] * 6 for _ in range(12)]
        for i in range(12):
            for j in range(6):
                if board[i][j] != '.' and not visited[i][j]:
                    update = bfs(i, j)
                    if update >= 4:
                        update_pop()

        # pop 해야할 푸요들 pop 하기
        if flag:
            answer += 1
            pop_board = []
            for j in range(6):
                deq = deque()
                cnt = 0
                for i in range(12):
                    if pop[i][j]:
                        cnt += 1
                        continue
                    deq.append(board[i][j])
                for _ in range(cnt):
                    deq.appendleft('.')
                pop_board.append(list(deq))
            board = list(map(list, zip(*pop_board)))

    print(answer)

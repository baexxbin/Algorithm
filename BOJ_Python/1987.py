import sys

input = sys.stdin.readline


def dfs(x, y, cnt):
    global ans
    cur = ord(board[x][y]) - 65
    if visited[cur]:
        return

    visited[cur] = True
    ans = max(ans, cnt)
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0 <= nx < r and 0 <= ny < c:
            if not visited[ord(board[nx][ny]) - 65]:
                dfs(nx, ny, cnt + 1)
    visited[cur] = False


if __name__ == '__main__':
    r, c = map(int, input().split())
    board = [list(map(str, input().strip())) for _ in range(r)]

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    visited = [False]*26
    ans = -1
    dfs(0, 0, 1)
    print(ans)

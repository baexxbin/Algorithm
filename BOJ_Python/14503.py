import sys

input = sys.stdin.readline


if __name__ == '__main__':
    n, m = map(int, input().split())
    x, y, d = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(n)]

    # 북, 동, 남, 서
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]

    clean = 0
    while True:
        # 1. 현재 칸이 빈칸(0)일 경우 청소
        if board[x][y] == 0:
            clean += 1
            board[x][y] = 2

        # 3. 현재 칸의 주변 중 청소 안된 빈칸 있을 경우
        for _ in range(4):
            d = (d + 3) % 4     # 현재 위치 부터 90도씩 돌면서 청소할 빈칸 찾기
            nx, ny = x + dx[d], y + dy[d]

            if 0 <= nx < n and 0 <= ny < m and board[nx][ny] == 0:
                x, y = nx, ny
                break

        # 2. 현재 칸의 주변 중 청소 안된 빈칸 없을 경우
        else:
            if board[x - dx[d]][y - dy[d]] == 1:  # 2-2. 후진 불가시 동작 그만(끝)
                print(clean)
                break
            x, y = x - dx[d], y - dy[d]  # 2-1. 후진 (현재d 반대)

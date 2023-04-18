import sys

input = sys.stdin.readline


# 주사위 굴리기
# 배열의 인덱스 순서는 문제에 주어진 전개도 인덱스
def roll(d):
    tmp = dice[0]
    # 동
    if d == 1:
        dice[0] = dice[3]
        dice[3] = dice[5]
        dice[5] = dice[2]
        dice[2] = tmp
    # 서
    elif d == 2:
        dice[0] = dice[2]
        dice[2] = dice[5]
        dice[5] = dice[3]
        dice[3] = tmp
    # 북
    elif d == 3:
        dice[0] = dice[1]
        dice[1] = dice[5]
        dice[5] = dice[4]
        dice[4] = tmp
    # 남
    else:
        dice[0] = dice[4]
        dice[4] = dice[5]
        dice[5] = dice[1]
        dice[1] = tmp


if __name__ == '__main__':
    n, m, x, y, k = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(n)]
    move = list(map(int, input().split()))
    dice = [0] * 6

    # 윗면, 동, 서, 북, 남
    dx = [0, 0, 0, -1, 1]
    dy = [0, 1, -1, 0, 0]

    for i in move:
        nx, ny = x + dx[i], y + dy[i]
        if not (0 <= nx < n and 0 <= ny < m):
            continue
        roll(i)
        if board[nx][ny] == 0:
            board[nx][ny] = dice[5]
        else:
            dice[5] = board[nx][ny]
            board[nx][ny] = 0
        x, y = nx, ny
        print(dice[0])

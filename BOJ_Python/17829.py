import sys

input = sys.stdin.readline


def polling(size, y, x):
    mid = size//2
    if size==2:
        ans = [board[y][x], board[y][x+1], board[y+1][x], board[y+1][x+1]]
        ans.sort()
        return ans[-2]

    lt = polling(mid, y, x)
    rt = polling(mid, y, x+mid)
    lb = polling(mid, y+mid, x)
    rb = polling(mid, y+mid, x+mid)

    ans = [lt, rt, lb, rb]
    ans.sort()
    return ans[-2]


if __name__ == '__main__':
    N = int(input())
    board = [list(map(int, input().split())) for _ in range(N)]
    print(polling(N, 0, 0))



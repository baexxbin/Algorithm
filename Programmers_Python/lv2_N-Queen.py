def backtracking(r, N):
    global cnt, col, diagonal_up, diagonal_down

    if r == N:
        cnt += 1

    for c in range(N):
        if col[c] or diagonal_up[r + c] or diagonal_down[r - c + N - 1]:
            continue
        col[c], diagonal_up[r + c], diagonal_down[r - c + N - 1] = True, True, True
        backtracking(r + 1, N)
        col[c], diagonal_up[r + c], diagonal_down[r - c + N - 1] = False, False, False


def solution(n):
    global col, diagonal_up, diagonal_down
    col = [False] * n
    diagonal_up = [False] * (2 * n - 1)
    diagonal_down = [False] * (2 * n - 1)

    global cnt
    cnt = 0

    backtracking(0, n)
    return cnt

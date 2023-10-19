
def solution(rows, columns, queries):
    board = [[0 for _ in range(columns)] for _ in range(rows)]
    ans = []

    # 숫자판 만들기
    idx = 1
    for r in range(rows):
        for c in range(columns):
            board[r][c] = idx
            idx+=1

    # 배열 돌리기
    for x1, y1, x2, y2 in queries:
        pre = board[x1 - 1][y1 - 1]
        tmp = [pre]

        # 왼쪽 세로
        for k in range(x1 - 1, x2 - 1):
            nxt = board[k + 1][y1 - 1]
            board[k][y1 - 1] = nxt
            tmp.append(nxt)

        # 하단 가로
        for k in range(y1 - 1, y2 - 1):
            nxt = board[x2 - 1][k + 1]
            board[x2 - 1][k] = nxt
            tmp.append(nxt)

        # 오른쪽 세로
        for k in range(x2 - 1, x1 - 1, -1):
            nxt = board[k - 1][y2 - 1]
            board[k][y2 - 1] = nxt
            tmp.append(nxt)

        # 상단 가로
        for k in range(y2 - 1, y1 - 1, -1):
            nxt = board[x1 - 1][k - 1]
            board[x1 - 1][k] = nxt
            tmp.append(nxt)

        board[x1 - 1][y1] = pre
        ans.append(min(tmp))

    print(ans)
    return ans


row = 6
columns = 6
queries = [[2,2,5,4],[3,3,6,6],[5,1,6,3]]
solution(row, columns, queries)
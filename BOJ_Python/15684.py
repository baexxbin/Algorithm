import sys

input = sys.stdin.readline


def isRightArrival():       # 사다리타고 i번째는 i번째에 도착하는지 확인
    for start in range(N):
        cur = start
        for i in range(H):
            if board[i][cur]:
                cur+=1
            elif cur > 0 and board[i][cur-1]:
                cur-=1
        if cur != start:
            return False
    return True


def dfs(cnt, x, y):
    global ans
    if isRightArrival():
        ans = min(ans, cnt)
        return
    elif cnt==3 or cnt >= ans:
        return

    for i in range(x, H):       # 행
        if i==x:                # 현재 행, 열일땐 현재 위치부터 탐색
            col = y
        else:                   # 행 업데이트 되면 열 0부터 시작
            col = 0
        for j in range(col, N-1):
            if not board[i][j] and not board[i][j+1]:    # 현재, 오른쪽에 사다리 없을 때
                if j > 0 and board[i][j-1]:              # 왼쪽에 사다리 있다면 패쓰
                    continue
                board[i][j] = True
                dfs(cnt+1, i, j+2)
                board[i][j] = False


if __name__ == '__main__':
    N, M, H = map(int, input().split())     # 열(세로), 사다리 수, 행(가로)
    board = [[False]*N for _ in range(H)]

    for _ in range(M):
        a, b = map(int, input().split())
        board[a-1][b-1] = True

    ans = 4
    dfs(0,0,0)
    print(ans if ans != 4 else -1)
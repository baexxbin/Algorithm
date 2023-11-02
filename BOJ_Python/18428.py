import sys

input = sys.stdin.readline

# 10:08 - 10:47

def check():
    for x,y in teachers:
        for i in range(4):
            nx, ny = x+dx[i], y+dy[i]
            while 0<=nx<N and 0<=ny<N:
                if board[nx][ny] == 'O':
                    break
                elif board[nx][ny] == 'S':
                    return False
                nx+=dx[i]
                ny+=dy[i]
    return True


def dfs(depth):
    if depth==3:
        if check():
            return True
        return False

    for i in range(N):
        for j in range(N):
            if board[i][j]=='X':
                board[i][j] = 'O'
                if dfs(depth+1):
                    return True
                board[i][j] = 'X'
    return False


if __name__ == '__main__':
    N = int(input())
    board = [input().split() for _ in range(N)]
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    teachers = []
    for i in range(N):
        for j in range(N):
            if board[i][j] == 'T':
                teachers.append((i,j))

    print('YES') if dfs(0) else print('NO')

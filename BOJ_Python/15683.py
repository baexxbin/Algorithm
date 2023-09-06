import copy
import sys

input = sys.stdin.readline


# DFS를 돌면서 모든 경우 탐색
# 구현 사항
# 1. 90도씩 방향 돌리기
# 2. 해당하는 방향 감시하기
# 3. 사각지대의 개수 구하기

def monitoring(x, y, direct, board):  # cctv가 보는 방향으로 감시하기
    for d in direct:  # dir(cctv가 감사하는 방향 담긴 변수)모두 돌면서 감시 체크
        nx, ny = x, y
        while True:
            nx, ny = nx + dx[d], ny + dy[d]
            if not (0 <= nx < N and 0 <= ny < M):
                break
            if board[nx][ny] == 6:  # 벽 만나면 끝
                break
            elif board[nx][ny] == 0:  # 감시할 수있는 공간은 감시 체크
                board[nx][ny] = 7


def dfs(depth, board):  # 현재까지 감시한 cctv개수, 현재의 감시 보드
    global area

    # 만약 존재하는 cctv 다 보았을경우, 사각지대 카운트
    if depth == len(cctv):
        cnt = 0
        for row in board:
            cnt += row.count(0)
        area = min(area, cnt)  # 현재까지 가장 작은 사각지대 개수로 업데이트
        return

    # 현재 cctv정보
    x, y, idx = cctv[depth]

    for di in info[idx]:  # 현재 cctv번호에 따라 봐야하는 방향 다 체크
        tmpBoard = copy.deepcopy(board)
        monitoring(x, y, di, tmpBoard)  # 한 방향에 대해 감시구역 체크
        dfs(depth + 1, tmpBoard)  # 현재 체크한 board바탕으로 dfs진행


if __name__ == '__main__':
    N, M = map(int, input().split())
    matrix = [list(map(int, input().split())) for _ in range(N)]

    cctv = []
    info = {  # cctv 각 번호별 90도씩 돌아가며 보는 방향
        1: [[0], [1], [2], [3]],
        2: [[0, 2], [1, 3]],
        3: [[0, 1], [1, 2], [2, 3], [3, 0]],
        4: [[0, 1, 3], [0, 1, 2], [1, 2, 3], [2, 3, 0]],
        5: [[0, 1, 2, 3]]
    }
    dx = [-1, 0, 1, 0]  # 북 동 남 서
    dy = [0, 1, 0, -1]

    # CCTV정보 조사하기
    area = 0  # 감시해야하는 영역(초기값)이자 사각지대 값
    for i in range(N):
        for j in range(M):
            if matrix[i][j] == 0:
                area += 1
                continue
            if matrix[i][j] == 6:
                continue
            cctv.append((i, j, matrix[i][j]))  # cctv의 좌표, 번호 정보

    dfs(0, matrix)
    print(area)


# tmpBoard 깊은 복사하고 for문 안에서 초기화하기
    # 깊은 복사 >> 원본 변수에 영향을 끼치지 않기 위해
    # for문 안 초기화 >> 매개변수로 넘겨준 tmpBoard는 monitoring,dfs호출 시 변경됨, 각 상황간 독립적으로 진행하기 위해
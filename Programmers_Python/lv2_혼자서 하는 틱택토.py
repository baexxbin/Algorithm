from collections import Counter

def check_ttt(c, board):
    for i in range(len(board)):
        if board[i][0] == c and board[i][1] == c and board[i][2] == c:
            return True
        elif board[0][i] == c and board[1][i] == c and board[2][i] == c:
            return True
    if board[0][0] == c and board[1][1] == c and board[2][2] == c:
        return True
    elif board[0][2] == c and board[1][1] == c and board[2][0] == c:
        return True

def solution(board):
    # 1. 순서 실수 >> 갯수 차이 판별(O가 X보다 1개 크거나 같음)
    counter = Counter(list(''.join(board)))
    if counter['X'] > counter['O'] or counter['X'] + 1 < counter['O']:
        return 0

    # 2. 종료 후 진행 판별 > 완성된 틱택토 수 구하기
    isOwin = check_ttt('O', board)
    isXwin = check_ttt('X', board)
    if isOwin:
        if counter['O'] <= counter['X'] or isXwin:
            return 0
        else:
            return 1

    elif isXwin:
        if counter['X'] < counter['O'] or isOwin:
            return 0
        else:
            return 1

    else:   # O,X 둘다 빙고 아닐 경우
        return 1



if __name__ == '__main__':
    board = ["O.X", ".O.", "..X"]
    print(solution(board))
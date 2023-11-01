def solution(board):
    dp = [[0]*(len(board[0])+1) for _ in range(len(board)+1)]

    ans = 0
    for i in range(1, len(board)+1):
        for j in range(1, len(board[0])+1):
            if board[i-1][j-1]==1:
                dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])+1
                ans = max(ans, dp[i][j])
    print(ans**2)

board = [[0,1,1,1],[1,1,1,1],[1,1,1,1],[0,0,1,0]]
solution(board)
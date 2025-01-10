import java.util.Arrays;

public class lv2_가장큰정사각형찾기 {
    public static int solution(int[][] board) {
        int N = board.length;
        int M = board[0].length;

        int[][] dp = new int[N][M];

        // dp배열 초기화
        int len = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j] = board[i][j];
                if (dp[i][j]==1) len = 1;
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (board[i][j]==0) continue;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                len = Math.max(len, dp[i][j]);
            }
        }

        return len*len;
    }

    public static void main(String[] args) {
        int[][] board = {{0, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {0, 0, 1, 0}};
        System.out.println(solution(board));
    }
}

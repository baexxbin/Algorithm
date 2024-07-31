public class lv3_보행자천국 {
    static int MOD = 20170805;

    public static int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[2][m + 1][n + 1];

        dp[0][0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cityMap[i][j] == 0) {
                    dp[0][i+1][j] += (dp[0][i][j] + dp[1][i][j]) % MOD;
                    dp[1][i][j+1] += (dp[0][i][j] + dp[1][i][j]) % MOD;
                }
                else if (cityMap[i][j] == 2) {
                    dp[0][i+1][j] += dp[0][i][j]%MOD;
                    dp[1][i][j+1] += dp[1][i][j]%MOD;
                }
            }
        }

        return (dp[0][m - 1][n - 1] + dp[1][m - 1][n - 1]) % MOD;
    }
    public static void main(String[] args) {
        int m = 3;
        int n = 6;
        int[][] cityMap = {
                {0, 2, 0, 0, 0, 2},
                {0, 0, 2, 0, 1, 0},
                {1, 0, 0, 2, 2, 0}
        };
        System.out.println(solution(m, n, cityMap)); // Expected output: 2
    }
}


//    private static int dfs(int x, int y, int dir) {
//        if (x == M - 1 && y == N - 1) {
//            return 1;
//        }
//
//        if (memo[x][y][dir] != -1) {
//            return memo[x][y][dir];
//        }
//
//        int paths = 0;
//
//        for (int i = 0; i < 2; i++) {
//            int nx = x + dx[i];
//            int ny = y + dy[i];
//            if (isOutbound(nx, ny) || board[nx][ny] == 1) continue;
//            if (board[x][y] == 2 && dir != i) continue;
//            paths = (paths + dfs(nx, ny, i)) % MOD;
//        }
//
//        memo[x][y][dir] = paths;
//        return paths;
//    }
//
//    private static boolean isOutbound(int x, int y) {
//        return !(0 <= x && x < M && 0 <= y && y < N);
//    }
//
//    public static int solution(int m, int n, int[][] cityMap) {
//        M = m;
//        N = n;
//        board = cityMap;
//        memo = new int[M][N][2];
//        for (int i = 0; i < M; i++) {
//            for (int j = 0; j < N; j++) {
//                memo[i][j][0] = -1;
//                memo[i][j][1] = -1;
//            }
//        }
//
//        return (dfs(0, 0, 0) + dfs(0, 0, 1)) % MOD;
//    }
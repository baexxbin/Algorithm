import java.util.Arrays;

/*
* 다 해보되, dp로 효율적으로
* */
public class lv2_땅따먹기 {
    static int N;
    static int[][] dp;

    static int solution(int[][] land) {
        N = land.length;
        dp = new int[N][4];

        for (int i = 0; i < 4; i++) {
            dp[0][i] = land[0][i];
        }

        for (int i = 1; i < N; i++) {
            dp[i][0] = land[i][0] + Math.max(dp[i - 1][1],Math.max(dp[i - 1][2], dp[i - 1][3]));
            dp[i][1] = land[i][1] + Math.max(dp[i - 1][0],Math.max(dp[i - 1][2], dp[i - 1][3]));
            dp[i][2] = land[i][2] + Math.max(dp[i - 1][1],Math.max(dp[i - 1][0], dp[i - 1][3]));
            dp[i][3] = land[i][3] + Math.max(dp[i - 1][1],Math.max(dp[i - 1][2], dp[i - 1][0]));
        }

        return Math.max(Math.max(dp[N - 1][0], dp[N - 1][1]), Math.max(dp[N - 1][2], dp[N - 1][3]));

    }
    public static void main(String[] args) {
        int[][] land = {{1, 2, 3, 5}, {5, 6, 7, 8}, {4, 3, 2, 1}};
        System.out.println(solution(land));
    }
}

import java.util.Arrays;

// 모든 문제를 푸는데 걸리는 최단 알고력과 코딩력 구하기

public class lv3_코딩테스트공부 {

    public static int solution(int alp, int cop, int[][] problems) {
        int INF = Integer.MAX_VALUE;

        // 최대 알고, 코딩력 찾기
        int mxA = -1, mxC = -1;
        for (int[] p : problems) {
            mxA = Math.max(mxA, p[0]);
            mxC = Math.max(mxC, p[1]);
        }

        // 초기 알고, 코딩력이 최대값을 넘지 않도록 조절
        alp = Math.min(alp, mxA);
        cop = Math.min(cop, mxC);

        int[][] dp = new int[mxA+1][mxC+1];         // i알고, j코딩력까지 가는 최소 시간
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[alp][cop] = 0;                           // 현재 가진 알고, 코딩력부터 시작

        for (int i = alp; i <= mxA; i++) {
            for (int j = cop; j <= mxC; j++) {
                if (i+1 <= mxA) dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                if (j+1 <= mxC) dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);

                for (int[] pb : problems) {
                    if (i < pb[0] || j < pb[1]) continue;
                    int nxA = Math.min(i + pb[2], mxA);         // 향상된 실력이 최대값을 넘어가면 패쓰하는 것이 아닌, 최대값으로 설정
                    int nxC = Math.min(j + pb[3], mxC);
                    dp[nxA][nxC] = Math.min(dp[nxA][nxC], dp[i][j] + pb[4]);
                }
            }
        }

        return dp[mxA][mxC];
    }


    public static void main(String[] args) {
        int alp = 0;
        int cop = 0;
        int[][] problems = {{0, 0, 2, 1, 2}, {4, 5, 3, 1, 2}, {4, 11, 4, 0, 2}, {10, 4, 0, 4, 2}};
        System.out.println(solution(alp, cop, problems));
    }
}

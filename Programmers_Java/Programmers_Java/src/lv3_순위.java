public class lv3_순위 {
    public static int solution(int n, int[][] results) {
        int[][] floyd = new int[n + 1][n + 1];
        for (int[] pair : results) {
            int a = pair[0];
            int b = pair[1];
            floyd[a][b] = 1;
            floyd[b][a] = -1;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                for (int k = 1; k < n + 1; k++) {
                    if (floyd[i][k] == 1 && floyd[k][j] == 1) {
                        floyd[i][j] = 1;
                        floyd[j][i] = -1;
                    }
                    if (floyd[i][k] == -1 && floyd[k][j] == -1) {
                        floyd[i][j] = -1;
                        floyd[j][i] = 1;
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 1; i < n + 1; i++) {
            int cnt = 0;
            for (int j = 1; j < n + 1; j++) {
                if (floyd[i][j]!=0) cnt++;
            }
            if (cnt==n-1) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        System.out.println(solution(n, results));
    }
}

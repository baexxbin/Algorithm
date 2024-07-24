public class lv3_파괴되지않은건물 {
    public static void main(String[] args) {
        int[][] board = {{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}};
        int[][] skill = {{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}};
        System.out.println(solution(board, skill));
    }
    public static int solution(int[][] board, int[][] skill) {
        int N = board.length;
        int M = board[0].length;
        int[][] tmp = new int[N+1][M+1];    // 한칸 뒤에 누적합 엔드포인트 설정

        for (int[] s : skill) {
            int d = s[0]==1 ? -1 : 1;
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            tmp[r1][c1] += s[5]*d;
            tmp[r1][c2+1] += s[5]*(-d);
            tmp[r2+1][c1] += s[5]*(-d);
            tmp[r2+1][c2+1] += s[5]*d;
        }

        // 행 누적합 (가로)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j+1] += tmp[i][j];
            }
        }

        // 열 누적합 (세로)
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                tmp[j+1][i] += tmp[j][i];
            }
        }

        // 최종 건물 확인
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j]+tmp[i][j] > 0) cnt++;
            }
        }
        return cnt;
    }
}

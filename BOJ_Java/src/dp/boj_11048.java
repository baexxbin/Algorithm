package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 이동하기
* 설계
*   - dfs로 갈 수 있는 모든 경우 중 사탕 최대값 찾기
*   - 시간복잡도 O(n^2) (인접행렬), O(n+e) 인접리스트 >> 10^6
*   - 시간초과 >> dp로 풀기..
*       - 0행, 0열 추가
*       - 점화식: dp[i][j] = Max(dp[i-1][j-1], dp[i-1][j], d[i][j-1]) + board[i-1][j-1]
* */
public class boj_11048 {
    static int N, M;
    static int[][] board;
////    static int[][] visited;
//    static int ans = 0;
//    static int[] dx = {1, 0, 1};
//    static int[] dy = {0, 1, 1};
//
//    private static void dfs(int x, int y) {
//        if (x == N - 1 && y == M - 1) {
//            ans = Math.max(ans, visited[x][y]);
//            return;
//        }
//
//        for (int i = 0; i < 3; i++) {
//            int nx = x + dx[i];
//            int ny = y + dy[i];
//            if (!(0<=nx && nx<N && 0<=ny && ny<M)) continue;
//            if (visited[nx][ny]==-1){
//                visited[nx][ny] = visited[x][y] + board[nx][ny];
//                dfs(nx, ny);
//                visited[nx][ny] = -1;
//            }
//        }
//
//    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
//        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
//                visited[i][j] = -1;
            }
        }

//        visited[0][0] = board[0][0];
//        dfs(0, 0);

        int[][] dp = new int[N + 1][M + 1];
        for (int i = 1; i < N + 1; i++) {       // dp배열 기준 (행,열 1씩 많음)
            for (int j = 1; j < M+1; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], Math.max(dp[i - 1][j], dp[i][j - 1])) + board[i - 1][j - 1];
            }
        }

        System.out.println(dp[N][M]);

//        System.out.println(ans);
    }
}

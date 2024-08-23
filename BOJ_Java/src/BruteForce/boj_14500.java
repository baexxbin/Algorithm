package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 테트로미노
* 문제 설명: 테트로미노 하나를 놓아서 가장 큰 수 합 만들기
* 설계
*   - dfs, 회전, 대칭을 통해 모든 경우의 수 구하기 >> 복잡..
*   - ㅜ 모양을 제외한 모든 테트로미노는 넓이가 4인 bfs로 구할 수 있음
*   - ㅜ모양의 경우 2번째 탐색했을때, 처음위치에서 탐색하도록하면 만들 수 있음
* */
public class boj_14500 {
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int mxVal = 0;
    static int ans = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static void dfs(int x, int y, int val, int depth) {
        if (ans > val+(mxVal*(4-depth))) return;    // 가지치기
        if (depth == 4) {
            ans = Math.max(ans, val);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!(0<=nx && nx<N && 0<=ny && ny <M) || visited[nx][ny]) continue;
            if (depth == 2) {       // ㅜ모양 만들기
                visited[nx][ny] = true;
                dfs(x, y, val + board[nx][ny], depth + 1);
                visited[nx][ny] = false;
            }
            visited[nx][ny] = true;
            dfs(nx, ny, val + board[nx][ny], depth + 1);        // 그 외 나머지 모양 만들기
            visited[nx][ny] = false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] > mxVal) mxVal = board[i][j];
            }
        }

        // 한 지점을 시작으로 테트로미노 만들기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, board[i][j], 1);
                visited[i][j] = false;
            }
        }

        System.out.println(ans);
    }
}

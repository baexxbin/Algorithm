import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1189 {
    static int R, C, K;
    static char[][] board;
    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {-1, 1, 0, 0};
    static int cnt;

    private static boolean inRange(int y, int x) {
        return 0<=y && y<R && 0<=x && x<C;
    }

    private static void dfs(int y, int x, int dist, boolean[][] visited) {
        if (y == 0 && x == C - 1) {
            if (dist==K) cnt++;
            return;
        }

        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];

            if (!inRange(ny, nx)) continue;
            if (board[ny][nx] == '.' && !visited[ny][nx]) {
                visited[ny][nx] = true;
                dfs(ny, nx, dist+1, visited);
                visited[ny][nx] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        for (int r = 0; r < R; r++) {
            board[r] = br.readLine().toCharArray();
        }

        cnt = 0;
        boolean[][] visited = new boolean[R][C];
        visited[R-1][0] = true;
        dfs(R-1, 0, 1, visited);
        System.out.println(cnt);
    }
}

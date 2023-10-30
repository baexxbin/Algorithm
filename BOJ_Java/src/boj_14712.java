import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class boj_14712 {
    static int N, M;
    static int[][] board;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N+1][M+1];

        ans = 0;
        dfs(0);

        System.out.println(ans);

    }

    public static void dfs(int depth) {
        if (depth == N * M) {
            ans++;
            return;
        }

        int x = depth/M+1;
        int y = depth%M+1;

        dfs(depth+1);

        if (board[x - 1][y] == 0 || board[x][y - 1] == 0 || board[x - 1][y - 1]==0) {
            board[x][y] = 1;
            dfs(depth+1);
            board[x][y] = 0;
        }

    }
}

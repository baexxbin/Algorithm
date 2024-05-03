import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_15684 {
    static int N, H, M;
    static boolean[][] board;
    static int ans;

    private static boolean isRightArrival() {
        for (int st = 0; st < N; st++) {
            int cur = st;
            for (int i = 0; i < H; i++) {   // 가로로 움직임
                if (board[i][cur]) cur++;
                else if (cur > 0 && board[i][cur-1]) cur--;
            }
            if (cur!=st) return false;
        }
        return true;
    }

    private static void dfs(int cnt, int y, int x){
        if (isRightArrival()) {
            ans = Math.min(ans, cnt);
            return;
        } else if (cnt==3 || cnt >= ans) {
            return;
        }

        int col;
        for (int j = y; j<H; j++) {
            if(j==y){
                col = x;
            }else col = 0;

            for (int i = col; i < N-1; i++) {
                if (!board[j][i] && !board[j][i + 1]) {     // 오른쪽에 사다리 놓을 수 있는지 검사
                    if (i>0 && board[j][i-1]) continue;
                    board[j][i] = true;
                    dfs(cnt+1, j, i+2);
                    board[j][i] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new boolean[H][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a-1][b-1] = true;
        }

        ans = 4;
        dfs(0,0,0);
        System.out.println(ans!=4?ans:-1);
    }
}

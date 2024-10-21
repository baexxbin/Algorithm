import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class st_시공의돌풍_2 {
    static int N,M,T;
    static int[][] board;
    static int[] dx = {0, -1, 0, 1};        // 우상좌하
    static int[] dy = {1, 0, -1, 0};

    private static void diffusion() {
        int[][] dust = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j]==-1) continue;
                int div = board[i][j]/5;
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (!isValid(nx, ny) || board[nx][ny]==-1) continue;
                    dust[nx][ny] += div;
                    cnt++;
                }
                dust[i][j] -= div*cnt;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] += dust[i][j];
            }
        }
    }

    private static void wind(int sr, int mod) {
        int pre = 0;
        int d = 0;
        int r = sr;
        int c = 1;

        while (!(r==sr && c==0)){
            int tmp = board[r][c];
            board[r][c] = pre;
            pre = tmp;

            int nr = r+dx[d];
            int nc = c+dy[d];

            if (!isValid(nr, nc)) {     // 범위밖이면 바람바람 바뀜
                d = (d+mod+4)%4;
                nr = r+dx[d];
                nc = c+dy[d];
            }
            r = nr;
            c = nc;
        }
    }

    private static boolean isValid(int x, int y) {
        return 0<=x && x<N && 0<=y && y<M;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        int dolp = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j]==-1) dolp = i;
            }
        }

        while (T-- > 0) {
            diffusion();
            wind(dolp - 1, 1);
            wind(dolp, -1);
        }

        int cnt = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cnt += board[i][j];
            }
        }
        System.out.println(cnt);
    }
}

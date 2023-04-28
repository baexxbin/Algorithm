import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14503 {
    static int[][] board;
    static int N;
    static int M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int clean;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        Point point = new Point(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()));

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(point);
        System.out.println(clean);

    }

    public static void dfs(Point point) {
        if (board[point.x][point.y]==0){
            board[point.x][point.y] = 2;
            clean++;
        }

        for (int i = 0; i < 4; i++) {
            point.d = (point.d+3)%4;
            int nx = point.x + dx[point.d];
            int ny = point.y + dy[point.d];

            if (nx>=0 && ny>=0 && nx<N && ny<M && board[nx][ny]==0){
                dfs(new Point(nx, ny, point.d));
                return;
            }
        }

        int bx = point.x - dx[point.d];
        int by = point.y - dy[point.d];
        if (board[bx][by]!=1){
            dfs(new Point(bx, by, point.d));
        }


    }

    static class Point {
        int x, y, d;

        public Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}

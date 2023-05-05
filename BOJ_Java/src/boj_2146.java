import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2146 {
    static int N, idx, mn;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 섬을 찾아서 번호 매기기
        visited = new boolean[N][N];
        idx = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j]==1) {
                    bfs(i, j);
                    idx++;
                }
            }
        }

        // 섬들을 돌면서 다른섬 bfs로 찾기
        mn = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j] > 1) {
                    visited = new boolean[N][N];
                    findBridge(i, j);
                }
            }
        }
        System.out.println(mn);
    }

    public static void findBridge(int i, int j) {
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(i, j, 0));
        int idx = board[i][j];
        visited[i][j] = true;

        while (!que.isEmpty()) {
            Point cur = que.poll();

            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];
                if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny] && board[nx][ny] != idx) {
                    visited[nx][ny] = true;
                    if (board[nx][ny] == 0) que.add(new Point(nx, ny, cur.d+1));
                    else mn = Math.min(mn, cur.d);
                }
            }
        }
    }

    public static void bfs(int i, int j) {
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(i, j, 0));
        visited[i][j] = true;
        board[i][j] = idx;

        while (!que.isEmpty()) {
            Point cur = que.poll();

            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];
                if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny] && board[nx][ny] == 1) {
                    que.add(new Point(nx, ny, 0));
                    board[nx][ny] = idx;
                    visited[nx][ny] = true;
                }
            }
        }
    }

}
class Point {
    int x;
    int y;
    int d;

    Point(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}

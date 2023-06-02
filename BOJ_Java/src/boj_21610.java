import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_21610 {
    static int N;
    static int M;
    static int[][] board;
    static Queue<Cloud> clouds = new LinkedList<>();

    static boolean[][] visited;
    // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 배열 초기화
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][N];

        // 초기 구름
        clouds.offer(new Cloud(N - 1, 0));
        clouds.offer(new Cloud(N - 1, 1));
        clouds.offer(new Cloud(N - 2, 0));
        clouds.offer(new Cloud(N - 2, 1));


        // 이동정보 받기
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());

            moveCloud(d, s);
            copyBug();
            newCloud();
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans += board[i][j];
            }
        }
        System.out.println(ans);
    }

    // 구름 이동하기
    public static void moveCloud(int d, int s) {
        s = s % N;
        for (Cloud cloud : clouds) {
            cloud.r = (cloud.r + (dx[d]*s) + N) % N;
            cloud.c = (cloud.c + (dy[d]*s) + N) % N;
            board[cloud.r][cloud.c]++;
        }
    }

    public static void copyBug(){
        while (!clouds.isEmpty()){
            Cloud cur = clouds.poll();

            for (int i = 1; i<8; i+=2){
                int nr = cur.r + dx[i];
                int nc = cur.c + dy[i];
                if (0<=nr && nr<N && 0<=nc && nc<N){
                    if (board[nr][nc] > 0){
                        board[cur.r][cur.c]++;
                    }
                }
            }
            visited[cur.r][cur.c] = true;
        }
    }

    public static void newCloud() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && board[i][j]>=2) {
                    clouds.offer(new Cloud(i, j));
                    board[i][j] -= 2;
                }
            }
        }

        // visited 배열 초기화
        for (int j = 0; j < N; j++) {
            Arrays.fill(visited[j], false);
        }
    }
}
class Cloud {
    int r;
    int c;

    Cloud(int r, int c) {
        this.r = r;
        this.c = c;
    }
}


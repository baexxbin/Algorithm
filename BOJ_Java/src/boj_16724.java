import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 순환 그래프(사이클) 수 찾기
// 각 순환 그래프 막으면 해당 사이클의 사람이 다 세이프존에 들어감
public class boj_16724 {
    static int N, M, safe;
    static int[][] board;
    static boolean[][] visited;
    static boolean[][] finished;
    static int[] dx = {-1, 0, 1, 0};    // 북(U) 동(R) 남(D) 서(L)
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                int c = line.charAt(j);
                if (c == 'U') board[i][j] = 0;
                else if (c == 'R') board[i][j] = 1;
                else if (c == 'D') board[i][j] = 2;
                else board[i][j] = 3;
            }
        }

        visited = new boolean[N][M];
        finished = new boolean[N][M];
        safe = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!finished[i][j]){
                    dfs(i,j);
                }
            }
        }
        System.out.println(safe);
    }

    public static void dfs(int x, int y) {
        visited[x][y] = true;
        int d = board[x][y];
        int nx = x+dx[d];
        int ny = y+dy[d];
        if (!visited[nx][ny]){
            dfs(nx, ny);
        } else if (!finished[nx][ny]) {
            safe ++;
        }
        finished[x][y] = true;
    }
}

// * 자바에서 문자 이용시 4바이트인 int 활용 (대부분의 컴퓨터 아키텍처는 4바이트로 훨씬 빠른 속도와 메모리 접근 최적화)

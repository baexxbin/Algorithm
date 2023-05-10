import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16197 {
    static int N, M;
    static char[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        Coin coin1 = null;
        Coin coin2 = null;
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j]=='o' && !flag){         // 동전 위치 파악
                    coin1 = new Coin(i,j,0);
                    flag = true;
                } else if (board[i][j]=='o' && flag) {
                    coin2 = new Coin(i, j,0);
                }
            }
        }

        // 동전 2개를 대상으로 bfs진행
        System.out.println(bfs(coin1, coin2));
    }
    static int bfs(Coin coin1, Coin coin2) {
        Queue<Coin> que1 = new LinkedList<>();
        Queue<Coin> que2 = new LinkedList<>();

        que1.add(coin1);
        que2.add(coin2);

        while (!que1.isEmpty() && !que2.isEmpty()) {
            Coin cur1 = que1.poll();
            Coin cur2 = que2.poll();

            if (cur1.cnt >= 10) {       // 현재 동전이 10번째(이상) 움직인 것이라면
                return -1;
            }

            for (int i = 0; i < 4; i++) {
                int check = 0;
                int nx1 = cur1.x + dx[i];
                int ny1 = cur1.y + dy[i];
                int nx2 = cur2.x + dx[i];
                int ny2 = cur2.y + dy[i];

                if (nx1 < 0 || nx1 >= N || ny1 < 0 || ny1 >= M) {
                    check++;
                }
                if (nx2 < 0 || nx2 >= N || ny2 < 0 || ny2 >= M) {
                    check++;
                }

                if (check==2) continue;

                if (check == 1) return cur1.cnt+1;

                // 벽 만났을 경우
                if (board[nx1][ny1] == '#' && board[nx2][ny2] == '#') continue;

                if (board[nx1][ny1] == '#') {
                    nx1 = cur1.x;
                    ny1 = cur1.y;
                }
                if (board[nx2][ny2] == '#') {
                    nx2 = cur2.x;
                    ny2 = cur2.y;
                }

                que1.add(new Coin(nx1, ny1, cur1.cnt+1));
                que2.add(new Coin(nx2, ny2, cur2.cnt+1));
            }
        }
        return -1;
    }
}
class Coin{
    int x;
    int y;
    int cnt;

    Coin(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_20056 {
    static int N, M, K;
    static Queue<FireBall>[][] board;
    static List<FireBall> balls;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        balls = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            balls.add(new FireBall(r, c, m, d, s)); // 행, 열, 질량, 방향, 속력
        }

        // board배열 각각의 요소가 Queue를 가짐
        // => 각 위치에서 파이어볼 연산위함
        board = new Queue[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < K; i++) {
            moveFireBall();
            splitFireBall();
        }

        int ans = 0;
        for (FireBall ball : balls) {
            ans+=ball.m;
        }
        System.out.println(ans);
    }

    // 파이어볼 이동
    static void moveFireBall() {
        for (FireBall ball : balls) {
            // IndexOutOfBoundsException 되지 않도록 N 더해주기
            ball.r = (N+ball.r + (ball.s%N)*dx[ball.d]) % N;
            ball.c = (N+ball.c + (ball.s%N)*dy[ball.d]) % N;

            board[ball.r][ball.c].add(ball);
        }
    }

    // 파이어볼 분열
    static void splitFireBall() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j].size() < 2){    // 질량 0일경우 소멸
                    board[i][j].clear();
                    continue;
                }

                int tmp_m = 0;
                int tmp_s = 0;
                int cnt = board[i][j].size();
                boolean odd = true, even = true;

                while (!board[i][j].isEmpty()){
                    FireBall cur = board[i][j].poll();
                    tmp_m+= cur.m;
                    tmp_s+= cur.s;

                    if (cur.d % 2 == 0) {
                        odd = false;
                    } else {
                        even = false;
                    }
                    balls.remove(cur);
                }

                int nxt_m = tmp_m/5;
                if (nxt_m==0){
                    continue;
                }
                int nxt_s = tmp_s/cnt;

                if (odd || even){
                    addSplitFireBall(0, i, j, nxt_m, nxt_s);
                }else {
                    addSplitFireBall(1, i, j, nxt_m, nxt_s);
                }
            }
        }
    }

    static void addSplitFireBall(int start, int r, int c, int m, int s) {
        for (int d = start; d < 8; d += 2) {
            balls.add(new FireBall(r, c, m, d, s));
        }
    }
}
class FireBall {
    int r;
    int c;
    int m;
    int d;
    int s;

    FireBall(int r, int c, int m, int d, int s) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.d = d;
        this.s = s;
    }
}

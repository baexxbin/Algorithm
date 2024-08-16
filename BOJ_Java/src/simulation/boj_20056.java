package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 마법사 상어와 파이어볼
* 문제 설명: K번 이동 명령 후 남아있는 파이어볼의 질량합 구하기
* 문제 설계
*   - 큐에 파이어볼 넣기
*   - 파이어볼 이동하기 (행,열 이어져있음, 이차원 리스트에 도착한 파이어볼 넣기)
*   - 2개 이상 파이어볼 합치고 나누기
* */
public class boj_20056 {
    static int N, M, K;
    static Queue<Ball>[][] board;
    static List<Ball> balls;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    // 파이어볼 이동
    static void move() {
        for (Ball ball : balls) {
            int speed = ball.s % N;     // ball.s 자체를 바꾸면 파이어볼 분열 계산 시 올바른 값 계산 못함
            ball.r = (ball.r + speed * dx[ball.d] + N) % N;
            ball.c = (ball.c + speed * dy[ball.d] + N) % N;
            board[ball.r][ball.c].add(ball);
        }

    }

    // 파이어볼 분열
    static void split() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = board[i][j].size();

                // 분열되지 않을 파이어볼은 냅두기
                if (cnt < 2){
                    board[i][j].clear();    // 보드에서는 초기화, ball에는 남아있음
                    continue;
                }

                // 파이어볼 2개 이상
                int nm = 0, ns = 0, nd = 0;
                boolean odd = true, even = true;
                while (!board[i][j].isEmpty()){
                    Ball cur = board[i][j].poll();
                    nm+= cur.m;
                    ns+= cur.s;
//                    nd+= cur.d;

                    if (cur.d % 2 == 0) odd = false;
                    else even = false;

                    balls.remove(cur);      // 분열될 파이어볼 제거
                }

                nm /= 5;
                ns /= cnt;
                if (nm==0) continue;
//                nd = nd % 2 == 0 ? 0 : 1;   // 홀+홀, 짝+짝 = 짝(0), 홀+짝=홀(1)
                int start = (odd || even) ? 0 : 1;
                for (int k = 0; k < 4; k++) {
//                    balls.add(new Ball(i, j, nm, nd + k * 2, ns));
                    balls.add(new Ball(i, j, nm, start + k * 2, ns));
                }
            }
        }
    }

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
            balls.add(new Ball(r, c, m, d, s));
        }

        board = new Queue[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = new ArrayDeque<>();
            }
        }

        while (K-- > 0) {
            move();
            split();
        }

        int ans = 0;
        for (Ball ball : balls) {
            ans+=ball.m;
        }
        System.out.println(ans);
    }
}
class Ball {
    int r, c, m, d, s;

    Ball(int r, int c, int m, int d, int s) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.d = d;
        this.s = s;
    }
}

/*
* 파이어볼을 담고 있는 리스트는 큐보다 리스트가 요소 삭제 시 효율적
* */
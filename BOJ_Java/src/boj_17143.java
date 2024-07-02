import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_17143 {
    static int R, C, M;
    static int ans = 0;
    static Shark[][] board;
    static int[] dx = {-1, 0, 1, 0};    // 상, 좌, 우, 하
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new Shark[R][C];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            // 상하우좌 (1,2,3,4) > 상좌하우(0,1,2,3)
            if (d == 1) {
                d = 0;
            } else if (d == 4) {
                d = 1;
            }
            board[r - 1][c - 1] = new Shark(r - 1, c - 1, s, d, z);
        }

        for (int col = 0; col < C; col++) {
            // 낚시왕 이동 (상어 잡기)
            for (int row = 0; row < R; row++) {
                if (board[row][col] != null) {
                    ans += board[row][col].z;
                    board[row][col] = null;
                    break;
                }
            }

            // 상어 이동
            Queue<Shark> que = new LinkedList<>();
            for (int i = 0; i < R; i++) {   // 현재 상어들 큐에 모두 담기
                for (int j = 0; j < C; j++) {
                    if (board[i][j] != null) {
                        que.add(new Shark(i, j, board[i][j].s, board[i][j].d, board[i][j].z));
                    }
                }
            }

            board = new Shark[R][C];
            while (!que.isEmpty()) {
                Shark sh = que.poll();
                int speed = sh.s;

                // 나머지 연산으로 최소한의 이동거리만 구하기 (위치한 행,열*2하면 제자리로 돌아옴을 이용)
                if (sh.d ==0 || sh.d == 2){
                    speed %= (R-1)*2;
                }else speed %= (C-1)*2;

                // 최소이동만큼 움직이기
                for (int k = 0; k < speed; k++) {
                    int nr = sh.r + dx[sh.d];
                    int nc = sh.c + dy[sh.d];
                    if (nr < 0 || nr >= R || nc < 0 || nc >= C){
                        sh.r -= dx[sh.d];
                        sh.c -= dy[sh.d];
                        sh.d = (sh.d+2)%4;
                        continue;
                    }
                    sh.r = nr;
                    sh.c = nc;
                }

                // 이동 후
                if (board[sh.r][sh.c] != null) {    // 기존에 상어가 있고, 얘보다 크다면 잡아먹기
                    if (board[sh.r][sh.c].z < sh.z) {
                        board[sh.r][sh.c] = new Shark(sh.r, sh.c, sh.s, sh.d, sh.z);
                    }
                } else {    // 빈 공간이면 바로 상어 추가
                    board[sh.r][sh.c] = new Shark(sh.r, sh.c, sh.s, sh.d, sh.z);
                }
            }
        }
        System.out.println(ans);
    }
    static class Shark{
        int r, c, s, d, z;
        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}

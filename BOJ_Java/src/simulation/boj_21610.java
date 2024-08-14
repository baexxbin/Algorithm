package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 마법사 상어와 비바라기
* 문제 설명: 1,N행열이 연결되어있는 격자에서, 비구름을 생성하고 이동하기
* 설계
*   - 1,N행열 연결해서 이동하기 >> 구름 4영역 반복문으로 좌표값 정하기 (i+(d*s)+N)%N
*   - 위 좌표값들에 물++
*   - 각 4영역에서 대각선 방향에 물갯수 세고 그만큼 ++
*   - 현재 구름빼고, 물 2이상 구름 생김 >> 구름배열 만들어서 관리, 구름만들고 물-2 >> 위 과정 반복하며이동
* */
public class boj_21610 {

    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};      // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static Queue<Cloud> clouds = new LinkedList<>();

    // 구름 이동, 해당자리에 물++
    private static void move(int d, int s) {
        s %=N;
        for (Cloud cloud : clouds) {
            cloud.r = (cloud.r + (dx[d] * s) + N) % N;
            cloud.c = (cloud.c + (dy[d] * s) + N) % N;
            board[cloud.r][cloud.c]++;
        }
    }

    // 각 구름에서 물복사버그 진행 (대각선 물 바구니 카운트)
    private static void copyBug() {
        while (!clouds.isEmpty()) {
            Cloud cur = clouds.poll();
            for (int i = 1; i < 8; i+=2) {      // 대각선만 체크
                int nx = cur.r + dx[i];
                int ny = cur.c + dy[i];
                if (!(0<=nx && nx<N && 0<=ny && ny<N)) continue;
                if (board[nx][ny] > 0) board[cur.r][cur.c]++;
            }
            visited[cur.r][cur.c] = true;       // 현재 구름은 다음 구름 될 수 없음
        }
    }

    // 새로운 구름 생성
    private static void newCloud() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && board[i][j] >= 2) {
                    clouds.offer(new Cloud(i, j));
                    board[i][j] -= 2;
                }
            }
        }

        for (int i = 0; i < N; i++) {           // 이전 구름 체크 배열 초기화
            Arrays.fill(visited[i], false);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 구름 생성
        clouds.offer(new Cloud(N - 1, 0));
        clouds.offer(new Cloud(N - 1, 1));
        clouds.offer(new Cloud(N - 2, 0));
        clouds.offer(new Cloud(N - 2, 1));

        // 이동 명령
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            move(d, s);
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

    static class Cloud {
        int r, c;

        Cloud(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

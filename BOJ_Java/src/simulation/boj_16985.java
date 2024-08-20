package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* Maaaaaaaaaze
* 문제 설명: 5*5*5로 이뤄진 큐브의 임의의 꼭짓점에서 반대 꼭짓점까지 최소 이동횟수로 도달하기
* 설계
*   - 시간복잡도: 5!(판 순서)*4^5(각 면 회전)
*   1. 층 정하기
*   2. 각 층의 면의 방향 정하기
*   3. bfs돌리기 (시작, 끝1일 경우만)
* */
public class boj_16985 {
    static int[][][] board = new int[5][5][5];
    static int[][][] tmpBoard;
    static int[] floor = new int[5];
    static int ans = Integer.MAX_VALUE;
    static int[] dir = {0, 1, 2, 3};            // 0, 90, 180, 270도 회전
    static int[] dx = {-1, 1, 0, 0, 0, 0};      // 상하좌우위아래
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};

    private static void bfs() {
        Queue<Node> que = new ArrayDeque<>();
        boolean[][][] visited = new boolean[5][5][5];

        que.offer(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (cur.x == 4 && cur.y == 4 && cur.z == 4) {
                ans = Math.min(ans, cur.d);
                if (ans == 12){     // 12는 최소값으로 프로그램 종료
                    System.out.println(ans);
                    System.exit(0);
                }
            }
            for (int i = 0; i < 6; i++) {
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];
                int nz = cur.z+dz[i];
                if (!(0<=nx && nx<5 && 0<=ny && ny<5 && 0<=nz && nz<5)) continue;
                if (!visited[nz][nx][ny] && tmpBoard[nz][nx][ny]==1){
                    que.offer(new Node(nx, ny, nz, cur.d + 1));
                    visited[nz][nx][ny] = true;
                }
            }
        }
    }
    private static void makeBoard(int idx, int z, int d) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                switch (d) {
                    case 0:
                        tmpBoard[idx][i][j] = board[z][i][j];
                        break;
                    case 1:
                        tmpBoard[idx][j][4-i] = board[z][i][j];
                        break;
                    case 2:
                        tmpBoard[idx][4-i][4-j] = board[z][i][j];
                        break;
                    case 3:
                        tmpBoard[idx][4-j][i] = board[z][i][j];
                        break;
                }
            }
        }
    }

    private static void rotate(int depth, int[] state) {
        if (depth == 5) {
            // 회전이 정해진 후, 최종 배열 만들기 tmpBoard
            tmpBoard = new int[5][5][5];
            for (int idx = 0; idx < 5; idx++) {         // idx: 층
                int z = floor[idx];                      // 현재 층에 해당하는 면
                int d = state[idx];                     // 현재 층의 방향 값
                makeBoard(idx, z, d);                   // 현재 층에 해당하는 배열 만들기
            }

            if (tmpBoard[0][0][0] == 1 && tmpBoard[4][4][4] == 1) {     // 입구와 출구가 갈 수 있는 경우
                bfs();
            }
            return;
        }

        // 층(depth)에 해당하는 판 돌리기
        for (int i = 0; i < 4; i++) {
            state[depth] = dir[i];      // 현재 층의 회전 방향 설정
            rotate(depth+1, state);
        }
    }

    // 층 정하기 (순열), floor[]에 각 층(인덱스)에 해당하는 배열 번호 들어있음
    private static void setFloor(int depth, boolean[] visited) {
        if (depth == 5) {
            int[] state = new int[5];
            rotate(0, state);       // 층이 다 정해졌으면 각 층의 회전 구하기
            return;
        }

        for (int i = 0; i < 5; i++) {
            if (!visited[i]){
                visited[i] = true;
                floor[depth] = i;
                setFloor(depth + 1, visited);
                visited[i] = false;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < 5; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 5; j++) {
                    board[k][i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        boolean[] visited = new boolean[5];
        setFloor(0, visited);
        System.out.println((ans == Integer.MAX_VALUE) ? -1 : ans);
    }
    static class Node{
        int x, y, z, d;

        Node(int x, int y, int z, int d) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.d = d;
        }
    }
}

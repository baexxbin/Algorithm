package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16933 {
    static int N, M, K;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int INF = Integer.MAX_VALUE;
    static int ans = INF;

    private static void bfs() {
        Queue<Node> que = new ArrayDeque<>();
        int[][][] visited = new int[N][M][K + 1];
        for (int i = 0; i < N; i++) {
            for (int j=0; j<M; j++){
                Arrays.fill(visited[i][j], INF);
            }
        }
        que.add(new Node(0, 0, K, 1));      // t: 시작일도 포함이니까 1
        visited[0][0][K] = 1;                           // 부실 수 있는 갯수 K채우고 시작

        int day;
        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (cur.x == N - 1 && cur.y == M - 1) {     // 목적지 도달하면 최소값 갱신 (빨리 도착한 값이 최단경로 값)
                ans = Math.min(ans, cur.t);
                continue;
            }

            day = cur.t % 2;            // 시간t는 계속 흐르고 이에 따라 밤낮이 결정됨
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!isValid(nx, ny)) continue;
                // 0이면, 밤낮 상관없이 감
                if (board[nx][ny]==0 && visited[nx][ny][cur.k] > cur.t){
                    que.add(new Node(nx, ny, cur.k, cur.t+1));
                    visited[nx][ny][cur.k] = cur.t;
                }
                // 1일때, 벽 뿌술수 있을때 (부술횟수k가 남아있고, 부수고 갈 위치가 방문안했을경우)
                if (board[nx][ny] == 1 && cur.k > 0 && visited[nx][ny][cur.k-1] > cur.t) {
                    if (day==1){       // 낮이면 바로 부수고 감
                        que.add(new Node(nx, ny, cur.k - 1, cur.t+1));
                        visited[nx][ny][cur.k-1] = cur.t;
                    }else{
                        que.add(new Node(cur.x, cur.y, cur.k, cur.t+1));      // 현재 상태에서 밤낮만 바뀐채 하루기다림
                    }
                }
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                board[i][j] = line.charAt(j)-'0';
            }
        }

        bfs();
        System.out.println(ans==INF ? -1 : ans);
    }
    static class Node{
        int x, y, k, t;        // x,y좌표, 벽 부순 갯수, 시간(날짜)

        public Node(int x, int y, int k, int t) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.t = t;
        }
    }
}

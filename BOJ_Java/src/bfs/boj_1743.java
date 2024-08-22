package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 음식물 피하기
* 문제 설명: 가장 큰 음식물 구하기
* 설계
*   - bfs로 가장 큰 음식물 크기 구하기
* */
public class boj_1743 {
    static int N,M,K;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static int bfs(int x, int y) {
        Queue<Node> que = new ArrayDeque<>();
        que.offer(new Node(x, y));
        visited[x][y] = true;

        int dist = 1;
        while (!que.isEmpty()) {
            Node cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!(0<=nx && nx<N && 0<=ny && ny<M)) continue;
                if (board[nx][ny] == 1 && !visited[nx][ny]) {
                    que.offer(new Node(nx, ny));
                    visited[nx][ny] = true;
                    dist++;
                }
            }
        }
        return dist;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            board[r][c] = 1;
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j]==1 && !visited[i][j]){
                    ans = Math.max(ans, bfs(i, j));
                }
            }
        }
        System.out.println(ans);

    }
    static class Node{
        int x,y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

package bfs;

/*
 * 공주님을 구해라!
 * 구현: Min(그냥 bfs로 공주님, 그람 구하기, 그람에서 최단거리로 공주님 구하기)
 * 10:21 - 11:11
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_17836 {
    static int N, M, T;
    static int[][] matrix;
    static Node gram;
    static int ans = Integer.MAX_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static void bfs(int x, int y) {
        Queue<Node> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        que.add(new Node(x, y, 0));
        visited[x][y] = true;

        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (cur.x == N - 1 && cur.y == M - 1) {
                ans = cur.d;
                break;
            }
            if (cur.x== gram.x && cur.y== gram.y) gram.d = cur.d;       // 그람 파악하면 걸린 거리 설정

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!(0<=nx&&nx<N && 0<=ny&&ny<M)) continue;
                if (!visited[nx][ny] && matrix[nx][ny] != 1) {
                    que.offer(new Node(nx, ny, cur.d + 1));
                    visited[nx][ny] = true;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if (matrix[i][j]==2) gram = new Node(i, j, 0);      // 그람 위치 파악
            }
        }

        // bfs로 공주님이랑 그람 최단 거리 찾기
        bfs(0, 0);

        // 그람을 찾을 경우, 그람거리+맨하튼 거리로 공주님 바로가기
        int gram2princ = (gram.d == 0 ? Integer.MAX_VALUE : gram.d + Math.abs(N - 1 - gram.x) + Math.abs(M - 1 - gram.y));
        ans = Math.min(ans, gram2princ);

        System.out.println((ans <= T ? ans : "Fail"));

    }
    static class Node{
        int x, y,d;

        Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
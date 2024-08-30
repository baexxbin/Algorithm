package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 점프 게임
* 문제 설명
*   - N*2로 이뤄짐, 1) 앞, 뒤, 점프(i+k) 이동 가능, 위험한칸 이동 불가
*   - i초마다 i칸 사라짐 (사람 움직이고 사라짐)
*   - N번 칸보다 더 큰 칸으로 이동시 클리어, 클리어 여부 구하기
* */
public class boj_15558 {
    static int N, K;
    static int[][] board;
    static boolean flag = false;

    private static boolean bfs() {
        int[] dy = {-1, 1, K};

        Queue<Node> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[2][N];
        que.add(new Node(0, 0, 0));
        visited[0][0] = true;

        while (!que.isEmpty()){
            Node cur = que.poll();
            for (int i = 0; i < 3; i++) {
                int nx = cur.x;
                int ny = cur.y + dy[i];
                int time = cur.tm;
                if (i == 2) nx = cur.x^1;

                if (ny >=N) return true;
                if (ny <= time || visited[nx][ny] || board[nx][ny]==0) continue;
                visited[nx][ny] = true;
                que.add(new Node(nx, ny, time + 1));
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[2][N];
        for (int i = 0; i < 2; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println((bfs() ? 1 : 0));
    }
    static class Node{
        int x, y, tm;

        Node(int x, int y, int tm) {
            this.x = x;
            this.y = y;
            this.tm = tm;
        }
    }
}

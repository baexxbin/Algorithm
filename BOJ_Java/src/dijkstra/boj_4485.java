package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 녹색 옷 입은 애가 젤다지?
* */
public class boj_4485 {
    static int N;
    static int[][] board, dist;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int INF = Integer.MAX_VALUE;
    static int idx = 1;
    static StringBuilder sb = new StringBuilder();

    private static void dijkstra() {
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, board[0][0]));
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.x == N - 1 && cur.y == N - 1) {
                sb.append("Problem ").append(idx).append(": ").append(cur.cost).append('\n');
                idx++;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!(0<=nx && nx<N && 0<=ny && ny<N)) continue;
                int newCost = cur.cost+board[nx][ny];

                if (newCost < dist[nx][ny]) {
                    dist[nx][ny] = newCost;
                    pq.offer(new Node(nx, ny, newCost));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N==0) break;

            board = new int[N][N];
            dist = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = INF;
                }
            }

            dijkstra();
        }

        System.out.println(sb);

    }
    static class Node implements Comparable<Node>{
        int x,y,cost;

        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}

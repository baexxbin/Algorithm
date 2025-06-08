import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1446 {
    static int N, D;
    static ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    static int[] dist;
    static int INF = Integer.MAX_VALUE;

    private static void dijsktra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));
        dist[0] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.c > dist[cur.v]) continue;

            // 일반 도로 이용
            if (cur.v + 1 <= D && cur.c + 1 < dist[cur.v + 1]) {
                dist[cur.v + 1] = cur.c + 1;
                pq.offer(new Node(cur.v + 1, cur.c + 1));
            }

            // 지름길 이용
            for (Node nxt : adj.get(cur.v)) {
                if (dist[cur.v] + nxt.c < dist[nxt.v]) {
                    dist[nxt.v] = dist[cur.v] + nxt.c;
                    pq.offer(new Node(nxt.v, dist[nxt.v]));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= D; i++) {
            adj.add(new ArrayList<>());
        }
        dist = new int[D+1];
        Arrays.fill(dist, INF);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (v > D) continue;
            adj.get(u).add(new Node(v, c));
        }


        dijsktra();
        System.out.println(dist[D]);

    }

    static class Node implements Comparable<Node>{
        int v, c;
        public Node(int v, int c) {
            this.v = v;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.c - o.c;
        }
    }
}

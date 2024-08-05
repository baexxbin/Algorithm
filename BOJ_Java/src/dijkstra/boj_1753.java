package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 13:50 - 14:17
public class boj_1753 {
    static int V, E, K;
    static ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    static int[] distance;
    static int INF = Integer.MAX_VALUE;

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.cost > distance[cur.idx]) continue;
            for (Node nxt : adj.get(cur.idx)) {
                if (distance[cur.idx] + nxt.cost < distance[nxt.idx]) {
                    distance[nxt.idx] = distance[cur.idx] + nxt.cost;
                    pq.offer(new Node(nxt.idx, distance[nxt.idx]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < V + 1; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj.get(u).add(new Node(v, w));
        }

        distance = new int[V + 1];
        Arrays.fill(distance, INF);

        dijkstra(K);

        for (int i = 1; i < V + 1; i++) {
            if (distance[i]==INF) sb.append("INF").append('\n');
            else sb.append(distance[i]).append('\n');
        }
        System.out.println(sb);
    }

    static class Node implements Comparable<Node>{
        int idx;
        int cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            int cmp = Integer.compare(this.cost, o.cost);
            if (cmp==0) return Integer.compare(this.idx, o.idx);
            return cmp;
        }
    }
}

package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 문제 설명: v1,v2를 거쳐 1번 정점에서 N번 정점으로 가는 최단거리 구하기

public class boj_1504 {
    static int N, E, V1, V2;
    static ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    static int INF = 200000000;
    static int[] dist;
    static int[] dist1;
    static int[] dist2;

    private static void dijkstra(int start, int[] dist) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(dist, INF);
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.cost > dist[cur.idx]) continue;
            for (Node nxt : adj.get(cur.idx)) {
                if (dist[cur.idx] + nxt.cost < dist[nxt.idx]) {
                    dist[nxt.idx] = dist[cur.idx] + nxt.cost;
                    pq.offer(new Node(nxt.idx, dist[nxt.idx]));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj.get(a).add(new Node(b, c));
            adj.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        V1 = Integer.parseInt(st.nextToken());
        V2 = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        dist1 = new int[N + 1];
        dist2 = new int[N + 1];

        dijkstra(1, dist);
        dijkstra(V1, dist1);
        dijkstra(V2, dist2);

        // 1 > V1 > V2 > N, 1 >V2 > V1 > N 중 최소값 구하기
        int mn = Math.min(dist[V1] + dist1[V2] + dist2[N], dist[V2] + dist2[V1] + dist1[N]);
        int ans = mn>=INF ? -1 : mn;
        System.out.println(ans);
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
            return Integer.compare(this.cost, o.cost);
        }
    }
}

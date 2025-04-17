package Kruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1368 {
    static int N;
    static int[] parents;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    private static int find(int x) {
        if (parents[x] !=x){
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }

    private static void union(int a, int b) {
        if (a < b) {
            parents[b] = a;
        }else {
            parents[a] = b;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        parents = new int[N+1];

        for (int i = 0; i < N+1; i++) {
            parents[i] = i;
        }

        for (int i = 1; i < N+1; i++) {
            int c = Integer.parseInt(br.readLine());
            pq.offer(new Node(0, i, c));
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int c = Integer.parseInt(st.nextToken());
                if (i==j) continue;
                pq.offer(new Node(i, j, c));
            }
        }

        int ans = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int p_u = find(cur.u);
            int p_v = find(cur.v);
            if (p_u !=p_v) {
                union(p_u, p_v);
                ans+=cur.cost;
            }
        }

        System.out.println(ans);

    }

    static class Node implements Comparable<Node>{
        int u, v, cost;

        Node(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

}

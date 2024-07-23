import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_17835 {
    static int N, M, K;
    static ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    static PriorityQueue<Integer> place = new PriorityQueue<>();
    static Long[] dist;
    static Long INF = Long.MAX_VALUE;   // 가중치의 합이 Integer.MAX_VALUE를 넘어설 수 있음
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }

        // 단방향 그래프 (지역-> 면접장이 무조건있음) >> 면접장에서 어떤 지역까지는 무조건 갈 수 있다로 바꿔서 풀기, u,v,바꿔서 넣기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj.get(v).add(new Node(u, c));
        }

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()){
            place.offer(Integer.parseInt(st.nextToken()));      // 특정 면접장으로부터 갈 수 있는 최단거리를 구하는 것이므로 한꺼번에 다익스트라
        }

        dijkstra();

        Node mx = new Node(0, 0);
        for (int i = 1; i < N + 1; i++) {
            if (dist[i] > mx.cost) {
                mx.cost = dist[i];
                mx.idx = i;
            }
        }

        sb.append(mx.idx).append('\n').append(mx.cost);
        System.out.println(sb);
    }

    static void dijkstra() {
        PriorityQueue<Node> que = new PriorityQueue<>();
        dist = new Long[N + 1];
        Arrays.fill(dist, INF);

        for (int start : place) {
            que.offer(new Node(start, 0));
            dist[start] = 0L;
        }

        while (!que.isEmpty()) {
            Node cur = que.poll();

            if (cur.cost > dist[cur.idx]) continue;
            for (Node nxt : adj.get(cur.idx)) {
                if (dist[nxt.idx] > dist[cur.idx] + nxt.cost) {
                    dist[nxt.idx] = dist[cur.idx] + nxt.cost;
                    que.add(new Node(nxt.idx, dist[nxt.idx]));
                }
            }
        }

    }

    static class Node implements Comparable<Node> {
        int idx;
        long cost;

        Node(int idx, long cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }
}

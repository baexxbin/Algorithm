import java.util.*;

// start부터 target 1, 2로 가는 최단경로 구하기
// 이때 최단경로에서 겹치는 부분까지가 합승하는 부분
// 12:40
public class lv3_합승택시요금 {
    static ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};

        System.out.println(solution(n,s,a,b,fares));
    }

    private static int[] dijkstra(int n, int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> que = new PriorityQueue<>();
        que.offer(new Node(start, 0));

        while (!que.isEmpty()) {
            Node cur = que.poll();

            if (cur.cost > dist[cur.idx]) continue;

            for (Node nxt : adj.get(cur.idx)) {
                if (dist[nxt.idx] > dist[cur.idx] + nxt.cost) {
                    dist[nxt.idx] = dist[cur.idx] + nxt.cost;

                    que.offer(new Node(nxt.idx, dist[nxt.idx]));
                }
            }
        }
        return dist;
    }

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] f : fares) {
            int u = f[0];
            int v = f[1];
            int c = f[2];
            adj.get(u).add(new Node(v, c));
            adj.get(v).add(new Node(u, c));
        }

        int[] start = dijkstra(n, s);   // 출발지에서 모든지점까지 최단거리 비용 >> 출발부터 i지점까지 합승하는 비용
        int[] startA = dijkstra(n, a);  // 자기집부터 거꾸로 모든 i지점까지 최단거리비용 >> 특정i지점부터 자기집까지 따로 가는 비용
        int[] startB = dijkstra(n, b);

        int ans = INF;
        for (int i = 1; i < n + 1; i++) {   // 가능한 모든 합승지점i 찾기
            ans = Math.min(ans, start[i]+startA[i]+startB[i]);
        }

        return ans;

        // start[i]: 출발지 s에서 합승종료지점i까지 최단거리비용 (합승해서 i까지 이동)
        // startA[i]: 합승종료지점i에서 목표지점 a까지 최단 거리 비용 (합승 후 a집까지 따로 이동)
        // startB[i]: 합승종료지점i에서 목표지점 b까지 최단 거리 비용 (합승 후 b집까지 따로 이동)
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

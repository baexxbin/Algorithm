package dijkstra;

// 문제 설명
// : N개의 마을에서 X마을로 최단 거리로 왔다가 되돌아가기 (단방향 그래프)
//   이때 가장 많은 시간을 소비한 사람

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 생각 과정
// : 간선의 방향을 바꿔 M->N의 최단거리*2 >> 방향바꿔 M->N또한 N에서 M으로 가는 최단거리 (잘못된 접근)
// : 정방향adj에서 X >> 각자 집으로 되돌아가는 최단거리
// : 역방향ajd에서 X >> 파티장으로 모이는 최단거리
public class boj_1238 {
    static int N, M, X;
    static ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    static ArrayList<ArrayList<Node>> rAdj = new ArrayList<>();
    static int[] distance;
    static int[] rDistance;
    static int INF = Integer.MAX_VALUE;

    private static void dijkstra(int start, int[] distance, ArrayList<ArrayList<Node>> adj) {
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

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
            rAdj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            adj.get(u).add(new Node(v, t));
            rAdj.get(v).add(new Node(u, t));
        }

        distance = new int[N + 1];
        rDistance = new int[N + 1];
        Arrays.fill(distance, INF);
        Arrays.fill(rDistance, INF);

        dijkstra(X, distance, adj);
        dijkstra(X, rDistance, rAdj);

        int mx = 0;
        for (int i = 1; i < N + 1; i++) {
            mx = Math.max(mx, distance[i] + rDistance[i]);
        }

        System.out.println(mx);
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

// 시간복잡도: (N+M)logN
// 최장 경로(distance 최대값): 특정경로가 모든 정점 포함 >> 최대간선비용 * 최대정점수-1 >> 100*(1000-1)

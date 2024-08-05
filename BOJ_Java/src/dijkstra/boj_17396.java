package dijkstra;

// 문제 설명: 현재노드에서 다른 노드까지 최소 시간 + 갈 수 있는지 여부
// 핵심 개념: 다익스트라, 갈 수 있는지 여부 체크

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 15:18 - 15:39
public class boj_17396 {
    static int N, M;
    static ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    static int[] visibility;
    static long[] distance;
    static long INF = Long.MAX_VALUE;

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));
        distance[0] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.cost > distance[cur.idx]) continue;
            for (Node nxt : adj.get(cur.idx)) {
                if (visibility[nxt.idx]==1) continue;
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

        visibility = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            visibility[i] = Integer.parseInt(st.nextToken());
        }
        visibility[N-1] = 0;    // 최종 목적지 방문 가능하도록 설정

        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            adj.get(a).add(new Node(b, t));
            adj.get(b).add(new Node(a, t));
        }

        distance = new long[N];
        Arrays.fill(distance, INF);

        dijkstra();
        long ans = distance[N-1] == INF ? -1 : distance[N-1];
        System.out.println(ans);
    }

    static class Node implements Comparable<Node>{
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

// 시간복잡도: O((N+M)logN)
// distance배열 범위 초과 >> 간선10만개를 10만 비용으로 10만-1간선 건너서 도착 >> 100000*10000-1 = 10^10

// 중요 개념: 특정 노드에서 어떤 노드까지 최단 거리, 배열 범위 초과 유의하기

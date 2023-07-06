import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1238 {
    static ArrayList<Node>[] adj;
    static ArrayList<Node>[] reversed_adj;
    static int[] dist;
    static int[] reversed_dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        reversed_adj = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
            reversed_adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            adj[v].add(new Node(u, t));
            reversed_adj[u].add(new Node(v, t));
        }

        dist = new int[N + 1];
        reversed_dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(reversed_dist, Integer.MAX_VALUE);

        dijkstra(adj, dist, X);
        dijkstra(reversed_adj, reversed_dist, X);

        int mx = 0;
        for (int i = 1; i <= N; i++) {
            mx = Math.max(mx, dist[i] + reversed_dist[i]);
        }
        System.out.println(mx);
    }

    public static void dijkstra(ArrayList<Node>[] graph, int[] check, int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        check[start] = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node nxt : graph[cur.idx]) {
                if (check[nxt.idx] > check[cur.idx] + nxt.weight) {   // 더 작은 경로로 갱신
                    check[nxt.idx] = check[cur.idx] + nxt.weight;
                    queue.add(new Node(nxt.idx, check[nxt.idx]));
                }
            }
        }
    }
}
class Node implements Comparable<Node>{
    int idx;
    int weight;

    Node(int idx, int weight) {
        this.idx = idx;
        this.weight = weight;
    }

    // 우선순위 큐 객체 정렬을 위한 compareTo
    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

}

/**
 * 다익스트라 : 특정한 하나의 정점에서 다른 모든 정점으로 가는 최단 경로
 * 파티 장소(X)를 출발점으로 각 지점에 대한 최단경로 구하기 => 역방향 그래프
 * 정방향 : 특정 노드에서 나머지 노드까지 최단거리
 * 역방향 : 나머지 노드들에서 특정 노드까지 최단거리
 */
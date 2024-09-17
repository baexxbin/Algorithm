
/*
* 도시 분할 계획
* 문제 설명: 양방향 가중치 그래프, 두개의 마을로 분리하기, 분리된 마을끼리 길 없애기, 마을안 집간 경로 존재하게하며 길 없애기, 길의 유지비 합 최소로
* 구현: 크루스칼(최소신장트리)O(ElogE)로 최소비용 갖는 그래프 만들기
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1647 {
    static int N, M;
    static List<Node> adj = new ArrayList<>();
    static int[] parent;

    private static int find(int x) {
        if (x != parent[x])
            parent[x] = find(parent[x]);
        return parent[x];
    }

    private static void union(int x, int y) {
        if (x>y) parent[y] = x;
        else parent[x] = y;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj.add(new Node(a, b, c));
        }

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        adj.sort(Comparator.naturalOrder());

        List<Integer> ans = new ArrayList<>();
        for (Node node : adj) {
            int p_u = find(node.u);
            int p_v = find(node.v);
            if (p_u != p_v) {
                union(p_u, p_v);
                ans.add(node.cost);
            }
        }

        int mx = Integer.MIN_VALUE;
        int sum = 0;
        for (int x : ans) {
            if (x > mx) mx = x;
            sum+=x;
        }
        System.out.println(sum-mx);
    }

    static class Node implements Comparable<Node>{
        int u;
        int v;
        int cost;

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

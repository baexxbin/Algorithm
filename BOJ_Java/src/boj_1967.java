import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 트리의 지름
 * 임의의 점(루트)에서 가장 먼 정점(tmpNode) 구하기
 * tmpNode에서 가장 먼 정점 구하기
 * tmpNode와 가장 먼 정점간의 거리가 '트리의 지름'
 */

public class boj_1967 {
    static class Node {
        int idx;
        int w;

        Node(int idx, int w) {
            this.idx = idx;
            this.w = w;
        }
    }
    static int N;
    static ArrayList<Node>[] tree;  // ArrayList배열로 트리 구현
    static boolean[] visited;
    static int mx = 0;
    static int tmpNode;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            tree[parent].add(new Node(child, weight));
            tree[child].add(new Node(parent, weight));
        }

        visited = new boolean[N + 1];
        dfs(1, 0);

        visited = new boolean[N + 1];
        dfs(tmpNode, 0);

        System.out.println(mx);

    }

    public static void dfs(int cur, int dist){
        if (mx < dist) {
            mx = dist;
            tmpNode = cur;
        }

        visited[cur] = true;

        for (int i = 0; i < tree[cur].size(); i++) {
            Node nxt = tree[cur].get(i);
            if (!visited[nxt.idx]) {
                dfs(nxt.idx, nxt.w+dist);
                visited[nxt.idx] = true;
            }
        }
    }
}
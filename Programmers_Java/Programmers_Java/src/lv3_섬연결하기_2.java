import java.util.Arrays;

public class lv3_섬연결하기_2 {
    static int[] parent;

    private static int find(int x) {
        if (x!=parent[x]) parent[x] = find(parent[x]);
        return parent[x];
    }

    private static void union(int a, int b) {
        if (a <= b) parent[b] = a;
        else parent[a] = b;
    }
    public static int solution(int n, int[][] costs) {
        // mst를 위한 간선의 최소비용으로 정렬
        Arrays.sort(costs, ((o1, o2) -> o1[2] - o2[2]));

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int ans = 0;
        for (int[] c : costs) {
            int p_u = find(c[0]);
            int p_v = find(c[1]);
            if (p_u != p_v) {
                union(p_u, p_v);
                ans += c[2];
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        // MST
        int n = 4;
        int[][] cost = {{0,1,1}, {0,2,2}, {1,2,5}, {1, 3, 1}, {2, 3, 8}};
        System.out.println(solution(n, cost));
    }
}

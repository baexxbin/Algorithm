import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class lv3_양과늑대 {
    static int[] left = new int[20];
    static int[] right = new int[20];
    static int[] val = new int[20];
    static int[] visited = new int[1<<17];
    static int mx = 1;
    static int N;

    private static void dfs(int state) {
        if (visited[state]==1) return;
        visited[state] = 1;

        int wolf = 0, total=0;
        for (int i = 0; i < N; i++) {         // 현재 상태에서 i번째로 가는것만 추가가능 (현재상태에서도 방문못한건 0으로 못감)
            if ((state & (1 << i)) != 0) {    // 아직 방문하지 않았으면
                total++;
                wolf += val[i];
            }
        }

        if (wolf*2 >= total) return;
        mx = Math.max(mx, total - wolf);

        // 다음 상태로 이동
        for (int i = 0; i < N; i++) {
            if ((state & (1<<i))==0) continue;
            if (left[i] != -1) dfs(state | (1<<left[i]));
            if (right[i] != -1) dfs(state | (1<<right[i]));
        }
    }
    public static int solution(int[] info, int[][] edges) {
        N = info.length;

        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        System.arraycopy(info, 0, val, 0, N);
        for (int i = 0; i < N - 1; i++) {
            int p = edges[i][0];
            int c = edges[i][1];
            if (left[p] == -1) left[p] = c;
            else right[p] = c;
        }

        dfs(1);
        return mx;
    }
    public static void main(String[] args) {
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
        solution(info, edges);
    }
}

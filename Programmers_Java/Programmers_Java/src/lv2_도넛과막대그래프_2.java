import java.util.Arrays;
import java.util.HashMap;

public class lv2_도넛과막대그래프_2 {
    static HashMap<Integer, int[]> graph = new HashMap<>();

    private static void cntInOutLink(int node, int type) {
        int[] cnt = graph.getOrDefault(node, new int[]{0, 0});      // cnt[0]: 진출차수, cnt[1]: 진입차수
        cnt[type]++;                // 해당하는 타입 값++
        graph.put(node, cnt);
    }
    public static int[] solution(int[][] edges) {
        int[] ans = {0, 0, 0, 0};              // 정점의 번호, 도넛, 막대, 8자

        // 노드들의 진입,진출 차수 세기
        for (int[] e : edges) {
            cntInOutLink(e[0], 0);      // 나가는 간선
            cntInOutLink(e[1], 1);      // 들어오는 간선
        }

        for (Integer key : graph.keySet()) {
            int[] degree = graph.get(key);
            if (degree[0] >=2 && degree[1]==0) ans[0] = key;        // 생성 정점
            else if (degree[0]==0 && degree[1] > 0) ans[2]++;       // 막대 그래프
            else if (degree[0]>=2 && degree[1] >=2) ans[3]++;       // 8자 그래프
        }
        ans[1] = graph.get(ans[0])[0] - ans[2] - ans[3];            // 중심에서 나간 간선 수(전체) - 막대 - 8자

        return ans;
    }
    public static void main(String[] args) {
        int[][] edges = {{2, 3}, {4, 3}, {1, 1}, {2, 1}};
        System.out.println(Arrays.toString(solution(edges)));
    }
}

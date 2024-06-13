import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class lv2_도넛과막대그래프 {
    private static Map<Integer, int[]> graph = new HashMap<>();
    public static int[] solution(int[][] edges) {
        int[] answer = {0,0,0,0};

        for (int i = 0; i < edges.length; i++) {
            int out = edges[i][0];
            int come = edges[i][1];

            countToGraph(out, 0);
            countToGraph(come, 1);
        }

        int[] counts;
        for (Integer key : graph.keySet()) {
            counts = graph.get(key);
            if (counts[0] >= 2 && counts[1] == 0) { // 생성된 정점
                answer[0] = key;
            } else if (counts[0] == 0 && counts[1] > 0) { // 막대 그래프
                answer[2]++;
            } else if (counts[0] >= 2 && counts[1] >= 2) { // 8자 그래프
                answer[3]++;
            }
        }
        answer[1] = graph.get(answer[0])[0] - answer[2] - answer[3];

        return answer;
    }

    private static void countToGraph(int node, int code) {
        int[] cnt = graph.getOrDefault(node, new int[] {0,0});      // node에 해당하는 값이 있으면 그 값을 반환하고, 없으면 기본값으로 [0, 0] 반환
        cnt[code]++;
        graph.put(node, cnt);
    }

    public static void main(String[] args) {
        int[][] edges = {{2,3}, {4,3}, {1,1}, {2,1}};
        System.out.println(Arrays.toString(solution(edges)));
    }
}

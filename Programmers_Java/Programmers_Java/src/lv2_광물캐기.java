import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class lv2_광물캐기 {
    public static void main(String[] args) {
        int[] picks = {0, 1, 1};
        String[] minerals = {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"};
        System.out.println(solution(picks, minerals));
    }
    public static int solution(int[] picks, String[] minerals) {
        // 광물 길이 조정하기
        if (Arrays.stream(picks).sum()*5 < minerals.length) {
            minerals = Arrays.copyOfRange(minerals, 0, Arrays.stream(picks).sum()*5);
        }

        // 광물에 따라 값 매기기
        int[] minerals_val = new int[minerals.length];
        HashMap<String, Integer> mapping = new HashMap<>(){{
            put("diamond", 25);
            put("iron", 5);
            put("stone", 1);
        }};

        for (int i = 0; i < minerals.length; i++) {
            minerals_val[i] = mapping.get(minerals[i]);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[1]-a[1]);
        for (int i = 0; i < minerals.length; i += 5) {
            int sum = 0;
            for (int j = i; j < Math.min(i + 5, minerals_val.length); j++) {
                sum += minerals_val[j];
            }
            pq.add(new int[]{i, sum});
        }

        int pick = 0;
        int fatigue = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            while (picks[pick] == 0) {
                pick++;
            }
            for (int i = cur[0]; i < Math.min(cur[0] + 5, minerals_val.length); i++) {
                int quo = (int) (minerals_val[i] / (Math.pow(5,2-pick)));
                fatigue += quo==0 ? 1 : quo;
            }
            picks[pick]--;
        }
        return fatigue;
    }
}

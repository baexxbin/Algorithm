import java.util.PriorityQueue;
import java.util.Queue;

public class lv2_더맵게 {
    public static int solution(int[] scoville, int K) {
        Queue<Integer> pq = new PriorityQueue<>();

        for (int sc : scoville) {
            pq.offer(sc);
        }

        int cnt = 0;
        while (!pq.isEmpty() && pq.size() >=2) {
            int cur = pq.poll();
            if (cur >=K) break;
            int cur2 = pq.poll();
            int nw = cur + (cur2 * 2);
            pq.offer(nw);
            cnt++;
        }
        if (!pq.isEmpty() && pq.peek() < K) return -1;
        return cnt;
    }
    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;

        System.out.println(solution(scoville, K));
    }
}

import java.util.Comparator;
import java.util.PriorityQueue;

public class lv3_야근지수_2 {
    public static long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        long ans = 0;
        for (int w : works) {
            pq.offer(w);
        }

        while (!pq.isEmpty() && n-- > 0) {
            Integer cur = pq.poll();
            cur--;
            if (cur>0) pq.offer(cur);
        }

        while (!pq.isEmpty()) {
            Integer cur = pq.poll();
            ans += Math.pow(cur, 2);
        }
        return ans;
    }
    public static void main(String[] args) {
        int n = 4;
        int[] works = {4, 3, 3};
        System.out.println(solution(n, works));
    }
}

/*
* 값 계산시에도 큐.poll 이용, 인덱스로 값 접근은 느림
* */
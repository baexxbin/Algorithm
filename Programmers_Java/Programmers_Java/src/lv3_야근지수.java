import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class lv3_야근지수 {
    public static void main(String[] args) {
        int[] works = {4,3,3};
        int n = 4;
        System.out.println(solution(n, works));
    }

    public static long solution(int n, int[] works) {
        long answer = 0;

        Queue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) {
            que.offer(work);
        }

        while (!que.isEmpty() && n>0) {
            int cur = que.poll();
            que.offer(--cur);
            n--;
        }

        while (!que.isEmpty()) {
            int cur = que.poll();
            if (cur<=0) continue;
            answer += Math.pow(cur, 2);
        }
        return answer;
    }
}

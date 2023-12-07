import java.util.PriorityQueue;
import java.util.Queue;

public class lv2_디펜스게임 {
    public static void main(String[] args) {
        int n = 7;
        int k = 3;
        int[] enemy = {4, 2, 4, 5, 3, 3, 1};

        System.out.println(solution(n, k, enemy));
    }
    public static int solution(int n, int k, int[] enemy) {

        if (k >= enemy.length) {
            return enemy.length;
        }

        Queue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < enemy.length; i++) {
            pq.add(enemy[i]);
            if (pq.size() > k) {
                int fight_enemy = pq.poll();
                if (n < fight_enemy) {
                    return i;
                }
                n -= fight_enemy;
            }
        }
        return enemy.length;
    }
}

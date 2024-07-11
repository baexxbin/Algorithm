import java.util.Comparator;
import java.util.PriorityQueue;

// 각 구간의 최댓값 중 최소값
// 각 구간의 최댓값 >> 건널뛸 수 있는 갯수인 k개의 묶음에서 제일 큰 값만 하나만 살아있어도 해당 묶음은 건너뛰기로 이동 가능
// 그 중 최소값 >> 이렇게 건널뛸 수 있는 기회 중 가장 작은 값만큼만 가능 (작은값 다 건너고 나면 못건너기에)
// 핵심, 각 모든 구간의 최대값을 어떻게 구할것인지 >> 최대힙
public class lv3_징검다리건너기 {
    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        System.out.println(solution(stones, k));
    }

    public static int solution(int[] stones, int k) {
        PriorityQueue<Integer[]> que = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return Integer.compare(o2[0], o1[0]);
            }
        });

        for (int i = 0; i < k; i++) {
            que.add(new Integer[]{stones[i], i});
        }

        int ans = que.peek()[0];
        for (int i = k; i < stones.length; i++) {
            que.add(new Integer[]{stones[i], i});

            while (!que.isEmpty() && que.peek()[1] <= i - k) {        // 현재 최대값이 현재 구간범위(i~k)를 벗어난 이전 값임(현재 구간범위보다 작거나 같음)
               que.poll();
            }
            ans = Math.min(ans, que.peek()[0]);
        }
        return ans;
    }
}

import java.util.*;
public class lv2_디펜스게임_2 {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int N = enemy.length;

        if(k>=N) return N;        // 다 무조건으로 이길 수 있을 경우

        for(int i=0; i<N; i++){
            pq.offer(enemy[i]);
            if(pq.size() > k){
                int weak = pq.poll();
                if(n-weak < 0) return i;
                n-=weak;
            }
        }
        return N;
    }
}

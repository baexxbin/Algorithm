
/*
* 1. 일단, 가장 멀리있는 집부터 배달해야함. (물건이있는 한 무조건 와야함, 왕복이니까, 먼 거리부터 해치움)
*    이를 편리하게 하기위해 누적합 사용. (i번째 시점으로부터 남아있는 배달/수거해야할 양)
* 2. 역순으로 배달 시작
*    현재 위치i에서 배달/수거해야할 물건이 현재 용량cap가지고 안되면 왕복해야함 (왕복횟수++), 현재위치 배달/수거를 다 완료할 수 있을때까지 반복
*    거리값 갱신은, 현재위치(거리)에서 왕복한 횟수
* 거꾸로 돌면서, n-1위치부터 커버가능한 왕복횟수를 세고있고, 앞으로 오면서 계속 커버가능한 왕복횟수 카운트 (누적합이기에 이전 처리내용까지 다 포함되어있음)
* */
public class lv2_택배배달과수거하기 {
    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        for (int i = n-2; i >=0; i--) {         // 누적합 구하기
            deliveries[i] += deliveries[i+1];
            pickups[i] += pickups[i+1];
        }

        int k = 0;      // 왕복 횟수
        long ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (deliveries[i] > cap * k || pickups[i] > cap * k) {   // 현재 위치에서 배달과 수거가 모두 완료되기위한 왕복횟수를 구해야함
                ans += (i+1)* 2L;         // 총거리 갱신: 현재위치에서 왕복 일어남. 해당 내용 더해줌
                k++;                    // 왕복횟수 업데이트
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int cap = 4;
        int n = 5;
        int[] deliveries = {1, 0, 3, 1, 2};
        int[] pickups = {0, 3, 0, 4, 0};
        System.out.println(solution(cap, n, deliveries, pickups));
    }
}

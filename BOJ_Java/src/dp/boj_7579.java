package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 앱
* 문제 설명: 활성화 된 앱, 사용 중인 메모리, 재실행 비용c, 새로운 앱이 필요한 메모리m가 주어질때,
*          비활성화 했을때의 총 재실행비용 c를 최소화하여 필요한 메모리m 확보하기
* 구현
*   - m,c기준 정렬(그리디)는 최적해를 보장X
*   - DP로 풀기: dp[i] = k, i비용으로 확보할 수 있는 최대 메모리k
* */
public class boj_7579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] mem = new int[N];
        int[] cost = new int[N];
        int totalCost = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            mem[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            totalCost += cost[i];
        }

        int[] dp = new int[totalCost + 1];
        for (int i = 0; i < N; i++) {       // i번째 앱부터 확인
            for (int j = totalCost; j >= cost[i]; j--) {      // 전체비용부터 거꾸로 돌면서 dp값 업데이트 (갱신값 덮어쓰기 안하기위해 역순)
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + mem[i]);      // i번째 선택안한 그냥 현재dp값 vs i번째 앱을 선택했을때 값
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < totalCost + 1; i++) {
            if (dp[i] >=M) ans = Math.min(ans, i);
        }
        System.out.println(ans);
    }
}

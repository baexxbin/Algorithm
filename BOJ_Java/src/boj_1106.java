import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// d[i] : i명의 고객을 늘릴 때 필요한 최소비용
public class boj_1106 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] dp = new int[C + 101];
        Arrays.fill(dp, 987654321);     // Integer.MAX_VALUE로 초기화 시 연산과정에서 오버플로우 발생

        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());

            for (int j = people; j < C + 101; j++) {
                dp[j] = Math.min(dp[j], cost + dp[j - people]);
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = C; i < C + 101; i++) {     // 최소 목표 고객 수 C 일때 부터 가능한 최소비용 확인
            result = Math.min(result, dp[i]);
        }
        System.out.println(result);
    }
}

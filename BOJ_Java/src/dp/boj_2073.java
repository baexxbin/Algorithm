package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 길이 D의 수도관 만들기
* 수도관 용량: 수도관을 이루는 파이프의 용량 중 최소 값
* 수도관 길이: 파이프들의 길이 총합
* */
public class boj_2073 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int D = Integer.parseInt(st.nextToken());           // 목표 수도관 길이
        int P = Integer.parseInt(st.nextToken());           // 파이프 개수

        int[] len = new int[P + 1];
        int[] cost = new int[P + 1];

        for (int i = 1; i <= P; i++) {
            st = new StringTokenizer(br.readLine());
            len[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[D + 1];
        dp[0] = Integer.MAX_VALUE;
        for (int i = 1; i <= P; i++) {
            for (int j = D; j >= len[i]; j--) {
                dp[j] = Math.max(dp[j], Math.min(dp[j - len[i]], cost[i]));
            }
        }
        System.out.println(dp[D]);
    }
}

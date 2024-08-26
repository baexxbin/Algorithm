package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
* 문자열 제거
* 문제 설명: 문자열을 지워서 얻을 수 있는 최대 점수 구하기
* 구현: 그냥 지우는게 나은지, 뭉탱이로 지우는게 이득인지 >> DP
* */
public class boj_21941 {
    static String S;
    static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        S = br.readLine();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        int[] dp = new int[S.length() + 1];

        for (int i = 1; i < S.length()+1; i++) {
            dp[i] = dp[i - 1] + 1;      // 그냥 하나 지울때

            for (int len = 1; len <= i; len++) {     // i번째 문자열까지로 만들 수 있는 부분 문자 만들기
                String sub = S.substring(i - len, i);
                if (map.containsKey(sub)) {
                    dp[i] = Math.max(dp[i], dp[i - len] + map.get(sub));
                }
            }
        }
        System.out.println(dp[S.length()]);
    }
}

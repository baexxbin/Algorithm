package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_15486 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[] T = new int[N+1];       // 걸리는 기간
        int[] P = new int[N+1];       // 버는 돈

        for (int i = 1; i <= N; i++) {                  // i번째가 상담 시작일
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 2];                      // 날짜N부터 시작하고, 이때 N+1값을 참조할 수 있음
        for (int i = N; i >=1; i--) {                   // dp의 형태가 계속 t[i]이후 값을 참조함. 갱신되면 안됨, 뒤에서부터 반복문 진행 (중복불가1차원dp라 그런가)
            if (i + T[i] > N+1) dp[i] = dp[i+1];          // 상담 불가(퇴사 이후 끝남)시 다음날의 최대값 사용(현재는 건너뛰니까 다음날 약속없고, 그러니까 다음날의 최대값이 현재 최대값됨), 상담종로는 N+1일까지 받을 수 있음(퇴사하는날까지 일함)
            else dp[i] = Math.max(dp[i + 1], P[i] + dp[i + T[i]]);      // 오늘 상담안하거나 하거나. 할 경우 "오늘 상담했을때 버는 돈P[i] + 오늘 상담이 끝난 날 벌 수 있는 최대값dp[i + T[i]]"
        }
        System.out.println(dp[1]);                      // 첫날부터 N날까지 얻을 수 있는 최대 수익, 역순으로 저장되었음
    }
}

/*
* 시작일이 연속적인 날짜에 대해, 각각 상담 선택여부를 선택하며 최대값 찾아나감
* */

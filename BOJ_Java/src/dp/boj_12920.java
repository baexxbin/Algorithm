package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_12920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());       // 물건 종류
        int M = Integer.parseInt(st.nextToken());       // 가방 최대 무게

        int[] dp = new int[M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());       // 물건 무게
            int C = Integer.parseInt(st.nextToken());       // 민호 만족도
            int K = Integer.parseInt(st.nextToken());       // 물건 개수

            // 물건의 개수를 이진분할로 조각 나누기(2의 거듭제곱)
            /*
            * 이진분할이, ex) 15:{1, 2, 4, 8}일때 이 4개의 수만 사용한다는 것이 아닌, 이진수처럼, 이 조합으로 모든 15조합을 만들 수 있는 것임
            * dp배열은 누적된 값을 저장하고 있기에, 이진분할로 만들 묶음의 조합으로 모든 경우의 수 만들 수 있음
            * */
            ArrayList<Integer> lst = new ArrayList<>();
            int q = 1;      // 부분조각 첫번째는 1로 시작

            while (K > 0) {
                if (q >K) q = K;    // 2거듭제곱으로 더이상 못가져가면 이전값의 나머지 가지기
                lst.add(q);
                K-=q;
                q*=2;
            }

            for (int num : lst) {
                int weight = V*num;
                int value = C*num;
                for (int j = M; j >= weight; j--) {     // 각 무게별 물건에 대해 중복불가로 진행(중복되는경우 이미 num으로 만들어놓음)
                    dp[j] = Math.max(dp[j], dp[j - weight] + value);
                }
            }
        }
        System.out.println(dp[M]);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_10942 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 회문 dp 만들기
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
        }

        for (int i = 0; i < N - 1; i++) {
            if (nums[i]==nums[i+1]){
                dp[i][i+1]=1;
            }
        }

        for (int ln = 2; ln < N; ln++) {
            for (int i = 0; i < N - ln; i++) {
                int j = i+ln;
                if (nums[i]==nums[j] && dp[i+1][j-1]==1){
                    dp[i][j] = 1;
                }
            }
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(dp[a-1][b-1]).append('\n');
        }
        System.out.println(sb);
    }
}

// System.out.println는 I/O로 비용이 많이든다. 따라서 sb로 한번에 한번의 출력으로 해결한다.
// StringBuilder는 변경 가능한 문자열을 만들어줌
// 문자열은 불변으로 새로운 문자열 객체가 생성되고 이전 문자열은 GC가 처, 많은 메모리 할당 해제는 성능저하
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// dp[i] = j, i번째 수 까지 읽을 수 있는 단어 수 j
public class boj_2011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String code = br.readLine();
        int[] dp = new int[code.length() + 1];
        dp[0] = 1;  // 초기값 설정
        dp[1] = 1;

        if (code.charAt(0) - '0' == 0) {    // 맨 첫 글자가 0일 경우 예외처리
            System.out.println(0);
            System.exit(0);
        }

        for (int i = 2; i <= code.length(); i++) {
            int one = code.charAt(i - 1) - '0';
            if (one != 0) {                     // 한 글자씩 읽을 경우 (그냥 일의자리만 보는 것으로 이전 값(dp[i-1])과 동일)
                dp[i] = dp[i-1] % 1000000;
            }

            int two = (code.charAt(i-2) - '0') * 10 + one;
            if (10<=two && two<27){
                dp[i] = (dp[i-2] + dp[i]) % 1000000;    // 두 자리 수로 읽을 경우 (두 자리를 숫자 하나로 봄으로, 그 전 값dp[i-2]에 경우가 더해짐
            }
        }
        System.out.println(dp[code.length()]);
    }
}

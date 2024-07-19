import java.util.Arrays;

public class lv3_거스름돈 {
    public static int solution(int n, int[] money) {
        Arrays.sort(money);
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int coin : money) {
            for (int i = coin; i < n + 1; i++) {
                dp[i] += dp[i-coin] % 1000000007;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 5;
        int[] money = {1, 2, 5};
        System.out.println(solution(n, money));
    }
}

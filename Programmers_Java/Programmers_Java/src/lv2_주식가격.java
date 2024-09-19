import java.util.Arrays;

// 자신보다 작은값 만나기까지 몇개인지 세기?
public class lv2_주식가격 {
    public static int[] solution(int[] prices) {
        int N = prices.length;

        int[] ans = new int[N];
        for (int i = N - 2; i >= 0; i--) {
            int cnt = 1;                                     // 가격이 떨어지지 않는 기간
            while (i+cnt < N && prices[i] <= prices[i+cnt]){ // 현재 값이 떨어지지 않을 동안
                if (ans[i+cnt] ==0) break;                   // 이용할 이전 값이 없음 >> 종료
                cnt += ans[i + cnt];                         // 값이 떨어지지 않은 동안(cnt)의 이전값 활용해 업데이트
            }
            ans[i] = cnt;
        }

        return ans;
    }
    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};
        System.out.println(Arrays.toString(solution(prices)));
    }
}

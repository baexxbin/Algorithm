// 자연수 n개로 이뤄진 중복 집합, 모든 원소의 합은 S
// 모든 원소의 곱이 최대인 배열 찾기

import java.util.Arrays;
public class lv3_최고의집합 {
    public static int[] solution(int n, int s) {
        if (s<n) return new int[]{-1};
        int div = s/n;
        int rm = s%n;

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {       // 몫 분배
            ans[i] = div;
        }

        for (int i = 0; i < rm; i++) {      // 나머지 거꾸로 분배
            ans[n-1-i]++;
        }

        return ans;
    }
    public static void main(String[] args) {
        int n = 4;
        int s = 15;
        System.out.println(Arrays.toString(solution(n, s)));
    }
}

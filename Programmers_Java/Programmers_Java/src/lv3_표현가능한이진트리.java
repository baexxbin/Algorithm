import com.sun.source.tree.IfTree;

import java.util.Arrays;

public class lv3_표현가능한이진트리 {

    private static boolean dfs(String sub) {
        int root = sub.length() / 2;

        // 종료 조건
        if (sub.length() ==1) return true;

        // 부모가 없는데 자식이 존재하면 이진트리 구성 못함
        if (sub.charAt(root)=='0' && (sub.substring(0, root).contains("1") || sub.substring(root+1).contains("1"))) return false;

        return dfs(sub.substring(0, root)) && dfs(sub.substring(root + 1));
    }

    private static String num2bin(long num) {
        StringBuilder bin = new StringBuilder(Long.toBinaryString(num));
        long len = bin.length();
        int n = 0;
        while ((1L << n) - 1 < len) {           // 2^n-1로 자리를 맞추기 위한 n찾기
            n++;
        }

        while (bin.length() < (1 << n) - 1) {   // 2^n-1로 길이 맞추기
            bin.insert(0, "0");
        }

        return bin.toString();
    }
    public static int[] solution(long[] numbers) {
        int N = numbers.length;
        int[] ans = new int[N];

        for (int i = 0; i < N; i++) {
            // 이진수로 만들기
            String bin = num2bin(numbers[i]);

            // 부모 자식 연결 확인
            ans[i] = dfs(bin) ? 1 : 0;
        }

        return ans;
    }
    public static void main(String[] args) {
        long[] numbers = {3, 7, 42, 5};
        System.out.println(Arrays.toString(solution(numbers)));
    }
}

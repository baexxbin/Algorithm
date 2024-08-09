package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 연산자 끼워넣기
// 연산자 조합 구해서, 조합당 최대,최소 결과값 구하기
public class boj_14888 {
    static int N;
    static int[] nums;
    static int mx = Integer.MIN_VALUE;
    static int mn = Integer.MAX_VALUE;

    private static void dfs(int depth, int ans, int[] op) {
        if (depth == N) {
            mx = Math.max(mx, ans);
            mn = Math.min(mn, ans);
            return;
        }

        if (op[0] > 0) {    // 덧셈
            op[0]--;
            dfs(depth+1, ans+nums[depth], op);
            op[0]++;
        }
        if (op[1] > 0) {    // 뺄셈
            op[1]--;
            dfs(depth+1, ans-nums[depth], op);
            op[1]++;
        }
        if (op[2] > 0) {    // 곱셈
            op[2]--;
            dfs(depth+1, ans*nums[depth], op);
            op[2]++;
        }
        if (op[3] > 0) {   // 나눗셈
            op[3]--;
            dfs(depth+1, ans/nums[depth], op);
            op[3]++;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        int[] operator = new int[4];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, nums[0], operator);
        System.out.println(mx);
        System.out.println(mn);
    }
}

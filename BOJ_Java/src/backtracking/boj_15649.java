package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// N과 M
public class boj_15649 {
    static int N, M;
    static int[] ans;
    static boolean[] check;
    static StringBuilder sb = new StringBuilder();

    private static void dfs(int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(ans[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = 1; i < N+1; i++) {
            if (check[i]) continue;
            ans[depth] = i;
            check[i] = true;
            dfs(depth+1);
            check[i] = false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = new int[M];
        check = new boolean[N + 1];

        dfs(0);
        System.out.println(sb);
    }
}

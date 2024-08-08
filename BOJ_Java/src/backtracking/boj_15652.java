package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// N과 M(4)

// 수열은 앞의 수와 같거나 큰 수로 구성되어야 함
// (M) depth: 끝나는 조건이자 dfs넘어가는 조건
// (N) 수열의 성립 조건은 dfs안에서 처리
public class boj_15652 {
    static int N, M;
    static int[] ans;
    static StringBuilder sb = new StringBuilder();

    private static void dfs(int depth, int last) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(ans[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = 1; i < N + 1; i++) {
            if (i < last) continue;
            ans[depth] = i;
            dfs(depth + 1, i);
        }


    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ans = new int[M];

        dfs(0, 0);
        System.out.println(sb);
    }
}

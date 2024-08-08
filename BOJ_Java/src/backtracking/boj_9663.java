package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_9663 {
    static int N;
    static boolean[] col;
    static boolean[] upDiagonal;
    static boolean[] downDiagonal;
    static int cnt = 0;

    private static void dfs(int row) {
        if (row==N) {
            cnt++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (col[i] || upDiagonal[row+i] || downDiagonal[row-i+N-1]) continue;
            col[i] = true;
            upDiagonal[row+i] = true;
            downDiagonal[row-i+N-1] = true;
            dfs(row+1);
            col[i] = false;
            upDiagonal[row+i] = false;
            downDiagonal[row-i+N-1] = false;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        col = new boolean[N];
        upDiagonal = new boolean[N*2-1];
        downDiagonal = new boolean[N*2-1];

        dfs(0);
        System.out.println(cnt);
    }
}

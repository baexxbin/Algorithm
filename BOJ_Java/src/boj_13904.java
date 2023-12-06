import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_13904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int mx_day = 0;
        Queue<int[]> que = new PriorityQueue<>((a,b) -> b[1]-a[1]);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            que.add(new int[]{d, w});
            mx_day = Math.max(mx_day, d);
        }

        int day = 0;
        int[] check = new int[mx_day+1];
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            if (cur[0] - day < 0) continue;

            int tmp = cur[0];
            while (tmp > 0 && check[tmp] != 0) {
                tmp-=1;
            }
            if (tmp <= 0) continue;
            check[tmp] = cur[1];
        }
        int ans = Arrays.stream(check).sum();
        System.out.println(ans);
    }
}

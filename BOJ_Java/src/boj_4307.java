import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_4307 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int mx =0;
            int mn =0;
            for (int j = 0; j < N; j++) {
                int ant = Integer.parseInt(br.readLine());
                int far = Math.max(ant, L - ant);
                int shrt = Math.min(ant, L - ant);
                mx = Math.max(mx, far);
                mn = Math.max(mn, shrt);
            }
            sb.append(mn).append(" ").append(mx).append('\n');
        }
        System.out.println(sb);
    }
}

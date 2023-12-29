import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            System.out.println(solve(y-x));
        }
    }

    public static int solve(int distance) {
        double root = Math.sqrt(distance);

        if (root % 1 == 0) {
            return (int)(2*root-1);
        }

        double nxt = Math.ceil(root);
        if (distance > Math.pow(nxt, 2) - nxt) {
            return (int) (2 * nxt - 1);
        }
        return (int) (2 * nxt - 2);
    }
}

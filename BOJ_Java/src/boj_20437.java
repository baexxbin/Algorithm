import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_20437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String w = br.readLine();
            int K = Integer.parseInt(br.readLine());

            if (K == 1) {
                System.out.println(1+" "+1);
                continue;
            }
            int[] alpha = new int[26];
            for (int i = 0; i < w.length(); i++) {
                alpha[w.charAt(i) - 'a']++;
            }

            int mn = Integer.MAX_VALUE;
            int mx = Integer.MIN_VALUE;
            for (int st = 0; st < w.length(); st++) {
                char cur = w.charAt(st);
                if (alpha[cur - 'a'] < K) continue;
                int cnt = 1;
                int end = st+1;
                while (end < w.length()) {
                    if (cur == w.charAt(end)) cnt++;
                    if (cnt == K) {
                        mn = Math.min(mn, end - st + 1);
                        mx = Math.max(mx, end - st + 1);
                        break;
                    }
                    end++;
                }
            }
            if (mx == Integer.MIN_VALUE || mn == Integer.MAX_VALUE) {
                System.out.println(-1);
                continue;
            }
            System.out.println(mn +" "+mx);
        }
    }
}

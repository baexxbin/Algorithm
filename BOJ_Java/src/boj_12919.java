import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_12919 {
    static String s,t;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        t = br.readLine();

        System.out.println(dfs(t));
    }

    public static int dfs(String t) {
        if (s.length() == t.length()) {
            if (s.equals(t)) {
                return 1;
            }
            return 0;
        }
        int ans = 0;
        if (t.charAt(0) == 'B') {
            String substring = t.substring(1);
            StringBuilder sb = new StringBuilder(substring);
            String string = sb.reverse().toString();
            ans += dfs(string);
        }

        if (t.charAt(t.length() - 1) == 'A') {
            ans += dfs(t.substring(0, t.length() - 1));
        }
        return ans > 0 ? 1 : 0;
    }
}

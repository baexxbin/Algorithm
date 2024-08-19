package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj_1759 {
    static int L, C;
    static char[] alpha;
    static List<Character> ans = new ArrayList<>();
    static StringBuilder res = new StringBuilder();
    static char[] vowels = {'a', 'e', 'i', 'o', 'u'};

    private static void dfs(int idx, int cnt) {
        if (cnt == L) {
            int vowelCnt = 0;
            for (char c : ans) {
                if (isVowel(c)) {
                    vowelCnt++;
                }
            }
            if (vowelCnt >= 1 && L - vowelCnt >= 2) {
                StringBuilder sb = new StringBuilder();
                for (char c : ans) {
                    sb.append(c);
                }
                res.append(sb.toString()).append('\n');
            }
            return;
        }

        for (int i = idx; i < C; i++) {
            ans.add(alpha[i]);
            dfs(i + 1, cnt + 1);
            ans.remove(ans.size() - 1);
        }
    }

    private static boolean isVowel(char c) {
        for (char vowel : vowels) {
            if (c == vowel) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        String[] line = br.readLine().split(" ");
        alpha = new char[C];

        for (int i = 0; i < C; i++) {
            alpha[i] = line[i].charAt(0);
        }

        Arrays.sort(alpha);

        dfs(0, 0);
        System.out.println(res);
    }

}

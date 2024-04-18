import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class boj_7490 {
    static int N;
    static List<String> ans;
    static String option[] = {" ", "+", "-"};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            ans = new ArrayList<>();
            dfs(1, "1");
            for (String s : ans) {
                System.out.println(s);
            }
            System.out.println();
        }
    }

    private static void dfs(int num, String s) {
        if (num==N){
            String express = s.replace(" ", "");
            if (cal(express)) {
                ans.add(s);
            }
            return;
        }
        for (int i = 0; i < 3; i++) {
            dfs(num+1, s+option[i]+Integer.toString(num+1));
        }
    }

    private static boolean cal(String express) {
        StringTokenizer st = new StringTokenizer(express, "+|-", true);
        int sum = Integer.parseInt(st.nextToken());
        while (st.hasMoreElements()) {
            String s = st.nextToken();
            if (s.equals("+")){
                sum += Integer.parseInt(st.nextToken());
            }else {
                sum -= Integer.parseInt(st.nextToken());
            }
        }
        if (sum == 0) {
            return true;
        }
        return false;
    }
}

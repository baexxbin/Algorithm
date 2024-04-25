import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1068 {
    static int N;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        parents = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            parents[i] = Integer.parseInt(st.nextToken());
        }

        int rm = Integer.parseInt(br.readLine());

        dfs(rm);

        int cnt = 0;
        for (int node = 0; node < N; node++) {
            if (parents[node]!=-2 && isLeaf(node)){
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static void dfs(int rm){
        parents[rm] = -2;
        for (int node = 0; node < N; node++) {
            if (parents[node] == rm) {
                dfs(node);
            }
        }
    }

    private static boolean isLeaf(int node) {
        for (int p : parents) {
            if (p == node) {
                return false;
            }
        }
        return true;
    }
}

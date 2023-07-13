import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1956 {
    static int V, E;
    static int[][] adj;
    static int ans;
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adj = new int[V+1][V+1];
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i != j) {
                    adj[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[a][b] = c;
        }

        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (adj[i][j] > adj[i][k] + adj[k][j]) {
                        adj[i][j] = adj[i][k] + adj[k][j];
                    }
                }
            }
        }

        ans = INF;
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i == j) {
                    continue;
                }
                if (adj[i][j] != INF && adj[j][i] != INF) {
                    ans = Math.min(ans, adj[i][j] + adj[j][i]);
                }
            }
        }

        System.out.println(ans == INF ? -1 : ans);
    }
}

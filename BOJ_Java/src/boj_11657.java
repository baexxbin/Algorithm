import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_11657 {
    static int N, M;
    static long[] dist;
    static Bus[] buses;
    static final Long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        buses = new Bus[M];
        dist = new long[N+1];
        Arrays.fill(dist, INF);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            buses[i] = new Bus(a, b, c);
        }

        if (bellmanFord(1)) {
            System.out.println(-1);
        }else {
            for (int i = 2; i < N + 1; i++) {
                if (dist[i] == INF) {
                    System.out.println(-1);
                    continue;
                }
                System.out.println(dist[i]);
            }
        }

    }

    public static boolean bellmanFord(int start) {
        dist[start] = 0;
        for (int i = 1; i < N+1; i++) {
            for (int j = 0; j < M; j++) {
                int cur = buses[j].cur;
                int nxt = buses[j].nxt;
                int cost = buses[j].cost;

                if (dist[cur] != INF && dist[nxt] > dist[cur] + cost) {
                    dist[nxt] = dist[cur]+cost;
                    if (i == N) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
class Bus{
    int cur;
    int nxt;
    int cost;

    public Bus(int cur, int nxt, int cost) {
        this.cur = cur;
        this.nxt = nxt;
        this.cost = cost;
    }
}
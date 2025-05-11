import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2655 {
    static int N;
    static Node[] nodes;
    static int[] dp;
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int b = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(i + 1, b, h, w);
        }

        dp = new int[N];        // i가 꼭대기일때 최대 높이
        dist = new int[N];
        Arrays.sort(nodes);
        Arrays.fill(dist, -1);

        for (int i = 0; i < N; i++) {
            dp[i] = nodes[i].h;
            for (int j = 0; j < i; j++) {     // j는 i 밑에 쌓일 블록들
                if (nodes[j].w > nodes[i].w) {
                    if (dp[i] < dp[j] + nodes[i].h) {
                        dp[i] = dp[j] + nodes[i].h;
                        dist[i] = j;
                    }
                }
            }
        }

        int mxh = 0;
        int mxIdx = 0;
        for (int i = 0; i < N; i++) {
            if (dp[i] > mxh) {
                mxh = dp[i];
                mxIdx = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        List<Integer> ans = new ArrayList<>();
        while (mxIdx != -1) {
            ans.add(nodes[mxIdx].idx);
            mxIdx = dist[mxIdx];
        }

        sb.append(ans.size()).append('\n');
        for (int idx : ans) {
            sb.append(idx).append('\n');
        }
        System.out.println(sb);
    }

    static class Node implements Comparable<Node> {
        int idx, b, h, w;

        public Node(int idx, int b, int h, int w) {
            this.idx = idx;
            this.b = b;
            this.h = h;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return o.b - this.b;
        }
    }
}

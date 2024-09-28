package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_16928 {
    static int N, M;
    static int[] board;
    static int[] visited;

    private static void bfs() {
        visited = new int[101];
        Queue<Integer> que = new ArrayDeque<>();
        que.add(1);
        Arrays.fill(visited, -1);
        visited[1] = 0;
        while (!que.isEmpty()) {
            int cur = que.poll();
            if (cur ==100) {
                break;
            }
            for (int i = 1; i <= 6; i++) {
                int nxt = cur + i;
                if (nxt > 100) continue;

                int nxtIdx = board[nxt];
                if (visited[nxtIdx] == -1) {
                    visited[nxtIdx] = visited[cur]+1;
                    que.add(nxtIdx);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[101];
        for (int i = 0; i < 101; i++) {
            board[i] = i;
        }

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a] = b;
        }

        bfs();
        System.out.println(visited[100]);
    }
}

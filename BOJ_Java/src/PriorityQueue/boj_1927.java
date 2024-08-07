package PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 09:00 - 09:13
public class boj_1927 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            long cmd = Long.parseLong(br.readLine());
            if (cmd == 0) {
                if (!pq.isEmpty()) sb.append(pq.poll()).append('\n');
                else sb.append('0').append('\n');
                continue;
            }
            pq.offer(cmd);
        }
        System.out.println(sb);
    }
}

package PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class boj_11279 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        PriorityQueue<Long> pq = new PriorityQueue<>(Comparator.reverseOrder());
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

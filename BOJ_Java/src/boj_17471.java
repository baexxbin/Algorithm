import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_17471 {
    static int N;
    static int[] peoples;
    static List<ArrayList<Integer>> group;
    static boolean[] checked;
    static boolean[] visited;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        peoples = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            peoples[i] = Integer.parseInt(st.nextToken());
        }

        group = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            group.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                group.get(i).add(Integer.parseInt(st.nextToken())-1);
            }
        }

        // 선거구 2개로 나누기
        checked = new boolean[N];
        ans = Integer.MAX_VALUE;
        split(0);
        ans = ans == Integer.MAX_VALUE ? -1 : ans;
        System.out.println(ans);

    }

    static void split(int idx) {
        if (idx == N) {
            List<Integer> area1 = new ArrayList<>();
            List<Integer> area2 = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                if (checked[i]){
                    area1.add(i);
                    continue;
                }
                area2.add(i);
            }
            if (area1.isEmpty() || area2.isEmpty()) {
                return;
            }

            // 각 선거구가 연결되어있는지 확인
            if (connection(area1) && connection(area2)) {
                int a = 0;
                int b = 0;
                for (int i = 0; i < N; i++) {
                    if (visited[i]) {
                        a += peoples[i];
                        continue;
                    }
                    b += peoples[i];
                }
                int sub = Math.abs(a-b);
                ans = Math.min(ans, sub);
            }
            return;
        }
        checked[idx] = true;
        split(idx + 1);
        checked[idx] = false;
        split(idx+1);
    }

    static boolean connection(List<Integer> area) {
        Queue<Integer> que = new LinkedList<>();
        visited = new boolean[N];
        que.offer(area.get(0));
        visited[area.get(0)] = true;

        int cnt = 1;
        while (!que.isEmpty()) {
            int cur = que.poll();
            for (int nxt : group.get(cur)) {
                if (area.contains(nxt) && !visited[nxt]) {
                    que.offer(nxt);
                    visited[nxt] = true;
                    cnt++;
                }
            }
        }
        return area.size() == cnt;
    }
}

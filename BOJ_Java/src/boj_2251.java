import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2251 {
    static int A,B,C;
    static List<Integer> ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        ans = new ArrayList<>();

        bfs();

        Collections.sort(ans);
        for (int a: ans){
            System.out.print(a+" ");
        }


    }
    static class Bottle{
        int a;
        int b;
        int c;

        public Bottle(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    static void bfs() {
        Queue<Bottle> que = new LinkedList<>();
        boolean[][][] visited = new boolean[A+1][B+1][C+1];

        que.add(new Bottle(0, 0, C));

        while (!que.isEmpty()) {
            Bottle cur = que.poll();
            if (visited[cur.a][cur.b][cur.c]) {
                continue;
            }
            if (cur.a == 0) {
                ans.add(cur.c);
            }

            visited[cur.a][cur.b][cur.c] = true;

            // A->B
            if (cur.a + cur.b <= B) {
                que.add(new Bottle(0, cur.a + cur.b, cur.c));
            }else{
                que.add(new Bottle(cur.a + cur.b - B, B, cur.c));
            }

            // A->C
            if (cur.a + cur.c <= C) {
                que.add(new Bottle(0, cur.b, cur.a + cur.c));
            } else {
                que.add(new Bottle(cur.a + cur.c - C, cur.b, C));
            }

            // B->A
            if (cur.a + cur.b <= A) {
                que.add(new Bottle(cur.a + cur.b, 0, cur.c));
            } else {
                que.add(new Bottle(A, cur.a + cur.b - A, cur.c));
            }

            // B->C
            if (cur.b + cur.c <= C) {
                que.add(new Bottle(cur.a,0, cur.b+cur.c));
            } else {
                que.add(new Bottle(cur.a, cur.b+ cur.c- C, C));
            }

            // C->A
            if (cur.a + cur.c <= A) {
                que.add(new Bottle(cur.a + cur.c, cur.b, 0));
            } else {
                que.add(new Bottle(A, cur.b, cur.a + cur.c - A));
            }

            // C->B
            if (cur.b + cur.c <= B) {
                que.add(new Bottle(cur.a, cur.b + cur.c, 0));
            } else {
                que.add(new Bottle(cur.a, B, cur.b + cur.c - B));
            }
        }
    }
}

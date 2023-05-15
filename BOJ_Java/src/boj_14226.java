import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class boj_14226 {
    static int S;
    static boolean[][] visited = new boolean[10001][10001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());

        bfs();

    }

    public static void bfs() {
        Queue<Emoticon> que = new LinkedList<>();
        que.offer(new Emoticon(1, 0, 0));
        visited[1][0] = true;

        int ans = 0;
        while (!que.isEmpty()) {
            Emoticon cur = que.poll();
            if (cur.screen == S) {
                ans = cur.time;
                break;
            }
            if (cur.clip != 0 && !visited[cur.screen+ cur.clip][cur.clip] && cur.screen + cur.clip <= 1000) {
                que.offer(new Emoticon(cur.screen + cur.clip, cur.clip, cur.time + 1));
                visited[cur.screen+ cur.clip][cur.clip] = true;
            }

            if (cur.screen != cur.clip && !visited[cur.screen][cur.screen]) {
                que.offer(new Emoticon(cur.screen, cur.screen, cur.time + 1));
                visited[cur.screen][cur.screen] = true;
            }

            if (cur.screen > 0 && !visited[cur.screen - 1][cur.clip]) {
                que.offer(new Emoticon(cur.screen - 1, cur.clip, cur.time + 1));
                visited[cur.screen-1][cur.clip] = true;
            }
        }
        System.out.println(ans);
    }
}
class Emoticon{
    int screen;
    int clip;
    int time;

    Emoticon(int screen, int clip, int time) {
        this.screen = screen;
        this.clip = clip;
        this.time = time;
    }
}

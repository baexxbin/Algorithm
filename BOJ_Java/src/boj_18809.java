import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_18809 {
    static int N, M, G, R;
    static int[][] board;
    static ArrayList<Land> candidates = new ArrayList<Land>();
    static boolean[] availableLands;
    static Land[] selectedLands;
    static boolean[] selectedGreen;
    static int ans = 0;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static void selectLand(int depth, int idx) {
        if (depth == G + R) {
            selectGreen(0,0);
            return;
        }

        for (int i = idx; i < candidates.size(); i++) {
            availableLands[i] = true;
            selectedLands[depth] = candidates.get(i);
            selectLand(depth+1, i+1);
            availableLands[i] = false;
        }
    }

    private static void selectGreen(int depth, int idx) {
        if (depth==G){
            ans = Math.max(ans, bfs());
            return;
        }

        for (int i = idx; i < selectedLands.length; i++) {
            selectedGreen[i] = true;
            selectGreen(depth + 1, i + 1);
            selectedGreen[i] = false;
        }
    }

    private static int bfs() {
        int[][] tmp = copyBoard();
        int[][] time = new int[N][M];
        Queue<Land> que = new ArrayDeque<>();

        for (int i = 0; i < selectedLands.length; i++) {
            Land l = selectedLands[i];
            if (selectedGreen[i]) {
                que.offer(new Land(l.x, l.y, 'G'));
                tmp[l.x][l.y] = 3;
            }
        }

        for (int i = 0; i < selectedLands.length; i++) {
            Land l = selectedLands[i];
            if (!selectedGreen[i]) {
                que.offer(new Land(l.x, l.y, 'R'));
                tmp[l.x][l.y] = 4;
            }
        }

        int cnt = 0;
        while (!que.isEmpty()) {
            Land cur = que.poll();

            if (tmp[cur.x][cur.y] == 5) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isOutRange(nx, ny)){
                    continue;
                }
                if (tmp[nx][ny]==1 || tmp[nx][ny]==2) {
                    if (cur.color=='G'){
                        tmp[nx][ny] = 3;
                    }else {
                        tmp[nx][ny] = 4;
                    }
                    que.offer(new Land(nx, ny, cur.color));
                    time[nx][ny] = time[cur.x][cur.y]+1;
                } else if (cur.color=='R' && tmp[nx][ny]==3 && time[nx][ny]==time[cur.x][cur.y]+1) {
                    tmp[nx][ny] = 5;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static int[][] copyBoard() {
        int[][] tmp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = board[i][j];
            }
        }
        return tmp;
    }

    private static boolean isOutRange(int x, int y) {
        if (0 <= x && x < N && 0 <= y && y < M) {
            return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) {
                    candidates.add(new Land(i, j));
                }
            }
        }

        availableLands = new boolean[candidates.size()];
        selectedLands = new Land[G + R];
        selectedGreen = new boolean[G + R];

        selectLand(0,0);
        System.out.println(ans);
    }

    static class Land {
        int x, y;
        char color;

        Land(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Land(int x, int y, char color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

}

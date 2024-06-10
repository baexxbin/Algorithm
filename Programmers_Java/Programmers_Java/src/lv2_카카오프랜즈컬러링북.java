import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class lv2_카카오프랜즈컬러링북 {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean[][] visited;
    static int m = 6;
    static int n = 4;
    static int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(m, n, picture)));
    }

    public static int bfs(int y, int x){
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{y, x});
        visited[y][x] = true;

        int color = picture[y][x];
        int cnt = 1;

        while (!que.isEmpty()){
            int[] cur = que.poll();
            int cy = cur[0];
            int cx = cur[1];

            for (int i = 0; i < 4; i++) {
                int ny = cy+dy[i];
                int nx = cx+dx[i];
                if (0<=ny && ny<m && 0<=nx && nx<n){
                    if (!visited[ny][nx] && picture[ny][nx] == color) {
                        que.add(new int[] {ny, nx});
                        visited[ny][nx] = true;
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
    public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j]!=0 && !visited[i][j]){
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(i, j));
                    numberOfArea++;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}

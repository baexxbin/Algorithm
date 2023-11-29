import java.util.ArrayDeque;
import java.util.Queue;

public class lv2_리코쳇로봇 {
    static int N,M, sx,sy, ex,ey;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0 , 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) {
        String[] board = {".D.R", "....", ".G..", "...D"};
        System.out.println(solution(board));
    }

    public static int bfs() {
        Queue<int[]> que = new ArrayDeque<>();
        visited[sx][sy] = true;
        que.add(new int[]{sx, sy, 0});

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];

            if (x==ex && y==ey) return dist;

            for (int i = 0; i < 4; i++) {
                int nx = x;
                int ny = y;

                while (nx + dx[i] >= 0 && nx + dx[i] < N && ny + dy[i] >= 0 && ny + dy[i] < M && map[nx + dx[i]][ny + dy[i]] != 'D') {
                    nx+=dx[i];
                    ny+=dy[i];
                }
                if (visited[nx][ny]) continue;
                visited[nx][ny] = true;
                que.add(new int[]{nx, ny, dist + 1});
            }
        }
        return -1;
    }

    public static int solution(String[] board) {
        N = board.length;
        M = board[0].length();

        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = board[i].toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'R') {
                    sx = i;
                    sy = j;
                } else if (map[i][j] == 'G') {
                    ex = i;
                    ey = j;
                }
            }
        }

        return bfs();
    }
}

// length : 배열의 길이, 필드를 가져옴
// lenght() : 문자열의 길이, 메소드를 호출
// size() : 컬렉션 프레워크 타입의 길이, 메소드를 호출
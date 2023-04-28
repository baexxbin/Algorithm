import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_3190 {
    static int N, K, L;
    static int[][] board;
    static int[] dx = {-1, 0, 1, 0};    // 북, 동, 남, 서
    static int[] dy = {0, 1, 0 , -1};
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        // board 리스트 초기화
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(board[i], 0);
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[r-1][c-1] = 1;
        }

        // info 큐 초기화
        Queue<Conversion> info = new LinkedList<>();
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);
            info.add(new Conversion(t, d));
        }

        Deque<Point> snake = new LinkedList<>();
        snake.add(new Point(0, 0));


        int d = 1; // 동(오른쪽)
        Conversion conversion = info.poll();
        count = 0;
        while (true) {
            if (conversion.time == count) {  //  방향바뀔 시간이고 큐가 비어있지 않다면 방향정보 업데이트
                d = conversion.direction == 'D' ? (d+1)%4 : (d+3)%4;    // 방향d는 현재 conversion의 direction으로 90도 회전
                if (!info.isEmpty()){
                    conversion = info.poll();                            // 새로운 conversion으로 업데이트
                }
            }

            Point cur = snake.peekFirst();   // 머리 앞으로 이동 (큐의 맨 앞의 요소 '가져옴')
            int nr = cur.r + dx[d];
            int nc = cur.c + dy[d];

            Point next = new Point(nr, nc);
            if (nr < 0 || nr >= N || nc < 0 || nc >= N || snake.contains(next)) {     // 머리가 벽이나 몸에 부딪힘
                System.out.println(count+1);
                break;
            }

            snake.addFirst(next);       // (새로운 머리위치)머리는 앞에서 넣음
            count++;
            if (board[next.r][next.c] == 1) {   // 앞으로 이동한 곳에 사과가 있음
                board[next.r][next.c] = 0;      // 사과 먹음
                continue;                       // 꼬리는 그대로
            }
            snake.removeLast();     // 사과 못먹으면 꼬리 위치 옮기기
        }
    }

}

class Conversion {
    int time;
    char direction;

    Conversion(int time, char direction) {
        this.time = time;
        this.direction = direction;
    }
}

class Point {
    int r;
    int c;

    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }

    // 덱에서 contains 이용 시 좌표가 같으면 같은 객체라고 인식하도록 오버라이딩
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Point)) {
            return false;
        }

        Point other = (Point) obj;
        return this.r == other.r && this.c == other.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }
}

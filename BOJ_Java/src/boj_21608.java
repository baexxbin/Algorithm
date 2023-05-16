import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_21608 {
    static int N;
    static int[][] board;
    static int[] students;
    static HashSet[] likes;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        students = new int[N*N+1];
        likes = new HashSet[N*N+1];

        for (int i = 1; i <= N*N; i++) {
            likes[i] = new HashSet<>();
        }

        for (int i = 1; i <= N*N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            students[i] = idx;
            for (int j = 0; j < 4; j++) {
                likes[idx].add(Integer.parseInt(st.nextToken()));
            }
        }

        // 첫번째 학생 자리 고정
        board[1][1] = students[1];

        // 차례대로 학생들 자리 배정
        for (int i = 2; i <= N * N; i++) {
            seatAssignment(i);
        }

        // 만족도 조사
        int ans = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {       // 학생이 앉은 자리
                int cnt = 0;
                for (int k = 0; k < 4; k++) {   // 학생의 주변 자리 탐색
                    int nr = r+dx[k];
                    int nc = c+dy[k];
                    if (0 <= nr && nr < N && 0 <= nc && nc < N) {
                        if (likes[board[r][c]].contains(board[nr][nc])) cnt++;
                    }
                }
                if (cnt == 0) continue;
                ans += Math.pow(10, cnt - 1);
            }
        }
        System.out.println(ans);
    }

    // 한 학생이 앉을 수 있는 최적의 자리 찾기
    public static void seatAssignment(int idx) {
        PriorityQueue<Student> que = new PriorityQueue<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (board[r][c] == 0) {
                    que.add(sitStudent(idx, r, c));   // 우선순위 큐에 idx학생이 앉을 수 있는 경우 모두 넣기
                }
            }
        }

        // 우선순위 제일 놓은 자리 뽑기
        Student best = que.poll();
        board[best.x][best.y] = students[idx];  // 자리 지정 완료
    }

    // 해당 자리에 앉았을 때 학생 정보
    public static Student sitStudent(int idx, int x, int y) {
        int like = 0;
        int empty = 0;

        // 현재 학생이 앉으려고 하는 자리 조사 (좋아하는 친구 수, 빈 칸 수)
        for (int i = 0; i < 4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];

            if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                if (board[nx][ny]==0){
                    empty++;
                    continue;
                }
                if (likes[students[idx]].contains(board[nx][ny])) like++;
            }
        }
        return new Student(x, y, like, empty);
    }
    static class Student implements Comparable<Student> {
        int x;
        int y;
        int like_cnt;
        int empty_cnt;

        Student(int x, int y, int like_cnt, int empty_cnt) {
            this.x = x;
            this.y = y;
            this.like_cnt = like_cnt;
            this.empty_cnt = empty_cnt;
        }

        @Override
        public int compareTo(Student o) {
            if (o.like_cnt == this.like_cnt) {
                if (o.empty_cnt == this.empty_cnt) {
                    if (o.x == this.x) {
                        return this.y-o.y;
                    }
                    return this.x-o.x;
                }
                return o.empty_cnt - this.empty_cnt;
            }
            return o.like_cnt - this.like_cnt;
        }
        // 반환값이 음수일 경우 오름차순 정렬, 양수일 경우 내림차순 정렬
    }
}

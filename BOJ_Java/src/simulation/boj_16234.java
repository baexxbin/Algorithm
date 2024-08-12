package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 인구 이동
* 09:20 - 10:40
* 1. 조건에 맞는 국경선 열기
* 2. 국경선을 모두 연 후 인구 이동
*   - 연합인 나라 찾기 (bfs) or 체크
* 인구 이동 며칠동안 발생하는지 구하기 >> 1번 조건 해당 안될때까지
* */
public class boj_16234 {
    static int N, L, R;
    static int[][] country;
    static int[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static boolean canOpen(int x, int y, int nx, int ny) {              // 인접한 국경을 열 수 있는지 체크
        int dif = Math.abs(country[x][y] - country[nx][ny]);
        return dif >= L && dif <= R;
    }
    private static int isOpen(int x, int y, int check) {      // 연합여부
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{x, y});
        visited[x][y] = check;
        int cntC = 1;   // 총 연합 나라 수
        int cntP = country[x][y];   // 총 연합 인구 수

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (!(0<=nx && nx<N && 0<=ny && ny<N)) continue;
                if (visited[nx][ny]==0 && canOpen(cur[0], cur[1], nx, ny)) {
                    que.add(new int[] {nx, ny});
                    visited[nx][ny] = check;
                    cntC++;
                    cntP += country[nx][ny];
                }
            }
        }

        if (cntC == 1) {                // 연합없을 경우
            visited[x][y] = -1;         // 처음 check로 업데이트한 visited 취소
            return -1;
        }
        return cntP / cntC;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        country = new int[N][N];
        visited = new int[N][N];        // 방문 여부 체크 + 자신의 연합 팀
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                country[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        while (true) {
            int[] checkLst = new int[N * N + 1];    // 연합 팀별 인구수 배열 초기화
            int team = 1;
            boolean isMoved = false;                // 인구 이동 여부 확인

            // 국경선 열면서 연합 만들기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] == 0) {
                        int tmp = isOpen(i, j, team);
                        if (tmp!=-1) {                      // 연합 이룰 경우
                            checkLst[team] = tmp;           // 현재 연합팀에 연합 인구수 적어놓기
                            team++;
                            isMoved = true;
                        }
                    }
                }
            }

            // 인구 이동이 없었으면 종료
            if (!isMoved) break;

            // 국경선을 모두 연 후, 인구 수 업데이트
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]<=0) continue;               // 연합 못이룬 경우 패쓰
                    country[i][j] = checkLst[visited[i][j]];      // 자기가 속한 연합팀의 인구 수로 업데이트
                }
            }

            // 방문 체크 배열 초기화
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], 0);
            }
            ans++;
        }
        System.out.println(ans);
    }
}

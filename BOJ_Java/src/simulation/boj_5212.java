package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
* 지구 온난화
* 09:00 - 09:37
* 문제 설명: 50년 후 지도(가장 작은 직사각형)찾기, 인접한 세칸, 네칸에 바다가 있으면 잠김
* 설계
*   - bfs로 바다체크(.,범위 벗어남), 잠길 땅 체크, 땅 좌표 저장
*   - 지도 그리기: 최대최소 꼭짓점 좌표 이용해서 최소직사각형 구하기
* */
public class boj_5212 {
    static int R, C;
    static char[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static boolean isSink(int x, int y) {
        int sea = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!(0<=nx && nx<R && 0<=ny && ny<C)) {
                sea++;
                continue;
            }
            if (board[nx][ny]=='.') sea++;
        }
        return sea > 2;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        boolean[][] check = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        // 잠김여부 체크
        ArrayList<int[]> coordi = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'X') {                       // 땅일 경우 잠기는지 체크
                    if(isSink(i, j)) {
                        check[i][j] = true;                     // 잠길 경우 check에 체크
                    } else coordi.add(new int[]{i, j});         // 안잠길 경우 좌표값 저장
                }
            }
        }

        // 땅 잠기기
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (check[i][j]) board[i][j] = '.';
            }
        }

        // 50년 후 바다 구하기
        int Xmax = Integer.MIN_VALUE;
        int Ymax = Integer.MIN_VALUE;
        int Xmin = Integer.MAX_VALUE;
        int Ymin = Integer.MAX_VALUE;
        for (int[] c : coordi) {
            if (c[0] < Xmin) Xmin = c[0];
            if (c[0] > Xmax) Xmax = c[0];
            if (c[1] < Ymin) Ymin = c[1];
            if (c[1] > Ymax) Ymax = c[1];
        }

        for (int i = Xmin; i < Xmax+1; i++) {
            for (int j = Ymin; j < Ymax+1; j++) {
                sb.append(board[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}

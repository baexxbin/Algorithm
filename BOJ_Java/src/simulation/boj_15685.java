package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
* 드래곤 커브
* 문제 설명: 드래곤 커브는 k-1세대 끝점을 기준으로 시계방향 90 회전 후 끝점에 붙인 모양으로 이뤄짐
*          드래곤 커브가 주어질때, 1*1정사각형 네꼭짓점이 모두 드래곤 커브의 일부인 갯수 구하기
* 설계: 1) 드래곤 커브 그리기
*       - 마지막 꼭짓점 ~ 시작 꼭짓점으로 가면서 방향 구하기 (반시계90)
*       - 구한 방향을 바탕으로 꼭짓점 구하기
*      2) 모든 꼭짓점이 커브에 속하는 수 구하기
* 주의: x,y범위 0~100
* */
public class boj_15685 {
    static int N;
    static boolean[][] board = new boolean[101][101];

    /*
    * 드래곤 커브 꼭짓점을 그리기 위한 방향을 얻은 함수
    * g세대까지, 0~g까지의 방향을 얻음
    * 방향은 [시작점 ~ 끝점]배열의 역순(끝-시작)으로 반시계90한 값 >> g세대까지 반복
    * */
    private static List<Integer> getDir(int d, int g) {
        List<Integer> dirs = new ArrayList<>();
        dirs.add(d);
        while (g-- > 0) {
            for (int i = dirs.size() - 1; i >= 0; i--) {
                int nd = (dirs.get(i) + 1) % 4;
                dirs.add(nd);
            }
        }
        return dirs;
    }

    // getDir에서 얻은 방향 정보를 바탕으로 꼭짓점 그리기
    private static void draw(int x, int y, List<Integer> dirs) {
        board[x][y] = true;
        for (int d : dirs) {
            switch (d) {
                case 0:
                    board[++x][y] = true;
                    break;
                case 1:
                    board[x][--y] = true;
                    break;
                case 2:
                    board[--x][y] = true;
                    break;
                case 3:
                    board[x][++y] = true;
                    break;
            }
        }
    }

    // 모든 꼭짓점이 커브에 속하는 사각형인지 확인
    private static int cntSquares() {
        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (board[i][j] && board[i+1][j] && board[i][j+1] && board[i+1][j+1]) cnt++;
            }
        }
        return cnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());   // 시작 점
            int d = Integer.parseInt(st.nextToken());   // 방향
            int g = Integer.parseInt(st.nextToken());   // 세대

            draw(x, y, getDir(d, g));
        }

        System.out.println(cntSquares());
    }
}

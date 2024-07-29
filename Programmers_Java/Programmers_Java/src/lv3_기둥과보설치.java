import java.util.ArrayList;
import java.util.Arrays;

/*
* 9:01 -
* 조건에 맞게 기둥과 보 설치하고 삭제하기
* - 조건 체크하기
*   - 기둥 끝 점: y+1, 보 끝 점: x+1
*   - 기둥 >> 1. 시작점 바닥(x=0)인지 체크 2. 시작점 보 혹은 기둥인지 체크
*   - 보 >> 1. 시작점 혹은 끝점 기둥인지 체크 2. 양쪽 끝 보인지 체크
* - 작업 수행하기
*   - 기둥, 보 양쪽 끝 기둥, 보 표시
*       - 좌표에 해당하는 n*n배열에 [true, false]로 표시하기 (3차원 배열)
*   - 세우기 : 조건에 맞으면 세우기
*   - 삭제
*       - 기둥 >> 양끝점에 대해 자기자신 뺏을때 board[0][x][y]가 0보다 작으면 삭제 못함
*       - 보 >> 양 끝점 중 하나라도 z좌표 합이 1이하면 삭제 못함
* */

public class lv3_기둥과보설치 {
    static int N;
    static boolean[][] columns;
    static boolean[][] beams;

    private static boolean canColum(int x, int y) {
        if (y==1) return true;
        else if (beams[x][y] || beams[x-1][y]) return true;
        else return columns[x][y - 1];
    }

    private static boolean canBeam(int x, int y) {
        if (columns[x][y-1] || columns[x+1][y-1]) return true;
        else return beams[x + 1][y] && beams[x - 1][y];
    }

    private static void create(int x, int y, int a) {
        if (a == 0) {
            if (canColum(x, y)) columns[x][y] = true;
        }else {
            if (canBeam(x, y)) beams[x][y] = true;
        }
    }
    private static void delete(int x, int y, int a) {
        if (a == 0) {
            if (canColum(x, y)) columns[x][y] = false;
        }else {
            if (canBeam(x, y)) beams[x][y] = false;
        }

        if (!canDelete(x,y)){
            if (a==0) columns[x][y] = true;
            else beams[x][y] = true;
        }
    }

    private static boolean canDelete(int x, int y) {
        for (int i = 1; i <= N + 1; i++) {
            for (int j = 1; j <= N + 1; j++) {
                if (columns[i][j] && !canColum(i, j)) return false;
                if (beams[i][j] && !canBeam(i, j)) return false;
            }
        }
        return true;
    }
    public static int[][] solution(int n, int[][] build_frame) {
        N = n;
        columns = new boolean[N+3][N+3];
        beams = new boolean[N+3][N+3];

        for (int[] bf : build_frame) {
            int x = bf[0]+1;
            int y = bf[1]+1;
            int a = bf[2];
            int b = bf[3];

            if (b==0) delete(x, y, a);
            else create(x,y,a);
        }

        ArrayList<int[]> lst = new ArrayList<>();
        for(int i = 1; i <= N+1; ++i) {
            for(int j = 1; j <= N+1; ++j) {
                if(columns[i][j]) {
                    lst.add(new int[] {i-1, j-1, 0}); // 1 높인 인덱스 다시 줄이기
                }

                if(beams[i][j]) {
                    lst.add(new int[] {i-1, j-1, 1});
                }
            }
        }

        int[][] ans = new int[lst.size()][3];
        for(int i = 0; i < ans.length; i++) {
            ans[i][0] = lst.get(i)[0];
            ans[i][1] = lst.get(i)[1];
            ans[i][2] = lst.get(i)[2];
        }

        return ans;
    }
    public static void main(String[] args) {
        int n = 5;
        int[][] build_frame = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
        System.out.println(Arrays.deepToString(solution(n, build_frame)));
    }
}

//    static int[][][] board;
//    private static void make(int x, int y, int kind) {
//        switch (kind) {
//            case 0:     // 기둥: 시작점(x, y) - 끝점(x, y+1)
//                if(x==0 || (board[0][x][y] | board[1][x][y]) == 1) {
//                    board[0][x][y] += 1;
//                    board[0][x][y+1] += 1;
//                }
//                break;
//            case 1:     // 보: 시작점(x, y) - 끝점(x+1, y)
//                if ((board[0][x][y]==1 || board[0][x+1][y]==1) || (board[1][x][y]==1 && board[1][x+1][y]==1)){
//                    board[1][x][y] += 1;
//                    board[1][x+1][y] += 1;
//                }
//        }
//    }
//
//    private static void delete(int x, int y, int kind) {
//        switch (kind) {
//            case 0:     // 기둥: 시작점(x, y) - 끝점(x, y+1)
//                if ((board[0][x][y] + board[1][x][y] <=1) || (board[0][x][y+1] + board[1][x][y+1] <=1)) break;
//                board[0][x][y] -=1;
//                board[0][x][y+1] -=1;
//                break;
//            case 1:     // 보: 시작점(x, y) - 끝점(x+1, y)
//                if ((board[0][x][y] + board[1][x][y] <=1) || (board[0][x+1][y] + board[1][x+1][y] <=1)) break;
//                board[0][x][y] -=1;
//                board[0][x+1][y] -=1;
//                break;
//        }
//    }
//    public static int[][] solution(int n, int[][] build_frame) {
//        board = new int[2][n+1][n+1];
//
//        for (int[] bf : build_frame) {
//            int x = bf[0];
//            int y = n-1-bf[1];  // y좌표 반전
//            int kind = bf[2];
//            int cmd = bf[3];
//
//            if (cmd==1) make(x, y, kind);
//            else delete(x, y, kind);
//        }
//
//        // 구조물 표시
//        ArrayList<int[]> ans = new ArrayList<>();
//        for (int i = 0; i <= n; i++) {
//            for (int j = 0; j <= n; j++) {
//                if (board[0][i][j] > 0){    // 기둥이 있을때
//                    if(j+1<n && board[0][i][j+1]>0) ans.add(new int[]{i, n-1-j, 0});
//                }
//                if (board[1][i][j] > 0) {
//                    if (i+1<n && board[1][i+1][j]>0) ans.add(new int[]{i, n-1-j, 1});
//                }
//            }
//        }
//
//        ans.sort((o1, o2) -> {
//            if (o1[0] == o2[0]) return Integer.compare(o1[2], o2[2]);
//            return Integer.compare(o1[0], o2[0]);
//        });
//
//        int[][] res = new int[ans.size()][3];
//        for (int i = 0; i < ans.size(); i++) {
//            res[i] = ans.get(i);
//        }
//
//        return res;
//    }
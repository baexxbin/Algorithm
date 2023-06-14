import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2580 {
    static int[][] matrix = new int[9][9];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0);
    }

    public static void dfs(int row, int col) {

        // 한 열(가로)을 다 채우면 다음 행으로 이동
        if (col == 9) {
            dfs(row+1, 0);
            return;
        }

        // 열도 다채우고 행도 다채우면 끝
        if (row == 9) {
            // 결과 출력
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    System.out.print(matrix[x][y] + " ");
                }
                System.out.println();
            }
            System.exit(0);     // 결과 하나 나오면 종료
        }

        // 1~9까지 넣으면서 해당되는 값인지 확인
        if (matrix[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (check(row, col, i)){
                    matrix[row][col] = i;
                    dfs(row, col+1);
                }
            }
            matrix[row][col] = 0;
            return;
        }

        // matrix[row][col]이 채워져있을 경우
        dfs(row, col+1);
    }

    public static boolean check(int row, int col, int num) {

        // 열(가로)확인
        for (int i = 0; i < 9; i++) {
            if (matrix[row][i] == num) {
                return false;
            }
        }

        // 행(세로)확인
        for (int i = 0; i < 9; i++) {
            if (matrix[i][col] == num) {
                return false;
            }
        }

        int tmpRow = (row/3)*3;
        int tmpCol = (col/3)*3;

        // 3x3 사각형 확인
        for (int i = tmpRow; i < tmpRow + 3; i++) {
            for (int j = tmpCol; j < tmpCol + 3; j++) {
                if (matrix[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}

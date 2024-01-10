import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_16935 {
    static int N,M,R;
    static int[][] matrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] options = new int[R];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            options[i] = Integer.parseInt(st.nextToken());
        }

        for (int idx : options) {
            switch (idx) {
                case 1: calOne(); break;
                case 2: calTwo(); break;
                case 3: calThree(); break;
                case 4: calFour(); break;
                case 5: calFive(); break;
                case 6: calSix(); break;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void calOne() {
        int[][] tmp = new int[N][M];
        for (int i = 0; i < N/2; i++) {
            tmp[i] = matrix[N-i-1];
            tmp[N-i-1] = matrix[i];
        }
        matrix = tmp;
    }

    public static void calTwo() {
        int[][] tmp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M / 2; j++) {
                tmp[i][j] = matrix[i][M-j-1];
                tmp[i][M-j-1] = matrix[i][j];
            }
        }
        matrix = tmp;
    }

    public static void calThree() {
        int[][] tmp = new int[M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[j][N-i-1] = matrix[i][j];
            }
        }
        matrix = new int[M][N];
        int m = M;
        M = N;
        N = m;
        matrix = tmp;
    }

    public static void calFour() {
        int[][] tmp = new int[M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[M-j-1][i] = matrix[i][j];
            }
        }
        matrix = new int[M][N];
        int m = M;
        M = N;
        N = m;
        matrix = tmp;
    }

    public static void calFive() {
        int[][] tmp = new int[N][M];

        for (int i = 0, r = N/2; i < r; i++) {
            for (int j = 0, c = M/2; j < c; j++) {
                tmp[i][j+c] = matrix[i][j];
                tmp[i + r][j + c] = matrix[i][j + c];
                tmp[i + r][j] = matrix[i + r][j + c];
                tmp[i][j] = matrix[i+r][j];
            }
        }
        matrix = tmp;
    }

    public static void calSix() {
        int[][] tmp = new int[N][M];

        for (int i = 0, r = N/2; i < r; i++) {
            for (int j = 0, c = M/2; j < c; j++) {
                tmp[i+r][j] = matrix[i][j];
                tmp[i][j] = matrix[i][j + c];
                tmp[i + r][j + c] = matrix[i + r][j];
                tmp[i][j+c] = matrix[i+r][j+c];
            }
        }
        matrix = tmp;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11054 {
    static int N;
    static int[] arr;
    static int[] dpUp;
    static int[] dpDown;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dpUp = new int[N];
        dpDown = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        LIS();
        LDS();

        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (dpUp[i] + dpDown[i] > ans) {
                ans = dpUp[i] + dpDown[i];
            }
        }
        System.out.println(ans-1);
    }

    public static void LIS() {

        for (int i = 0; i < N; i++) {
            dpUp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dpUp[i] < dpUp[j] + 1) {
                    dpUp[i] = dpUp[j]+1;
                }
            }
        }
    }

    public static void LDS() {

        for (int i = N - 1; i >= 0; i--) {
            dpDown[i] = 1;
            for (int j = N - 1; j > i; j--) {
                if (arr[j] < arr[i] && dpDown[i] < dpDown[j] + 1) {
                    dpDown[i] = dpDown[j]+1;
                }
            }
        }
    }
}

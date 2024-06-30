import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1806 {
    static int N, S;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 슬라이딩 윈도우 부분합
        int mn = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0;
        for (int right = 0; right < N; right++) {
            sum += arr[right];

            while (sum >= S) {
                mn = Math.min(mn, right - left + 1);    // mn, 현재길이
                sum-=arr[left];
                left++;
            }
        }
        if (mn == Integer.MAX_VALUE) {
            System.out.println(0);
        }else System.out.println(mn);

    }
}

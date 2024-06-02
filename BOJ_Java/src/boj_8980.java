import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_8980 {
    static int N, C, M, ans;
    static int[] truck;
    static ArrayList<Box> boxes = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        for (int m=0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            boxes.add(new Box(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        boxes.sort((b1, b2) -> b1.end - b2.end);
        truck = new int[N];
        Arrays.fill(truck, C);

        for (Box box : boxes) {
            int mn = C;
            for (int i = box.start; i < box.end; i++) {
                if(mn > Math.min(truck[i], box.weight)){
                    mn = Math.min(truck[i], box.weight);
                }
            }

            for (int j = box.start; j < box.end; j++) {
                truck[j] -= mn;
            }
            ans += mn;
        }
        System.out.println(ans);

    }
    static class Box{
        int start;
        int end;
        int weight;

        Box(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}

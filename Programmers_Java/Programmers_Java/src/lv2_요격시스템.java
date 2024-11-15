import java.util.Arrays;

public class lv2_요격시스템 {
    public static int solution(int[][] targets) {
        Arrays.sort(targets, ((o1, o2) -> o1[1] - o2[1]));

        int cnt = 1;
        int pos = targets[0][1];
        for (int[] t : targets) {
            if (t[0] < pos) continue;
            pos = t[1];
            cnt++;
        }
        return cnt;
    }
    public static void main(String[] args) {
        int[][] targets = {{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}};
        System.out.println(solution(targets));
    }
}

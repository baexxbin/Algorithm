import java.util.Arrays;
import java.util.Comparator;

public class lv3_단속카메라 {
    public static void main(String[] args) {
        int [][] routes = {{-20, -15}, {-14,-5}, {-18,-13}, {-5,-3}};
        System.out.println(solution(routes));
    }
    public static int solution(int[][] routes) {
        int cnt = 0;

        Arrays.sort(routes, Comparator.comparingInt((int[] o) -> o[1]));

        int camera = -30001;
        for (int[] car : routes) {
            if (camera < car[0]) {
                camera = car[1];
                cnt++;
            }
        }
        return cnt;
    }
}

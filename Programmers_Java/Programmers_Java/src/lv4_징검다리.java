import java.util.*;

public class lv4_징검다리 {

    private static int removeCnt(int x, int[] rocks, int arrive) {      // x가 돌 사이간 최소거리가되려면 몇개의 돌을 지워야하는지
        int pre = 0;
        int remove = 0;
        for (int i = 0; i < rocks.length; i++) {
            if (rocks[i]-pre < x){
                remove++;
                continue;
            }
            pre = rocks[i];
        }
        if (arrive-pre < x) remove++;

        return remove;
    }
    public static int solution(int distance, int[] rocks, int n) {
        int left = 1;
        int right = distance;
        int ans = 0;

        Arrays.sort(rocks);

        while (left <= right) {
            int mid = (left + right) / 2;
            if (removeCnt(mid, rocks, distance) <=n) {       // mid가 가능하면 길이 키워보기
                ans = Math.max(ans, mid);
                left = mid+1;
            }else right = mid-1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int distance = 25;
        int[] rocks = {2, 14, 11, 21, 17};
        int n = 2;
        System.out.println(solution(distance, rocks, n));
    }
}

import java.util.Arrays;

/*
 * 인센티브 못받는 사원 구하기
 * 받을 수 있는 사원 끼리 순위 매기기, 완호 순위 매기기
 *
 * 태도점수 내림차순, 동료평가 오름차순 정렬 >> 현재 동료평가가 낮으면 무조건 인센티브 못받음
 * */
public class lv3_인사고과 {
    public static void main(String[] args) {
        int[][] scores = {{2,2}, {1,4}, {3,2}, {3,2}, {2,1}};
        System.out.println(solution(scores));
    }

    public static int solution(int[][] scores) {
        int wan_w = scores[0][0];
        int wan_p = scores[0][1];
        int wan_sum = wan_w+wan_p;

        Arrays.sort(scores, ((o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o2[0] - o1[0];
        }));

        int rank = 1;
        int mx_p = 0;
        for (int[] s : scores) {
            if (wan_w < s[0] && wan_p < s[1]) return -1;    // 완호 인센티브 못받으면 -1
            if (mx_p <= s[1]) {                             // 태도점수 높은 이전 애들 중 동료평가 최고점인 애보다 동료평가 높거나 같을때 (인센티브 살아남음)
                if (wan_sum < s[0]+s[1]) rank++;            // 완호총점이 더 낮으면 순위++
                mx_p = s[1];                                // 이전 최고 태도점수 중 최고 동료평가 점수 업데이트
            }
        }
        return rank;
    }
}


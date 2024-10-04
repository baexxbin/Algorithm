import java.util.*;

public class lv2_구명보트 {
    public static int solution(int[] people, int limit) {
        Arrays.sort(people);

        int left = 0;
        int right = people.length-1;

        int cnt = 0;
        while (left <= right) {
            if (people[left]+people[right] <=limit){
                left++;
            }
            right--;
            cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] people = {70, 80, 50};
        int limit = 100;
        System.out.println(solution(people, limit));
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lv2_우박수열정적분 {
    public static void main(String[] args) {
        int k = 5;
        int[][] ranges = {{0,0}, {0,-1}, {2,-3}, {3,-3}};
        System.out.println(Arrays.toString(solution(k, ranges)));
    }

    public static List<Integer> makeSequence(int k) {
        List<Integer> seq = new ArrayList<>();
        while (k != 1) {
            seq.add(k);
            k = k%2==0 ? k/2 : k*3+1;
        }
        seq.add(k);

        return seq;
    }

    public static double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];

        List<Integer> seq = makeSequence(k);
        for (int i =0; i<ranges.length; i++) {
            if (ranges[i][0] > ranges[i][1] + seq.size() - 1) {
                answer[i] = -1;
                continue;
            } else if (ranges[i][0] == ranges[i][1] + seq.size() - 1) {
                answer[i] = 0;
                continue;
            }

            double total=0;
            for (int j = ranges[i][0]; j< ranges[i][1] + seq.size() - 1; j++) {
                total += (seq.get(j) + seq.get(j+1)) / 2.0;
            }
            answer[i] = total;
        }

        return answer;
    }
}

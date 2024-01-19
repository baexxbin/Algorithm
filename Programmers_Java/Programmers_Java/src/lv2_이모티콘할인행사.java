import java.util.ArrayList;
import java.util.Arrays;

public class lv2_이모티콘할인행사 {
    static ArrayList<int[]> discount = new ArrayList<>();
    static int[] data = {10, 20, 30, 40};
    static int[] tmp;
    static int[] ans = new int[2];
    public static int[] solution(int[][] users, int[] emoticons) {
        tmp = new int[emoticons.length];
        dfs(0);

        for (int i = 0; i < discount.size(); i++) {
            int join = 0;
            int[] price = new int[users.length];
            for (int j = 0; j < emoticons.length; j++) {
                for (int k = 0; k < users.length; k++) {
                    if (users[k][0] <= discount.get(i)[j]) {
                        price[k] += emoticons[j]*(100- discount.get(i)[j])/100;
                    }
                }
            }

            for (int k = 0; k < users.length; k++) {
                if (price[k] >= users[k][1]) {
                    join+=1;
                    price[k] = 0;
                }
            }
            if (join >= ans[0]) {
                if (join==ans[0]){
                    ans[1] = Math.max(ans[1], Arrays.stream(price).sum());
                }else {
                    ans[1] = Arrays.stream(price).sum();
                }
                ans[0] = join;
            }
        }

        return ans;
    }

    public static void dfs(int depth) {
        if (depth == tmp.length) {
            discount.add(Arrays.copyOf(tmp, tmp.length));
            return;
        }

        for (int d : data) {
            tmp[depth] += d;
            dfs(depth+1);
            tmp[depth] -= d;
        }
    }

    public static void main(String[] args) {
        int[][] users = {{40, 10000}, {25, 10000}};
        int[] emoticons = {7000, 9000};
        System.out.println(Arrays.toString(solution(users, emoticons)));
    }
}

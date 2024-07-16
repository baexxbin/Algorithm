import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

// 1. 트리 만들기, 부모 트리 정보 저장하기
// 2. 밑에서부터 자기 수익+부모한테 보낼 수익 계산
public class lv3_다단계칫솔판매 {
    static HashMap<String, Integer> dict = new HashMap<>();
    static int[] money;

    private static void dfs(int total, int idx, String[] referral) {
        if (total<=0) return;
        if (idx == 0) {
            money[idx] += total;
            return;
        }

        int tribute = (int) (total*0.1);
        money[idx] += total-tribute;
        int parent = dict.get(referral[idx-1]);
        dfs(tribute, parent, referral);

    }
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        money = new int[enroll.length+1];

        dict.put("-", 0);
        for (int i = 0; i < enroll.length; i++) {
            dict.put(enroll[i], i + 1);
        }

        for (int i = 0; i < seller.length; i++) {
            int idx = dict.get(seller[i]);
            int total = amount[i]*100;
            dfs(total, idx, referral);
        }

        return Arrays.copyOfRange(money, 1, money.length);
    }

    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};
        System.out.println(Arrays.toString(solution(enroll, referral, seller, amount)));
    }
}

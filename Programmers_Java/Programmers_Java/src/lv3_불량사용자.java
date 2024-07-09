import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class lv3_불량사용자 {
    // 1. ban id별로 가능한 아이디 목록 구하기
    // 2. 걍 완전 탐색
    private static HashSet<String> patterns;
    private static HashMap<String, ArrayList<String>> map = new HashMap<>(8);
    private static HashMap<String, Boolean> check = new HashMap<>(8);
    private static int L;
    private static HashSet<List<String>> ans = new HashSet<>();
    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"*rodo", "*rodo", "******"};
        System.out.println(solution(user_id, banned_id));
    }

    private static void findBanId(String[] banned_id, String[] user_id) {
        patterns = new HashSet<>(Arrays.asList(banned_id));

        for (String pattern : patterns) {
            ArrayList<String> tmp = new ArrayList<>();
            String regex = pattern.replace("*", ".");
            String exactLengthRegex = String.format("^%s$", regex);
            Pattern compiled = Pattern.compile(exactLengthRegex);
            for (String id : user_id) {
                Matcher matcher = compiled.matcher(id);
                if (matcher.matches()) {
                    tmp.add(id);
                }
            }
            map.put(pattern, tmp);
        }
    }

    private static void dfs(int depth, String[] banned_id, List<String> lst) {
        if (depth == L) {
            List<String> sortLst = new ArrayList<>(lst);
            Collections.sort(sortLst);
            ans.add(new ArrayList<>(sortLst));
            return;
        }

        String ban = banned_id[depth];
        for (String candi : map.get(ban)) {
            if (!check.get(candi)){
                check.put(candi, true);
                lst.add(candi);
                dfs(depth+1, banned_id, lst);
                lst.remove(lst.size()-1);
                check.put(candi, false);
            }
        }

    }
    public static int solution(String[] user_id, String[] banned_id) {

        findBanId(banned_id, user_id);
        L = banned_id.length;

        for (String id : user_id) {
            check.put(id, false);
        }
        List<String> lst = new ArrayList<>();
        dfs(0, banned_id, lst);
        System.out.println(ans);
        return ans.size();
    }
}

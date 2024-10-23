import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class lv2_스킬트리 {
    public static int solution(String skill, String[] skill_trees) {
        Set<Character> sk = new HashSet<>();
        for (char c : skill.toCharArray()) {
            sk.add(c);
        }

        int cnt = 0;
        for (String st : skill_trees) {
            StringBuilder sb = new StringBuilder();
            for (char c : st.toCharArray()) {
                if (sk.contains(c)) sb.append(c);
            }
            if (skill.startsWith(sb.toString())) cnt++;
        }

        return cnt;
    }
    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
        solution(skill, skill_trees);
    }
}

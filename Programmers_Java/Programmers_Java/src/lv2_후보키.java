import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class lv2_후보키 {
    static int answer;
    static int row, col;
    static List<HashSet<Integer>> candidateKey;
    static String[][] relationCopy;
    public static void main(String[] args) {
        String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
        System.out.println(solution(relation));
    }
    public static int solution(String[][] relation) {
        relationCopy = relation;
        answer = 0;

        candidateKey = new ArrayList<>();
        row = relationCopy.length;
        col = relationCopy[0].length;

        for (int i = 1; i < col + 1; i++) {
            combination(0, i, 0, new HashSet<>());
        }

        return answer;
    }

    static void combination(int idx, int size, int depth, HashSet<Integer> set) {
        if (depth == size) {
            // 유일성 검사
            if (!unique(set)) {
                return;
            }
            // 최소성 검사
            for (HashSet<Integer> key : candidateKey) {
                if (set.containsAll(key)) { // A ∩ B = B
                    return;
                }
            }
            candidateKey.add(set);
            answer++;
            return;
        }
        // 조합 만들기
        for (int i = idx; i < col; i++) {
            HashSet<Integer> newSet = new HashSet<>(set);
            newSet.add(i);
            idx++;
            combination(idx, size,depth+1, newSet);
        }

    }

    private static boolean unique(HashSet<Integer> set) {
        List<String> lst = new ArrayList<>();
        // 만들어진 조합 중복검사
        for (int i = 0; i < row; i++) {
            StringBuilder sb = new StringBuilder();
            for (int idx : set) {
                sb.append(relationCopy[i][idx]);
            }
            if (!lst.contains(sb.toString())) {
                lst.add(sb.toString());
            }else {
                return false;
            }
        }
        return true;
    }
}

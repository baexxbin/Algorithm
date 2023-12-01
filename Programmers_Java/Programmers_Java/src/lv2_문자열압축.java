public class lv2_문자열압축 {
    public static void main(String[] args) {
        String s = "ababcdcdababcdcd";
        System.out.println(solution(s));
    }
    public static int solution(String s) {
        int answer = 0;

        for (int i = 1; i <= (s.length() / 2) + 1; i++) {
            int res = getSplitLength(s, i, 1).length();
            answer = (i == 1) ? res : Math.min(answer, res);
        }
        return answer;
    }

    private static String getSplitLength(String s, int n, int repeat) {
        if (s.length() < n) return s;

        StringBuilder sb = new StringBuilder();
        String preString = s.substring(0, n);
        String postString = s.substring(n);


        if (!postString.startsWith(preString)){
            if (repeat == 1) {
                sb.append(preString).append(getSplitLength(postString, n, 1));
            } else {
                sb.append(repeat).append(preString).append(getSplitLength(postString, n, 1));
            }
        } else {
            sb.append(getSplitLength(postString, n, repeat + 1));
        }
        return sb.toString();
    }
}

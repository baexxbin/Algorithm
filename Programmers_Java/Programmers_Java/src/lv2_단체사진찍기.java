public class lv2_단체사진찍기 {
    private static final String[] friends = {"A", "C", "F", "J", "M", "N", "R", "T"};
    private static int ans = 0;
    private static void dfs(String lines, boolean[] visited, String[] data){
        if (lines.length() == 8) {
            if (isValid(lines, data)){
                ans++;
            }
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (!visited[i]) {
                visited[i]=true;
                String line = lines+friends[i];
                dfs(line, visited, data);
                visited[i] = false;
            }
        }
    }

    private static boolean isValid(String lines, String[] datas) {
        for (String data : datas) {
            int p1 = lines.indexOf(data.charAt(0));
            int p2 = lines.indexOf(data.charAt(2));
            char op = data.charAt(3);
            int idx = data.charAt(4)-'0';   // 아스키코드 이용 문자를 정수로 바꿈

            if (op == '=') {
                if (!(Math.abs(p1-p2)==idx+1)) return false;
            } else if (op == '>') {
                if (!(Math.abs(p1-p2)>idx+1)) return false;
            }else if (op == '<'){
                if (!(Math.abs(p1-p2)<idx+1)) return false;
            }
        }
        return true;
    }
    public static int solution(int n, String[] data) {
        boolean[] visited = new boolean[8];
        dfs("", visited, data);
        return ans;
    }
    public static void main(String[] args) {
        int n = 2;
        String[] data = {"N~F=0", "R~T>2"};

        System.out.println(solution(n, data));
    }
}

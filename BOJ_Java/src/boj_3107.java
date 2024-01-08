import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class boj_3107 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] ipv6 = br.readLine().split(":");

        StringBuilder sb = new StringBuilder();
        int unit=0;
        boolean flag = false;
        for (String cur : ipv6) {
            if (cur.isEmpty() && !flag) {
                for (int i = 0; i < 8 - ipv6.length + 1; i++) {
                    sb.append("0000").append(":");
                    unit++;
                }
                flag = true;
            }else {
                sb.append("0".repeat(Math.max(0, 4 - cur.length())));
                sb.append(cur).append(":");
                unit++;
            }
        }

        // ::가 맨 뒤에 붙었을때 처리 (빈문자열이 마지막에 붙어 ipv6배열에 포함되지 않음)
        while (unit<8){
            sb.append("0000").append(":");
            unit++;
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);  // System.out.println() 메서드는 내부적으로 toString() 메서드를 호출하여 객체를 문자열로 변환한 후 출력
    }
}

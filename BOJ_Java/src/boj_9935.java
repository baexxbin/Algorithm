import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String sentence = br.readLine();
        String explosion = br.readLine();

        int lenSentence = sentence.length();
        int lenExplosion = explosion.length();

        for (int i = 0; i < lenSentence; i++) {
            char cur = sentence.charAt(i);
            sb.append(cur);

            if (sb.length() >= lenExplosion) {
                boolean check = true;
                for (int j = 0; j < lenExplosion; j++) {
                    char c1 = sb.charAt(sb.length() - lenExplosion + j);
                    char c2 = explosion.charAt(j);
                    if (c1 != c2) {
                        check = false;
                        break;
                    }
                }
                if (check){
                    sb.delete(sb.length() - lenExplosion, sb.length());
                }
            }
        }
        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }
}

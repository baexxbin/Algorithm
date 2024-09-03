package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 여우는 어떻게 울지?
* 문제 설명: 여우 울음소리만 뽑아서 출력
* 구현
*   - 울음소리 단어 단위로 리스트로 받기
*   - 해시맵으로 동물, 울음소리 저장
*   - 두번째 st가 gose가 아니라 dose이면 해시맵 종료
*   - 울음소리 리스트를 돌면서 해시맵에 들어있으면 삭제
* */
public class boj_9536 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            List<String> sounds = new java.util.ArrayList<>(List.of(br.readLine().split(" ")));
            HashMap<String, String> map = new HashMap<>();
            while (true) {
                st = new StringTokenizer(br.readLine());
                String animal = st.nextToken();
                String mid = st.nextToken();
                String s = st.nextToken();
                if (Objects.equals(mid, "does")) break;
                map.put(animal, s);
            }
            for (Iterator<String> it = sounds.iterator(); it.hasNext(); ) {
                String sd = it.next();
                if (map.containsValue(sd)) {
                    it.remove();  // 안전하게 요소 제거
                }
            }
            sb.append(String.join(" ", sounds)).append('\n');
        }
        System.out.println(sb);
    }
}

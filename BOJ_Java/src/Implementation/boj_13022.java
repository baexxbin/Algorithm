package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
* 늑대와 올바른 단어
* 문제 설명: w,o,l,f 순으로 n번식 나오면 올바른 단어. 올바른 단어인지 아닌지 판별 (1,0)
* 구현
*   1. 우선 w 갯수 카운트 >> 해당 울프의 n이됨
*   2. 새로운 단어가 올 경우, 순서대로 오는지 확인, n갯수 맞는지 확인
*   3. 같은 단어가 올 경우, 카운트 세며 n 조건 체크
*   4. 반복
* */
public class boj_13022 {
    static String word;
    static char[] wolf = {'w', 'o', 'l', 'f'};
    static int n = 0;
    private static int setW(int start) {        // w일 경우 n세팅
        int startO = -1;
        n = 0;
        for (int i = start; i < word.length(); i++) {
            if (i==start && word.charAt(i)!='w') {
                return -1;
            }
            if (word.charAt(i)=='w') n++;
            else {
                startO = i;
                break;
            };
        }
        return startO;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        word = br.readLine();

        // w일 경우, n 초기화
        int startO = setW(0);
        if (startO == -1 || startO == word.length()) {     // 올바른 단어 아닐 경우 0출력
            System.out.println(0);
            return;
        }

        // 단어 확인
        int tmp = 0;
        int idx = 1;
        for (int i = startO; i < word.length(); i++) {
            char cur = word.charAt(i);

            if (cur != wolf[idx]) {
                System.out.println(0);
                return;
            }

            tmp++;
            if (tmp == n) {         // tmp가 n갯수만큼 되면 idx업데이트
                idx = (idx + 1) % 4;
                tmp = 0;
            }

            if (idx == 0 && i<word.length()-1) {    // w일 경우 n 새로 세팅 ( 마지막 글자는 실행X)
                int updateO = setW(i+1);
                if (updateO == -1) {
                    System.out.println(0);
                    return;
                }
                i = updateO - 1;
                idx = 1;
                tmp = 0;
            }

        }

        // 모든 단어 체크 후 idx가 올바른 wolf로 끝났는지 확인
        System.out.println((idx != 0 || tmp != 0) ? 0 : 1);     // idx==0:패턴이 f로 완전히 끝남, tmp==0: 모든 문자가 n번 잘 반복하고 끝남
    }
}

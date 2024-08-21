package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
* 오리
* 문제설명: 최소 오리 몇마린지 구하기
* 설계 (X)
*   - 스택 이용
*   - 순서에 맞춰 quack이 되면 cnt++, 스택에서 없애기
*   - q일때 새로운 스택 생성 후 넣기
*   - q가 아닐때, 스택에 넣기
*       - 연속되는 인덱스일때 tmp++, tmp가 5가 되면 오리++, tmp이전 인덱스부터 다시 시작
* 구현
*   - 울음소리를 돌면서 q가 나오면 완성되는 울음소리 카운트
*       - 이때, 오리 한마리가 여러번 울 경우는 cnt 하지않음
*   - 울음소리 체크를 위해 check[] 불리언 배열 놓기
*   - 예외 처리
*       - 첫 글자가 q로 시작안할때
*       - 모든 울음소리가 5배수가 아닐때
*       - 완성되지 못한, (체크되지 못한) ckeck 배열 값이 존재할때
* */
public class boj_12933 {
    static char[] duck = new char[]{'q', 'u', 'a', 'c', 'k'};
    static char[] sound;
    static int N;
    static boolean[] check;
    static int cnt = 0;

    private static void findDuck(int idx) {
        int tmp = 0;
        boolean one = true;

        for (int i = idx; i < N; i++) {
            if (sound[i] == duck[tmp] && !check[i]) {     // 현재 소리가 올바른 울음소리 순서이고, 체크안했을때
                tmp++;                                    // 울음소리 순서++
                check[i] = true;                          // 해당 소리 체크

                if (sound[i]=='k'){                       // 마지막 울음소리일때 (울음소리 완성)
                    if (one){                             // 한명이 처음 불렀을때만 cnt++, 완성된 울음이 이전에 부른애면 cnt안함
                        cnt++;
                        one = false;
                    }
                    tmp = 0;                              // 울음소리 순서 초기화
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sound = br.readLine().toCharArray();

        N = sound.length;
        if (sound[0]!='q' || N%5!=0){
            System.out.println(-1);
            System.exit(0);
        }

        check = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (sound[i]=='q' && !check[i]) findDuck(i);
        }

        boolean allCheck = true;
        for (boolean c : check) {
            if (!c){
                allCheck = false;
                break;
            }
        }
        if (!allCheck || cnt==0) System.out.println(-1);
        else System.out.println(cnt);
    }
}

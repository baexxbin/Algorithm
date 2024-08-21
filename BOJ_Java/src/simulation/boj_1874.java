package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/*
* 스택 수열
* 문제 설명
*   - '1~n까지 수'를 스택에 넣었다 빼서 '주어진 입력' 수열 만들기
*   - 오름차순으로만 push 가능
*   - 수열을 만들 수 있다면 연산 순서 출력(psuh(+), pop(-)), 없다면 NO 출력
* */
public class boj_1874 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        Deque<Integer> stack = new ArrayDeque<>();

        int idx = 0;                                        // 현재까지 스택에 넣은 숫자 (가장 큰 마지막 값)
        while (N-- > 0) {
            int cur = Integer.parseInt(br.readLine());      // 현재 입력 받은 수
            while (stack.isEmpty() || idx<cur) {            // 스택이 비었거나, cur이 되기까지 idx를 더 넣어야할 경우 push
                stack.addFirst(++idx);
                sb.append('+').append('\n');
            }
            if (stack.peekFirst() == cur) {                 // 스택 상단 값이 cur인 경우 pop
                stack.removeFirst();
                sb.append('-').append('\n');
            }
        }
        System.out.println((!stack.isEmpty() ? "NO" : sb));
    }
}

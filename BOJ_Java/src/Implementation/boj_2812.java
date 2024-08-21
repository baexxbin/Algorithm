package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/*
* 크게 만들기
* 문제 설명: N자리 수에서 K개 지울때, 가장 큰 수 만들기 >> 앞에서부터 작은 수 없애야함
* 설계: 스택으로 peek값이 현재 값보다 작으면 pop하기, pop한 갯수가 K개되면 끝
* */
public class boj_2812 {
    static int N, K;
    static int[] num;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        String line = br.readLine();
        num = new int[N];
        num = Stream.of(line.split("")).mapToInt(Integer::parseInt).toArray();

        Deque<Integer> stack = new ArrayDeque<>();
        for (int cur : num) {
            if (stack.isEmpty() || stack.peekFirst() >= cur) {
                stack.addFirst(cur);
                continue;
            }

            while (!stack.isEmpty() && K>0 && stack.peekFirst() < cur) {
                stack.removeFirst();
                K--;
            }
            stack.addFirst(cur);
        }

        // 예외, 모든 같은 수로 이뤄졌을때, 내림차순은 뒤에서부터 빼기 >> K를 소진 못했을땐 뒤에서 부터 빼기
        while (K-- > 0) {
            stack.remove();
        }

        for (int n : stack) {
            sb.append(n);
        }
        System.out.println(sb.reverse());
    }
}

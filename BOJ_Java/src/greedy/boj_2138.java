package greedy;
/*
* 전구와 스위치
* 문제 설명: 스위치를 누르면 현재,양옆의 전구상태가 바뀔때, 최소 몇번으로 만들고자하는 상태를 만들 수 있는지
* 구현
*   - 앞에서 부터 만들어나가기.
*   - 이때, 0번 스위치 위치 고정(0,1)한 후 탐색시작 >> 위치를 고정해나가며 앞으로 나아가기
*   - 그렇게되면 현재 스위치(i)에 영향을 주는건 (i+1)밖에 없음 >> i의 상태를 바꾸기위해 i+1조작
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class boj_2138 {
    static int N;
    static int[] state;
    static int[] target;

    private static int OnOff(int[] before, boolean press) {
        int[] tmp = before.clone();
        int cnt = 0;

        if (press) {        // 0번째 값 설정
            cnt++;
            tmp[0] ^= 1;
            tmp[1] ^= 1;
        }

        for (int i = 1; i < N - 1; i++) {
            if (tmp[i-1]!=target[i-1]){     // 상태(i-1)가 목표상태랑 다르면(0부터 탐색)
                cnt++;                  // 스위치 조작횟수++
                tmp[i-1] ^= 1;          // 현재위치(i-1) 값 변경
                tmp[i] ^= 1;            // i을 조작해서
                tmp[i + 1] ^= 1;        // i+1도 영향받음
            }
        }

        if (tmp[N - 2] != target[N - 2]) {      // 마지막 전구 조작
            cnt++;
            tmp[N - 2] ^= 1;
            tmp[N - 1] ^= 1;
        }

        return Arrays.equals(tmp, target) ? cnt : Integer.MAX_VALUE;        // 만들 수 있다면 cnt, 아니면 INF값 반환
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        state = new int[N];
        target = new int[N];

        state = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        target = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

        int ans = Integer.MAX_VALUE;
        ans = Math.min(ans, OnOff(state, false));
        ans = Math.min(ans, OnOff(state, true));

        System.out.println((ans == Integer.MAX_VALUE ? -1 : ans));
    }
}

package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 싸이버개강총회
 * 문제 설명: 개강총회 입장, 퇴장이 확인된 학회원 수 구하기
 *   - 개총 시작 전 회원 체크
 *   - 개총, 스트링밍 끝난 후 회원 체크
 * 설계
 *   - 입력이 10만 이하로 O(N)안쪽으로 해결하기
 *   - HashSet으로 구현, 시작전까지 채팅한 회원 구하기, 종료 이후 채팅에 시작 회원있는지 체크
 * */
public class boj_19583 {
    private static int str2time(String time) {      // HH:MM형태 시간 숫자(분)으로 변환
        String[] tm = time.split(":");
        return Integer.parseInt(tm[0]) * 60 + Integer.parseInt(tm[1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] streamTime = new int[3]; // 0:S, 1:E, 2:Q
        for (int i = 0; i < 3; i++) {
            String time = st.nextToken();
            streamTime[i] = str2time(time);
        }

        Set<String> attendBefore = new HashSet<>();
        Set<String> attendAfter = new HashSet<>();

        String line = null;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] p = line.split(" ");
            int time = str2time(p[0]);
            String name = p[1];

            if (time <= streamTime[0]) {    // 개총 시작 전
                attendBefore.add(name);
            }
            if (time >= streamTime[1] && time <= streamTime[2]) {   // 개총끝 ~ 스트리밍 종료 사이
                attendAfter.add(name);
            }
        }

        // 개강총회 시작 전과 종료 후 모두 출석한 사람 cnt++
        int ans = 0;
        for (String name : attendBefore) {
            if (attendAfter.contains(name)) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}

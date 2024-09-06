package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 공유기 설치
* 가장 공유기 거리를 크게해서 설치할때 거리 구하기
* 구현
*  - 이분탐색으로 적절한 거리 구하기
*  - 해당 거리로 커버 가능한지 확인
*  - 커버 여부에따라 조절
* */
public class boj_2110 {
    static int N, C;
    static int[] house;

    private static boolean canPlace(int len) {
        int cnt = 1;
        int lastPoint = house[0];
        for (int i = 1; i < N; i++) {
            if (house[i] - lastPoint >= len) {
                cnt++;
                lastPoint = house[i];
            }
            if (cnt >= C) return true;      // 공유기 수 충분하면 탈출
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        house = new int[N];
        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);

        int left = 1;                    // 공유기 최소 거리는 1은 되야함
        int right = house[N - 1] - house[0];        // 공유기 최대 거리는 처음 끝 차
        int res = 0;
        while (left <= right){
            int len = left + (right - left) / 2;
            if (canPlace(len)) {        // 공유기 설치가 가능하면 길이를 늘려봄
                res = len;
                left = len+1;
            } else {                    // 설치가 불가능하면 길이를 줄임
                right = len-1;
            }
        }
        System.out.println(res);
    }
}

package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
* 한 줄로 서기
* 12:46 - 13:20
* 문제 설명: 키가 1부터 N까지인 사람 N명이 존재. 자기 왼쪽에 자신보다 큰 사람 수가 주어질때, 올바른 줄 서기 구하기
* 설계
*   - 오름차순일때 자신의 왼쪽은 작은애들이 온다., N번째는 항상 0
*   - N부터 1까지 내림차순으로 주어진 값에 해당하는 인덱스로 이동 << 리스트 중간 삽입
*       - 자기보다 작은애들 영향은 안받기때문에 내림차순으로 진행
* */
public class boj_1138 {
    static int N;
    static int[] tall;
    static List<Integer> line = new LinkedList<>();
    private static void bisec(int idx) {

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        tall = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tall[i] = Integer.parseInt(st.nextToken());
        }

        line.add(N);
        for (int i = N - 2; i >= 0; i--) {
            line.add(tall[i], i+1);
        }

        for (int num : line) {
            sb.append(num).append(' ');
        }
        System.out.println(sb);
    }
}

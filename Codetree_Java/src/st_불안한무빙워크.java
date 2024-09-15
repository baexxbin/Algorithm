import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 불안한 무빙워크
 * 문제 설명: 무빙워크의 안정성과 종료 조건이 주어질때 몇번째 실험인지 찾기
 * */
public class st_불안한무빙워크 {
    static int N, K, cnt;
//    static Node[] arr;
    static int[] arr;
    static boolean[] isPeople;
    static int time = 0;

//    static class Node {
//        int durability;
//        boolean hasPerson;
//
//        Node(int durability, boolean hasPerson) {
//            this.durability = durability;
//            this.hasPerson = hasPerson;
//        }
//    }

    private static void moveMoving() {
//        Node tmp = new Node(arr[2 * N - 1].durability, arr[2 * N - 1].hasPerson);
//        for (int i = 2 * N - 1; i > 0; i--) {
//            arr[i] = arr[i - 1];
//        }
//        arr[0] = tmp;
        arr[0] = arr[arr.length - 1];
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }

        for (int i = isPeople.length - 2; i > 0; i--) {
            isPeople[i + 1] = isPeople[i];
        }
        isPeople[N] = false;
        isPeople[1] = false;
    }

    private static void movePeople() {
//        for (int i = N - 1; i > 0; i--) {  // 마지막 칸(N-1)부터 역순으로 이동
//            if (!arr[i - 1].hasPerson) continue;
//            if (arr[i].hasPerson || arr[i].durability == 0) continue;
//            arr[i].hasPerson = true;
//            arr[i - 1].hasPerson = false;
//            arr[i].durability--;
//
//            if (arr[i].durability == 0) cnt++;
//        }
        for (int i = N - 1; i > 0; i--) {
            if (!isPeople[i]) continue;
            if (isPeople[i+1]||arr[i+1]==0) continue;
            isPeople[i+1] = true;
            isPeople[i] = false;
            arr[i+1]--;

            if (arr[i+1]==0) cnt++;
        }
    }

    private static void onPeople() {
//        if (arr[0].durability==0) return;
//        arr[0].hasPerson = true;
//        arr[0].durability--;
//        if (arr[0].durability==0) cnt++;
        if (arr[1]==0) return;
        isPeople[1] = true;
        arr[1]--;
        if (arr[1]==0) cnt++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

//        arr = new Node[2 * N + 1];
        arr = new int[2 * N + 1];
        isPeople = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < arr.length; i++) {
//            arr[i] = new Node(Integer.parseInt(st.nextToken()), false);
            arr[i] = Integer.parseInt(st.nextToken());
        }

        while (true) {
            time++;

            // 배열 회전
            moveMoving();

            // 사람 이동
            movePeople();

            // 첫 번째 칸에 사람 올리기
            onPeople();

            if (cnt >=K) break;
        }
        System.out.println(time);
    }
}

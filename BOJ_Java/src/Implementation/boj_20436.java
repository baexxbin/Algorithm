package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/*
* ZOAC 3
* 문제설명: 맨하튼 거리로 좌표를 이동할때, 문자열을 출력하는데 걸리는 최소시간 구하기
* 설계
*   - 출발점에서 주어진 문자 하나씩 이동해서 시간구하기
* */
public class boj_20436 {
    static HashMap<Character, Coordi> leftKey = new HashMap<>();
    static HashMap<Character, Coordi> rightKey = new HashMap<>();

    private static int calDist(Coordi hand, Coordi cur) {
        return Math.abs(hand.x - cur.x) + Math.abs(hand.y - cur.y) + 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 키보드 값 설정
        initQwerty();

        // 입력 처리
        String[] inputs = br.readLine().split(" ");
        char leftStart = inputs[0].charAt(0);
        char rightStart = inputs[1].charAt(0);
        String word = br.readLine();

        Coordi left = leftKey.get(leftStart);
        Coordi right = rightKey.get(rightStart);

        int time = 0;

        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (leftKey.containsKey(c)) {
                time += calDist(left, leftKey.get(c));
                left = leftKey.get(c);
            }else{
                time += calDist(right, rightKey.get(c));
                right = rightKey.get(c);
            }
        }

        System.out.println(time);
    }

    private static void initQwerty() {

        leftKey.put('q', new Coordi(1, 1));
        leftKey.put('w', new Coordi(1, 2));
        leftKey.put('e', new Coordi(1, 3));
        leftKey.put('r', new Coordi(1, 4));
        leftKey.put('t', new Coordi(1, 5));
        leftKey.put('a', new Coordi(2, 1));
        leftKey.put('s', new Coordi(2, 2));
        leftKey.put('d', new Coordi(2, 3));
        leftKey.put('f', new Coordi(2, 4));
        leftKey.put('g', new Coordi(2, 5));
        leftKey.put('z', new Coordi(3, 1));
        leftKey.put('x', new Coordi(3, 2));
        leftKey.put('c', new Coordi(3, 3));
        leftKey.put('v', new Coordi(3, 4));

        rightKey.put('y', new Coordi(1, 6));
        rightKey.put('u', new Coordi(1, 7));
        rightKey.put('i', new Coordi(1, 8));
        rightKey.put('o', new Coordi(1, 9));
        rightKey.put('p', new Coordi(1, 10));
        rightKey.put('h', new Coordi(2, 6));
        rightKey.put('j', new Coordi(2, 7));
        rightKey.put('k', new Coordi(2, 8));
        rightKey.put('l', new Coordi(2, 9));
        rightKey.put('b', new Coordi(3, 5));
        rightKey.put('n', new Coordi(3, 6));
        rightKey.put('m', new Coordi(3, 7));
    }

    private static class Coordi {
        int x, y;

        Coordi(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

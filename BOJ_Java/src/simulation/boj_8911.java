package simulation;

/*
* 거북이
* 10:45 - 12:12
* - 거북이 회전 구현하기
*   (0,1,2,3) 상우하좌
*   L : (i+3)%4
*   R : (i+1)%4
* - 거북이가 지나간 좌표(꼭짓점) 확인
*   이를 바탕으로 xmn,xmx, ymn,ymx구해서 최소 직사각형 넓이 구하기
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class boj_8911 {
    static int T;
    static int[] dx = {0, 1, 0, -1};    // 상, 우, 하, 좌 (좌표평면 x,y값)
    static int[] dy = {1, 0, -1, 0};
    static ArrayList<Node> lst = new ArrayList<>();

    private static Node move(Node cur, char c) {
        if (c == 'F') {
            int nx = cur.x + dx[cur.d];
            int ny = cur.y + dy[cur.d];
            return new Node(nx, ny, cur.d);
        }
        int nx = cur.x - dx[cur.d];
        int ny = cur.y - dy[cur.d];
        return new Node(nx, ny, cur.d);
    }

    private static void control(String cmd) {
        Node turtle = new Node(0, 0, 0);
        lst.add(new Node(0, 0, 0));

        for (int i = 0; i < cmd.length(); i++) {
            char c = cmd.charAt(i);
            if (c=='F' || c=='B') {
                Node nxt = move(turtle, c);
                lst.add(nxt);           // 새로운 좌표 넣기
                turtle.x = nxt.x;
                turtle.y = nxt.y;
                continue;
            }

            // 방향 전환
            if (c=='L'){
                turtle.d = (turtle.d + 3) % 4;
            }else {
                turtle.d = (turtle.d + 1) % 4;
            }
        }
    }

    private static int calRect() {
        int mxX = Integer.MIN_VALUE;
        int mxY = Integer.MIN_VALUE;
        int mnX = Integer.MAX_VALUE;
        int mnY = Integer.MAX_VALUE;

        for (Node node : lst) {
            if (node.x < mnX) mnX = node.x;
            if (node.x > mxX) mxX = node.x;
            if (node.y < mnY) mnY = node.y;
            if (node.y > mxY) mxY = node.y;
        }
        return (mxX - mnX) * (mxY - mnY);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        while (T > 0) {
            String cmd = br.readLine();
            control(cmd);
            sb.append(calRect()).append('\n');
            lst.clear();
            T--;
        }
        System.out.println(sb);
    }

    static class Node{
        int x,y, d;

        Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}

/*
* 주의해야할 것
* - 행열 좌표가 아닌 좌표평면x,y값으로 계산
* - 객체 참조 유의하기, lst에 넣은 Node 변경 시 lst에 넣은 값 변경 >> 새로운 객체를 생성해서 넣기
* */
import java.util.*;

// 요구사항 테이블 초기화 (행번호, 이름(숫자))
// 입력수행
// - 이분 탐색: U,D,C에 대한 인덱스 구하기
//  - 삭제는 cur 인덱스 예외처리
//  - 복구: 스택 사용, 원래 인덱스로 중간삽입, cur 인덱스 재정의
public class lv3_표편집 {
    public static void main(String[] args) {
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
        System.out.println(solution(n, k, cmd));
    }

    public static String solution(int n, int k, String[] cmd) {
        int[] pre = new int[n];
        int[] nxt = new int[n];
        for (int i = 0; i < n; i++) {
            pre[i] = i - 1;
            nxt[i] = i + 1;
        }
        nxt[n - 1] = -1;
        Deque<Node> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder("O".repeat(n));
        StringTokenizer st;

        for (String cm : cmd) {
            st = new StringTokenizer(cm);
            char c = st.nextToken().charAt(0);
            int num = 0;
            if (st.hasMoreTokens()) num = Integer.parseInt(st.nextToken());

            switch (c) {
                case 'U':
                    while (num-- > 0) {
                        k = pre[k];
                    }
                    break;
                case 'D':
                    while (num-- > 0) {
                        k = nxt[k];
                    }
                    break;
                case 'C':
                    stack.push(new Node(pre[k], k, nxt[k]));
                    if (pre[k] != -1) nxt[pre[k]] = nxt[k]; // 이전노드의 다음노드를 현재노드의 다음노드로 설정
                    if (nxt[k] != -1) pre[nxt[k]] = pre[k];
                    sb.setCharAt(k, 'X');

                    if (nxt[k]!=-1) k = nxt[k];
                    else k = pre[k];    // 마지막행일때 바로 윗행
                    break;
                case 'Z':
                    Node redo = stack.pop();
                    if (redo.pre != -1) nxt[redo.pre] = redo.cur;     // 복구한 노드의 이전노드가 가리키는 다음노드를 복구한 노드로 변경
                    if (redo.nxt != -1) pre[redo.nxt] = redo.cur;     // 복구한 노드의 다음노드가 가르키는 이전노드를 복구한 노드로 변경 >> 양옆의 포인터 복구한 노드로 다시 연결
                    sb.setCharAt(redo.cur, 'O');
                    break;
            }
        }
        return sb.toString();

    }
    static class Node {
        int pre, cur, nxt;

        Node(int pre, int cur, int nxt) {
            this.pre = pre;
            this.cur = cur;
            this.nxt = nxt;
        }
    }
}


//    public static String solution(int n, int k, String[] cmd) {
//        StringTokenizer st;
//        StringBuilder sb = new StringBuilder();
//
//        LinkedList<Integer> board = new LinkedList<>();
//        Deque<int[]> stack = new ArrayDeque<>();
//
//        int orign = n;
//
//        // 테이블 초기화
//        for (int i = 0; i < orign; i++) {
//            board.add(i);
//        }
//
//        int idx = k;
//        for (String cm : cmd) {
//            st = new StringTokenizer(cm);
//            char c = st.nextToken().charAt(0);
//            int num = 0;
//            if(st.hasMoreTokens()) num = Integer.parseInt(st.nextToken());
//
//            switch (c) {
//                case 'U':
//                    idx = (idx-num+n)%n;
//                    break;
//                case 'D':
//                    idx = (idx+num)%n;
//                    break;
//                case 'C':
//                    stack.push(new int[]{idx, board.get(idx)});
//                    board.remove(idx);
//                    n--;    // 전체 요소 수 감소
//                    idx = idx==n ? idx-1 : idx;
//                    break;
//                case 'Z':
//                    int[] redo = stack.pop();
//                    board.add(redo[0], redo[1]);
//                    n++;    // 전체 요소 수 증가
//                    idx = redo[0]<=idx ? idx+1 : idx;
//                    break;
//            }
//        }
//
//        boolean[] ans = new boolean[orign];
//        for (int i : board) {
//            ans[i] = true;
//        }
//
//        for (int i = 0; i < orign; i++) {
//            if (ans[i]) sb.append('O');
//            else sb.append('X');
//        }
//
////        // O(n log n)
////        Integer[] arr = board.toArray(new Integer[0]);
////        Arrays.sort(arr);
////        for (int i = 0; i < orign; i++) {
////            if (Arrays.binarySearch(arr, i)>=0) {
////                sb.append('O');
////            }else sb.append('X');
////        }
//        return sb.toString();
//    }

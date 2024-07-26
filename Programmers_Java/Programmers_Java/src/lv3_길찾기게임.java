import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lv3_길찾기게임 {
    public static void main(String[] args) {
        int[][] nodeinfo = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
        System.out.println(Arrays.deepToString(solution(nodeinfo)));
    }

    private static void preOrder(Node node, List<Integer> lst){     // 전위순회: 중, 왼, 오
        if (node==null) return;
        lst.add(node.idx);
        preOrder(node.left, lst);
        preOrder(node.right, lst);
    }

    private static void postOrder(Node node, List<Integer> lst) {   // 후위순회: 왼, 오, 중
        if (node==null) return;
        postOrder(node.left, lst);
        postOrder(node.right, lst);
        lst.add(node.idx);
    }

    private static void insertNode(Node parent, Node child) {
        if (child.x < parent.x) {
            if (parent.left == null) parent.left = child;
            else insertNode(parent.left, child);
        }else {
            if (parent.right == null) parent.right = child;
            else insertNode(parent.right, child);
        }
    }
    public static int[][] solution(int[][] nodeinfo) {
        int N = nodeinfo.length;
        Node[] tree = new Node[N];

        // 트리 구성 -> y값 내림차순 정렬 >> 부모노드 순 정렬, x값 오름차순 정렬 >> 왼, 오
        for (int i = 0; i < N; i++) {
            tree[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
        }
       Arrays.sort(tree);

        // 연결리스트 이진트리 만들기
        Node root = tree[0];
        for (int i = 1; i < N; i++) {
            insertNode(root, tree[i]);
        }

        List<Integer> pre = new ArrayList<>();
        List<Integer> post = new ArrayList<>();

        preOrder(root, pre);
        postOrder(root, post);

        int[][] ans = new int[2][N];
        for (int i = 0; i < N; i++) {
            ans[0][i] = pre.get(i);
            ans[1][i] = post.get(i);
        }
        return ans;
    }

    static class Node implements Comparable<Node> {
        int idx, x, y;
        Node left = null;
        Node right = null;

        Node(int idx, int x, int y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            if (this.y == o.y) return Integer.compare(this.x, o.x);
            return Integer.compare(o.y, this.y);
        }
    }
}

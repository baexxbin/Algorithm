import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_14003 {
    static int N;
    static int[] arr;
    static int[] idx;
    static ArrayList<Integer> lst;

    private static int binary_search(int target) {
        int left = 0;
        int right = lst.size();

        while (left < right) {
            int mid = left+(right-left)/2;
            if (lst.get(mid) >= target) {
                right = mid;
            }else {
                left = mid+1;
            }
        }
        return left;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        idx = new int[N];
        lst = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lst.add(arr[0]);
        idx[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > lst.get(lst.size() - 1)) {
                lst.add(arr[i]);
                idx[i] = lst.size()-1;  // 현재 요소의 인덱스 설정
            } else {
                int findIdx = binary_search(arr[i]);
                lst.set(findIdx, arr[i]);
                idx[i] = findIdx;
            }
        }

        sb.append(lst.size()).append('\n');
        int lastIdx = lst.size()-1;
        for (int i = N - 1; lastIdx >= 0 && i >= 0; i--) {
            if (idx[i] == lastIdx){
                stack.push(arr[i]);
                lastIdx--;
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);
    }
}

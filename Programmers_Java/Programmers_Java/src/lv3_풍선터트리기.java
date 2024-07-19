import java.util.Arrays;

public class lv3_풍선터트리기 {
    public static int solution(int[] a) {
        int[] leftMn = new int[a.length];
        int[] rightMn = new int[a.length];

        int lmn = a[0];
        for (int i = 0; i < a.length; i++) {
            if(a[i]<lmn) lmn = a[i];
            leftMn[i] = lmn;
        }

        int rmn = a[a.length-1];
        for (int i = a.length-1; i >= 0; i--) {
            if (a[i]<rmn) rmn = a[i];
            rightMn[i] = rmn;
        }


        int cnt = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > leftMn[i] && a[i] > rightMn[i]) cnt++;
        }
        return a.length-cnt;

    }

    public static void main(String[] args) {
        int[] a = {-16, 27, 65, -2, 58, -92, -71, -68, -61, -33};
        System.out.println(solution(a));
    }
}

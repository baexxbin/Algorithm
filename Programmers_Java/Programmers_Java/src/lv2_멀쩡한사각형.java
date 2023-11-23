public class lv2_멀쩡한사각형 {
    public static void main(String[] args) {
        System.out.println(solution(8, 12));
    }
    public static long solution(int w, int h) {
        int gcd_val = gcd(w, h);
        return ((long) w * h) - (w + h - gcd_val);
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a%b);
    }
}

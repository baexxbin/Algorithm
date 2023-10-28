public class lv2_숫자카드나누기 {
    public static void main(String[] args) {
        int[] arrayA = {14, 35, 119};
        int[] arrayB = {18, 30, 102};
        solution(arrayA, arrayB);
    }

    public static int solution(int[] arrayA, int[] arrayB) {
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];

        for (int i = 1; i < arrayA.length; i++) {
            gcdA = gcd(gcdA, arrayA[i]);
            gcdB = gcd(gcdB, arrayB[i]);
        }

        for (int a: arrayA){
            if (a % gcdB == 0) {
                gcdB = 1;
                break;
            }
        }

        for (int a: arrayB){
            if (a % gcdA == 0) {
                gcdA = 1;
                break;
            }
        }

        return gcdA == gcdB ? 0 : Math.max(gcdA, gcdB);
    }

    public static int gcd(int a, int b){
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

}

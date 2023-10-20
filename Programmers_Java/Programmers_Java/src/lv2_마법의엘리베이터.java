public class lv2_마법의엘리베이터 {
    private static int result = Integer.MAX_VALUE;
    private static String str;
    public static void main(String[] args) {
        int storey = 16;
        solution(storey);
    }

    public static int solution(int storey) {
        str = Integer.toString(storey);
        int len = str.length();

        recursion(len-1, 0,0);
        System.out.println(result);
        return result;

    }
    private static void recursion(int idx, int offset, int magic){
        if (idx < 0) {
            result = Math.min(result, magic+offset);
            return;
        }
        int unit = Character.getNumericValue(str.charAt(idx))+offset;
        recursion(idx-1, 0, magic+unit);        // lower로 10단위 맞춤
        recursion(idx-1, 1, magic+10-unit);     // upper로 10단위 맞춤
    }
}

// 1) 10단위를 upper로 설정할때 그 다음자릿수 올림되어서 +1해줘야함
// 2) '10단위로 맞춘다'는 내용을 1의자리수로만 생각했는데 모든 자리수에 대해 진행해줘야한다
// * 자바에서 문자열의 특정자리 숫자를 숫자로 바꾸는 함수: Character.getNumericValue();
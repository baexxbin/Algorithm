import java.util.List;

public class lv2_아날로그시계 {
    public static void main(String[] args) {
        int h1 = 0;
        int m1 = 5;
        int s1 = 30;
        int h2 = 0;
        int m2 = 7;
        int s2 = 0;

        System.out.println(solution(h1, m1, s1, h2, m2, s2));
    }
    public static int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;

        int start = new Time(h1, m1, s1).toSecond();
        int end = new Time(h2, m2, s2).toSecond();

        for (int i = start; i < end; i++) {
            List<Double> cur = new Time(i).getDegree();
            List<Double> nxt = new Time(i+1).getDegree();

            boolean hMatch = hourMatch(cur, nxt);
            boolean mMatch = minuteMatch(cur, nxt);

            if (hMatch && mMatch) {
                if (Double.compare(nxt.get(0),nxt.get(1)) == 0) answer++;
                else answer+=2;
            } else if (hMatch || mMatch) {
                answer++;
            }
        }
        if(start == 0 || start == 43200) answer++;
        return answer;
    }

    private static boolean hourMatch(List<Double> cur, List<Double> nxt) {
        if (Double.compare(cur.get(0), cur.get(2)) > 0 && Double.compare(nxt.get(0), nxt.get(2)) <= 0) {
            return true;
        }
        if (Double.compare(cur.get(2), 354d) == 0 && Double.compare(cur.get(0), 354d) > 0) {
            return true;
        }
        return false;
    }

    private static boolean minuteMatch(List<Double> cur, List<Double> nxt) {
        if (Double.compare(cur.get(1), cur.get(2)) > 0 && Double.compare(nxt.get(1), nxt.get(2)) <= 0) {
            return true;
        }
        if (Double.compare(cur.get(2), 354d) == 0 && Double.compare(cur.get(1), 354d) > 0) {
            return true;
        }
        return false;
    }

    static class Time{
        int h, m, s;

        public Time(int h, int m, int s) {
            this.h = h;
            this.m = m;
            this.s = s;
        }

        public Time(int second) {
            this.h = second / 3600;
            this.m = (second%3600) / 60 ;
            this.s = (second%3600) % 60;
        }

        public int toSecond() {
            return h*3600 + m*60 + s;
        }

        List<Double> getDegree() {
            Double hDegree = (h % 12) * 30d + m*0.5d + s * (1/120d);
            Double mDegree = m * 6d + s * (0.1d);
            Double sDegree = s * 6d;

            return List.of(hDegree, mDegree, sDegree);
        }
    }
}

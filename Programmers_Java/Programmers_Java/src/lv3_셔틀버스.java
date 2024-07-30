import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 막차 타기 >> x+t*(n-1)시 출발 차 탐, 최소 도착 순서: n*m-1번째
// 막차 타기위해 안전하게 와야할 순서구하기
public class lv3_셔틀버스 {
    public static String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> que = new PriorityQueue<>();
        StringTokenizer st;
        for (String tm: timetable) {
            st = new StringTokenizer(tm, ":");
            int time = Integer.parseInt(st.nextToken())*60 + Integer.parseInt(st.nextToken());
            que.add(time);
        }

        int start = 540;
        int last = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt = 0;
            while (!que.isEmpty()) {
                int cur = que.peek();
                if (cur <= start && cnt < m) {
                    que.poll();
                    cnt++;
                } else break;   // 해당 조건 충족안되면 다음 버스
                last = cur-1;   // 각 시간대에서 마지막으로 올 시간 업데이트 (마지막으로 타는 사람보다 1분 빨리)
            }
            start+=t;
        }

        if (cnt < m) {          // 마지막 버스를 널널하게 탈 수 있으면(탑승자 수 < 전체 탑승 가능 수) 출발시간에 옴
            last = start-t;
        }

        // 시간 변환
        String hh = String.format("%02d", last/60);
        String mm = String.format("%02d", last%60);

        return hh + ":" + mm;
    }
    public static void main(String[] args) {
        int n = 1;
        int t =1;
        int m = 5;
        String[] timetable = {"08:00", "08:01", "08:02", "08:03"};
        System.out.println(solution(n, t, m, timetable));
    }
}


//    int lastBus = 540 + t * (n - 1);
//    int arriveIdx = n*m-1;      // 인덱스는 0부터 시작하므로 -1
//    int ans = lastBus;
//
//        if (arriveIdx > N-1) {        // 최소로 도착해야할 순서가 전체 사람수보다 클 때
//                ans = lastBus;
//                } else {
//                if (arriveIdx-1<0) arriveIdx = 1;
//        ans = Math.min(lastBus, order[arriveIdx-1]-1);  // 현재 마지막 탑승자보다 1분빨리 와야함
//        }

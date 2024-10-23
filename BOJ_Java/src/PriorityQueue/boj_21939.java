package PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 큰값, 작은값 나눠서 관리, 삭제되었을때 표시해야함, 같은 번호 다른 난이도로 들어올 수 있음, 키값 번호로 사용 불가.. >> 해시코드 값 이용
// 우선순위큐 넣을땐 비교 오버라이드(번호, 레벨같으면 같은 값, 어짜피 번호같아도 레벨 다르면 다른 해시코드 값)
// 그럼 set엔 문제 객체가 아닌 해시코드 값넣어 관리 >> X 문제 없어지고 같은 번호가 추가되어서 상관없음
public class boj_21939 {
    static int N, M;
    static Set<Integer> check = new HashSet<>();        // 풀어야할 문제들
    static Map<Integer, Problem> notes = new HashMap<>();   // 문제 번호, 문제
    static StringBuilder sb = new StringBuilder();

    private static void recommend(PriorityQueue<Problem> pq) {
        while (!pq.isEmpty()) {
            Problem p = pq.peek();
            if (check.contains(p.idx)){     // 풀어야할 문제에 속하면 추천
                sb.append(p.idx).append('\n');
                break;
            }
            pq.poll();              // 풀 문제가 아니면(이미 푼 문제) 삭제
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        PriorityQueue<Problem> pqHard = new PriorityQueue<>();
        PriorityQueue<Problem> pqEasy = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            Problem problem = new Problem(p, l);
            pqHard.add(problem);
            pqEasy.add(problem);
            check.add(p);
            notes.put(p, problem);
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("add")){
                int p = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());

                if (notes.containsKey(p)) {         // 동일한 번호가 들어오면, 이전 번호 삭제
                    Problem prev = notes.get(p);
                    pqHard.remove(prev);
                    pqEasy.remove(prev);
                    check.remove(prev.idx);
                }
                Problem problem = new Problem(p, l);
                pqHard.add(problem);
                pqEasy.add(problem);
                notes.put(p, problem);
                check.add(p);
            } else if (cmd.equals("solved")) {
                int p = Integer.parseInt(st.nextToken());
                check.remove(p);
            }else{
                int r = Integer.parseInt(st.nextToken());
                if (r==1) recommend(pqHard);
                else recommend(pqEasy);
            }
        }
        System.out.println(sb);
    }

    static class Problem implements Comparable<Problem>{
        int idx, level;

        public Problem(int idx, int level) {
            this.idx = idx;
            this.level = level;
        }

        @Override
        public int compareTo(Problem o) {
            if (o.level-this.level==0) {
                return o.idx - this.idx;
            }
            return o.level - this.level;
        }
    }
}

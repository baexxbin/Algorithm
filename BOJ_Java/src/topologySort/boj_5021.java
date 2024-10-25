package topologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 위상정렬 이용, 각 사람에 대한 진입차수 관리 필요 >> 스트링에 대한 값이므로 해시맵 이용
 * 또한, 각 사람의 부모 정보를 알아야함 >> 얘 또한 해시맵
*/

public class boj_5021 {
    static int N, M;
    static HashMap<String, Integer> ingree = new HashMap<>();
    static HashMap<String, List<String>> family = new HashMap<>();      // 키: 부모, 값: 자식 리스트
    static HashMap<String, Double> blood = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Queue<String> que = new PriorityQueue<>();
        String king = br.readLine();
        blood.put(king, 1.0);
        ingree.put(king, 0);
        que.add(king);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String p1 = st.nextToken();
            String p2 = st.nextToken();

            //  부모 키값에 자식 넣어주기
            family.computeIfAbsent(p1, k -> new ArrayList<>()).add(child);
            family.computeIfAbsent(p2, k -> new ArrayList<>()).add(child);

            // 부모의 진입차수 초기화
            ingree.putIfAbsent(p1, 0);
            ingree.putIfAbsent(p2, 0);

            // 자식이라면 진입차수 2로 설정
            ingree.put(child, 2);

            // 혈통 0으로 초기화
            blood.putIfAbsent(p1, 0.0);
            blood.putIfAbsent(p2, 0.0);
            blood.putIfAbsent(child, 0.0);
        }

        for (String parent : family.keySet()) {     // 왕을 제외한, 부모 노드 큐에 넣고 피 초기화
            if (parent.equals(king)) continue;
            if (ingree.get(parent)==0) que.add(parent);
        }

        while (!que.isEmpty()) {
            String cur = que.poll();
            if (family.containsKey(cur)) {      // 자식을 가진 부모라면
                for (String ch : family.get(cur)) {
                    blood.put(ch, blood.get(ch) + blood.get(cur) * 0.5);        // 자기 혈통 반쪽 물려주고
                    ingree.put(ch, ingree.get(ch) - 1);                         // 물려줬으니 진입차수--
                    if (ingree.get(ch)==0) que.add(ch);
                }
            }
        }

        String ans = "";
        double mx = -1.0;
        for (int i = 0; i < M; i++) {
            String candi = br.readLine();
            double bld = blood.getOrDefault(candi, 0.0);    // 가족도에 없는 후보는 0처리
            if (bld > mx){
                mx = bld;
                ans = candi;
            }
        }
        System.out.println(ans);
    }
}

/*
* 스트링에 대한 값을 다루기에 해시맵 이용
* 가족도의 구성은, 부모 키값으로 가지고 있는 자식을 리스트로 관리
* 이렇게 해야 진입차수0인 부모를 큐에서 가져왔을때, 혈통을 물려주고 자식의 진입차수를 지워나갈 수 있음
* */

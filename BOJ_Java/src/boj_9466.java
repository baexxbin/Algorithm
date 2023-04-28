import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 방향그래프 사이클 찾기, 역방향 간선의 존재여부를 이용
// 역방향 간선 : dfs탐색 시 어떤 노드가 방문상태인데 탐색 종료는 되지 않았을때 해당
//
// discoverd[node] - discoverd[hope] + 1 : 사이클에 속한 노드 수 (해당 사이클의 시작노드와 마지막으로 합류한 노드 사이의 거리)
public class boj_9466 {
    static int T;
    static int[] students;
    static int[] discovered;
    static boolean[] finished;
    static int order, group;

    public static void dfs(int node) {
        discovered[node] = order++;         // node의 방문한 순서 기록
        int hope = students[node];          // node가 팀하고 싶은 학생: hope
        if (discovered[hope] == 0) {       // hope가 탐색되지 않았으면
            dfs(hope);                      // hope 탐색(dfs) 진행
        } else if (!finished[hope]) {        // 탐색진행 후(방문상태)에도 아직 종료되지 않았다면 역방향 간선, 사이클 존재
            group += discovered[node] - discovered[hope] +1;    // 그룹을 이룬 사람들에 해당 사이클의 노드 수 더해주기
        }
        finished[node] = true;              // 탐색 종료 표시
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            discovered = new int[N];            // 해당 노드를 몇번째로 방문했는지
            finished = new boolean[N];           // 해당노드가 호출한 dfs가 종료되었는지
            Arrays.fill(discovered, 0);
            Arrays.fill(finished, false);
            students = new int[N];
            order = 0;                          // 노드를 방문한 순서
            group = 0;                          // 그룹을 이룬 학생들의 수
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                students[i] = Integer.parseInt(st.nextToken()) -1;
            }

            for (int i = 0; i < N; i++) {       // 학생들을 돌면서
                if (!finished[i]) {              // 해당 학생을 대상으로 진행한 dfs가 종료되지 않았으면 dfs진행
                    dfs(i);
                }
            }
            System.out.println(N-group);
        }
    }
}

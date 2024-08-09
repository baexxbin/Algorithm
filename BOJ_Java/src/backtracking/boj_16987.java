package backtracking;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 문제 설명: 내구도와 무게가 주어진 계란끼리 쳐서 최대한 많은 계란깨기
// 이때, 일렬로 놓인 계란의 왼쪽부터 차례대로 치며, 상대계란은 안깨진 계란 중 하나
public class boj_16987 {
    static int N;
    static Egg[] eggs;
    static int mx = 0;

    private static void dfs(int depth) {
        if (depth == N) {
            int cnt = 0;
            for (Egg e : eggs) {
                if (e.strong <=0) cnt++;
            }
            mx = Math.max(mx, cnt);
            return;
        }

        if (eggs[depth].strong <= 0) {      // 현재 계란이 깨졌을때 넘어가기
            dfs(depth+1);
            return;
        }

        boolean allBroken = true;           // for문안에서 계란이 다깨져서 dfs못돌경우를 위한 flag
        for (int i = 0; i < N; i++) {
            if (i==depth || eggs[i].strong <=0) continue;

            allBroken = false;
            eggs[i].strong -= eggs[depth].weight;
            eggs[depth].strong -= eggs[i].weight;
            dfs(depth+1);
            eggs[i].strong += eggs[depth].weight;
            eggs[depth].strong += eggs[i].weight;
        }

        if (allBroken) dfs(depth+1);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        eggs = new Egg[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            eggs[i] = new Egg(s, w);
        }

        dfs(0);
        System.out.println(mx);
    }

    static class Egg{
        int strong;
        int weight;

        Egg(int strong, int weight) {
            this.strong = strong;
            this.weight = weight;
        }
    }
}

/*
* 계란 깨질 조건
* 상대를 깨기위해선 '무게'가 커야함, 깨는 계란은 정해져 있음, n번씩 다침 >> n번안에 많이 깨기
* 깨지기 위해선 '내구도'가 작아야함, 많은 계란을 깨기위해 내구도가 작은 계란부터 치면서 내구도 깎기
* 인줄 알았는데 다 깨봐야함 >> 어떤 계란을 깨느냐에 따라 그 이후에 수행에 영향이 달라짐 >> 모든 경우 탐색하기
* */
package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 소문난 칠공주
// 문제 설명: 칠공주 만들 수 있는 모든 경우의 수 구하기(7명 중 이다솜파 4명이상 존재)

/* 설계: 이다솜파를 시작으로 dfs를 돌면서 칠공주 구하기
* 이때 중복처리를 어떻게 할지 >> 비트마스킹으로 존재하는 조합인지 확인
*   종료 조건 >> 현재 1켜진 갯수 구하기: Integer.bitCount(state)
*   위치 체크
*       - i번째 자리 1켜져있나 확인: state & (1 << i) != 0
*       - i번째 자리에 1켜기: state | (1 << i)
*       - i번째 자리에 1끄기: state & ~(1<<i)
* 종료 조건, depth==7 or 임도현파 4이상
*   임도현파 갯수 매개변수로 관리
* */
public class boj_1941 {
    static char[][] board = new char[5][5];
    static boolean[] visited = new boolean[1 << 25];
    static int N = 5;
    static int ans = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static void dfs(int cnt, int state, int doyeon) {
        if (cnt == 7) {
            ans++;
            return;
        }
        if (doyeon > 3) return;        // 임도현파가 4명이상이면 칠공주 성립X, 더 진행할 필요없음

        for (int i = 0; i < 25; i++) {                  // 현재 state 돌면서 체크된 사람을 기준으로 dfs 진행
            if ((state & (1<<i)) == 0) continue;        // 선택되지 않은 사람은 패쓰

            int x = i / N;
            int y = i % N;

            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];
                if (!(0<=nx && nx<N && 0<=ny && ny<N)) continue;
                if (!visited[state | (1 << (nx*5+ny))]) {
                    visited[state | (1 << (nx*5+ny))] = true;
                    if (board[nx][ny] == 'Y') dfs(cnt+1, state | (1 << (nx*5+ny)), doyeon + 1);
                    else dfs(cnt+1, state|(1<<(nx*5+ny)), doyeon);
//                    visited[state | (1 << nxt)] = false;
                }
            }

        }


    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j);
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j]=='S') {
//                    int idx = i*N+j;
                    dfs(1, 1<<i*5+j, 0);
                }
            }
        }

        System.out.println(ans);
    }
}

/*
* 단순히 S일때 dfs를 돌리는건 7공주 조합을 찾는것일뿐 '연결'되어있는지는 검사하지 않음!
* int idx = j*N+i; 형태로 비트연산하면 올바르게 동작안함..
* */

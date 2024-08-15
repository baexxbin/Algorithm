package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 2048 (Easy)
* 문제 설명: 2048게임 구현하기, 최대 5번이동으로 만들 수 있는 가장 큰 블록 값 구하기
* 1. 판 기울이기
*   - 기울였을때 값 계산하기
* 2. 최대값 구하는 모든 경우 완전탐색
*   - 4진법 이용
* */
public class boj_12100 {
    static int N;
    static int[][] board = new int[21][21];
    static int[][] newBoard = new int[21][21];

    private static void tilt(int d) {
        while (d-- > 0) {       // tilt는 왼쪽밀기이므로 밀어야하는 방향에 맞게 배열 회전
            rotate();
        }

        for (int i = 0; i < N; i++) {
            int[] titled = new int[N];                                  // 기울인 값 넣을 배열
            int idx = 0;
            for (int j = 0; j < N; j++) {
                if (newBoard[i][j] == 0) continue;                      // 보드 값이 0일 경우 패쓰
                if (titled[idx]==0) titled[idx] = newBoard[i][j];       // titled에 들어갈 곳이 0이면 들어갈 수 있음
                else if (titled[idx] == newBoard[i][j]) {               // 다른 값이 있는데 보드 값이랑 같다면 합쳐지기, 인덱스 업데이트
                    titled[idx] *= 2;
                    idx++;
                }else {                                                 // 서로 다른 값이라면 다음 칸에 값 넣어주기
                    idx++;
                    titled[idx] = newBoard[i][j];
                }
            }
            System.arraycopy(titled, 0, newBoard[i], 0, N);     // 만든 titled배열로 업데이트
        }
    }

    private static void rotate() {
        int[][] tmpBoard = new int[21][21];
        for (int i = 0; i < N; i++) {
            System.arraycopy(newBoard[i], 0, tmpBoard[i], 0, N);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newBoard[i][j] = tmpBoard[N - 1 - j][i];
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int mx = 0;
        // 4^5=1024, 4방향으로 5번 움직일 수 있는 모든 경우의 수
        for (int tmp = 0; tmp < 1024; tmp++) {

            for (int i = 0; i < N; i++) {       // 원본 배열 복사
                System.arraycopy(board[i], 0, newBoard[i], 0, N);
            }

            int state = tmp;                    // 4진법(4방향)으로 기울일 방향 확인
            int dir;
            for (int i = 0; i < 5; i++) {
                dir = state%4;
                state/=4;
                tilt(dir);
            }

            // 최댓값 갱신
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    mx = Math.max(mx, newBoard[i][j]);
                }
            }
        }
        System.out.println(mx);
    }
}

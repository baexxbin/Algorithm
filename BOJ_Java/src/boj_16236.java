import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 물고기를 못먹어서 엄마 부를 때까지 진행 (첫번째 while문)
 *      * 물고기 먹었을 경우 : 상어 사이즈 업 진행
 *      * 물고기 못 먹었을 경우 : 엄마부르기 (코드 종료)
 * 물고기를 먹을때 까지 이동(bfs), 이때 물고기 먹으면 바로 탈출 -> 첫번째 while문으로
 *  * 물고기는 가장 가까운 거리에 있는 물고기 먹기 (우선순위 큐 or 물고기 리스트 이용)
 *      * 먹은 물고기 처리) 우선순위큐 : bfs의 if문 안에서, 물고기 리스트 : 먹은 물고기 반환으로 첫번째 while문에서
 */

public class boj_16236 {
    static int N ;
    static int[][] board;
    static Shark baby;
    static int ans;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9) {
                    baby = new Shark(i, j, 0);
                    board[i][j] = 0;
                }
            }
        }

        bfs();
        System.out.println(ans);
    }

    public static void bfs() {
        int size = 2;
        int eatFish = 0;

        while (true) {
            PriorityQueue<Shark> eatQue = new PriorityQueue<>();    // 가까운 물고기 순으로 먹어야함으로 우선순위 큐 이용
            boolean[][] visited = new boolean[N][N];

            eatQue.add(new Shark(baby.x, baby.y, 0));   // 이전 while문 진행 시 baby값이 있으므로 baby.dist가 아닌 0으로 초기화
            visited[baby.x][baby.y] = true;

            boolean babyEat = false;

            while (!eatQue.isEmpty()) {
                baby = eatQue.poll();

                if (board[baby.x][baby.y] != 0 && board[baby.x][baby.y] < size) {       // 현재 위치에 있는 물고기를 먹을 수 있을 경우, (먹은 물고기 처리)
                    board[baby.x][baby.y] = 0;                                          // 물고기 먹고
                    eatFish++;
                    ans+= baby.dist;                                                    // 거리 업데이트 (우선순위 높은 물고기 먹었으므로 가장 가까운 물고기 먹음)
                    babyEat = true;                                                     // 물고기 먹고 반복문 탈출하는 것이라고 표시
                    break;                                                              // 반복문 탈출
                }
                for (int i = 0; i < 4; i++) {                                           // 현재 위치 먹을 물고기가 없는 경우, 사방으로 이동
                    int nx = baby.x + dx[i];
                    int ny = baby.y + dy[i];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || board[nx][ny] > size) {
                        continue;
                    }
                    eatQue.add(new Shark(nx, ny, baby.dist + 1));
                    visited[nx][ny] = true;
                }
            }
            if (!babyEat) {                                                         // 물고기 못먹고 while문 탈출한 경우, (첫번째 while문의 종료 조건)
                break;                                                              // 엄마 불러야함
            }
            if (size == eatFish) {                                                  // 먹은 물고기양이 사이즈랑 같아지면
                size++;                                                             // 사이즈 업
                eatFish = 0;                                                        // 먹은 물고기 초기화
            }
        }

    }
}
class Shark implements Comparable<Shark> {
    int x;
    int y;
    int dist;

    public Shark(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    @Override
    public int compareTo(Shark o) {     // 조건에 따른 가까운 거리 우선순위 설정
        if (this.dist != o.dist) {
            return this.dist - o.dist;
        }
        if (this.x != o.x) {
            return this.x - o.x;
        } return this.y - o.y;
    }
}
package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
* 아기 상어
* 문제 설명: 아기 상어가 먹을 수 있는 물고기를 다 먹었을때 시간(초) 구하기
* 설계
*   - 먹을 수 있는 물고기와 거리 구하기
*   - 가장 가까운 물고기 먹으러가기 (이동한 만큼 시간++)
*   - 자신의 크기만큼 물고기를 먹었다면 크기++
*   - 반복
*  먹을 수 있는 물고기를 우선하는것이 아닌, 상어의 bfs를 돌며 물고기 먹을 수 있을때 먹기
* */
public class boj_16236 {
    static int N;
    static int[][] board;
    static Shark baby;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static int bfs() {
        int time = 0;
        int size = 2;
        int ateFish = 0;

        while (true) {
            PriorityQueue<Shark> canEatFish = new PriorityQueue<>();
            boolean[][] visited = new boolean[N][N];
            canEatFish.offer(new Shark(baby.x, baby.y, 0));
            visited[baby.x][baby.y] = true;
            boolean isEat = false;

            while (!canEatFish.isEmpty()) {
                Shark cur = canEatFish.poll();

                // 현재 위치에 먹을 물고기가 있을 경우, 물고기 먹기
                if (board[cur.x][cur.y] != 0 && board[cur.x][cur.y] < size) {
                    ateFish++;
                    time += cur.dist;
                    board[cur.x][cur.y] = 0;

                    baby.x = cur.x;             // 아기상어 위치 업데이트
                    baby.y = cur.y;

                    isEat = true;
                    break;                      // 물고기 먹었으면, 해당 위치에서 새로운 물고기 탐색
                }

                // 현재 위치에 먹을 물고기가 없을 경우, 사방으로 이동
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if (!(0<=nx && nx<N && 0<=ny && ny <N)) continue;
                    if (visited[nx][ny] || board[nx][ny] > size) continue;
                    canEatFish.offer(new Shark(nx, ny, cur.dist+1));
                    visited[nx][ny] = true;
                }
            }
            if (!isEat) break;
            if (size == ateFish) {
                size++;
                ateFish = 0;
            }
        }
        return time;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j]==9) {
                    baby = new Shark(i, j, 0);
                    board[i][j] = 0;
                }
            }
        }

        System.out.println(bfs());
    }
    static class Shark implements Comparable<Shark>{
        int x, y, dist;

        Shark(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Shark o) {
            int cmp = Integer.compare(this.dist, o.dist);
            if (cmp != 0) return cmp;
            int Xcmp = Integer.compare(this.x, o.x);
            if (Xcmp != 0) return Xcmp;
            return Integer.compare(this.y, o.y);
        }
    }
}

package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 다리 만들기
* 09:03 - 10:03
* 문제 설명: 섬들을 잇는 가장 짧은 다리 만들기
* 설계
*   - 각 섬별로 표시하기
*   - 섬의 가장 자리 구하기
*   - 가장자리에서 다른 섬까지 최단 거리 구하기
* */
public class boj_2146 {
    static int N;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Node> boundary = new ArrayList<>();
    static boolean[][] visitedSea;
    static int ans = Integer.MAX_VALUE;

    private static void build(Node n) {
        Queue<Node> que = new ArrayDeque<>();
        int[][] visited = new int[N][N];
        for (int[] row : visited) {
            Arrays.fill(row, -1);
        }
        que.add(new Node(n.x, n.y, n.idx));
        visited[n.x][n.y] = 0;

        while (!que.isEmpty()) {
            Node cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!(0 <= nx && nx < N && 0 <= ny && ny < N)) continue;

                if (board[nx][ny] > 0 && board[nx][ny] != cur.idx) {  // 다른 섬을 만났을때
                    ans = Math.min(ans, visited[cur.x][cur.y]);
                    return;  // 최단 거리 갱신 후 더 이상 탐색할 필요 없음
                }

                if (visited[nx][ny] >= ans) continue;

                if (board[nx][ny] == 0 && visited[nx][ny] == -1) {  // 바다일 경우
                    que.add(new Node(nx, ny, cur.idx));
                    visited[nx][ny] = visited[cur.x][cur.y] + 1;
                }
            }
        }
    }

    private static void bfs(int x, int y, int idx) {
        Queue<Node> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        que.add(new Node(x, y, idx));
        board[x][y] = idx;
        visited[x][y] = true;

        while (!que.isEmpty()) {
            Node cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!(0 <= nx && nx < N && 0 <= ny && ny < N)) continue;

                if (board[nx][ny] == 1 && !visited[nx][ny]) {  // 섬 탐색
                    que.add(new Node(nx, ny, idx));
                    board[nx][ny] = idx;
                    visited[nx][ny] = true;
                } else if (board[nx][ny] == 0 && !visitedSea[cur.x][cur.y]) {   // 경계선일 경우
                    visitedSea[cur.x][cur.y] = true;
                    boundary.add(new Node(cur.x, cur.y, idx));  // 경계선 육지 좌표 추가
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        visitedSea = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 별 표시
        int idx = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    bfs(i, j, idx);
                    idx++;
                }
            }
        }

        // 최단 다리 짓기
        for (Node node : boundary) {
            build(node);
        }

        System.out.println(ans);
    }

    static class Node {
        int x, y, idx;

        Node(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
    }
}
import java.io.*;
import java.util.*;
public class st_포탑부수기 {
    static int N, M, K;
    static int[][] board;
    static int[] dx = {0, 1, 0, -1, -1, -1, 1, 1};		// 우, 하, 좌, 상, 대
    static int[] dy = {1, 0, -1, 0, -1, 1, 1, -1};
    static int[][] attTime;
    static HashSet<Integer> harts;
    static int broken;
    static int turn =1;

    private static void getHart(int[] parents, Top a2, Top a1) {
        harts = new HashSet<Integer>();		// 공격과 관련있는 애들
        int idx = a2.x*M+a2.y;
        board[a2.x][a2.y] -= a1.pw;				// 공격력--
        harts.add(idx);		// harts에 공격대상 포함

        while(true) {
            int pre = parents[idx];		// 공격 경로의 포탑
            if(pre==-1) break;
            int px = pre/M;
            int py = pre%M;
            board[px][py] -= a1.pw/2;		// 경로 포탑도 피해입음
            harts.add(pre);
            idx = pre;
        }
    }

    private static boolean tryRiser(Top a1, Top a2) {
        // bfs, 범위 모듈러, 최단 경로의 경로도 알기
        // 각 좌표의 인덱스: x*M+y;
        Queue<Node> que = new ArrayDeque<Node>();
        int[] visited = new int[N*M];
        que.add(new Node(a1.x, a1.y));

        // 방문체크겸 부모배열 초기화 (-2:아직 안감, -1: 부모, 나머지는 idx)
        Arrays.fill(visited, -2);
        visited[a1.x*M+a1.y] = -1;		// -1값일때 시작 지점

        boolean flag = false;
        while(!que.isEmpty()) {
            Node cur = que.poll();

            if(cur.x==a2.x && cur.y==a2.y) {
                flag = true;
                break;
            }

            for(int i=0; i<4; i++) {
                int nx = (cur.x+dx[i]+N)%N;		// 음수 처리 유
                int ny = (cur.y+dy[i]+M)%M;
                int idx = cur.x*M+cur.y;		// 현재 좌표의 인덱스
                int ndx = nx*M+ny;				// 이동할 좌표의 인덱스
                if(board[nx][ny]<=0 || visited[ndx]!=-2) continue;		// 이미 부서지거나, 방문한 곳이면 패쓰
                que.add(new Node(nx, ny));
                visited[ndx] = idx;		// 해당 배열은 부모idx로부터 온거라고 체크
            }
        }

        if(flag) {		// 공격 입히기
            getHart(visited, a2, a1);
        }return flag;
    }

    private static void tryBoom(Top a2, Top a1) {
        harts = new HashSet<Integer>();  	// 여기도 여기만의 피해자 초기화

        // 공격대상 먼저 피해 입음
        board[a2.x][a2.y] -= a1.pw;				// 공격력--
        harts.add(a2.x*M+a2.y);		// harts에 공격대상 포함

        // 8방 피해
        for(int i=0; i<8; i++) {
            int nx = (a2.x+dx[i]+N)%N;
            int ny = (a2.y+dy[i]+M)%M;
            if(nx==a1.x && ny==a1.y) continue;
            board[nx][ny] -= a1.pw/2;
            harts.add(nx*M+ny);
        }
    }

    private static boolean allBroken() {
        broken = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(board[i][j] <= 0) broken++;
            }
        }
        return broken >= N*M-1;
    }

    private static boolean play() {
        // 공격자 정하기
        PriorityQueue<Top> at1 = new PriorityQueue<Top>();
        PriorityQueue<Top> at2 = new PriorityQueue<Top>(Collections.reverseOrder());

        // 공격자 구하기
        broken = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(board[i][j] > 0) {
                    at1.add(new Top(i, j, board[i][j], attTime[i][j]));
                }else broken++;
            }
        }

        Top att1 = at1.poll();

        // 공격 대상 구하기
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(board[i][j] <= 0) continue;
                if(att1.x==i && att1.y==j) continue;
                at1.add(new Top(i, j, board[i][j], attTime[i][j]));
                at2.add(new Top(i, j, board[i][j], attTime[i][j]));
            }
        }


        if(broken >= N*M-1) return false;

        // 공격자, 공격대상 포탑 뽑기
        Top att2 = at2.poll();

        // 공격자는 최근 공격++;
        attTime[att1.x][att1.y] = turn;

        // 핸디캡 적용
        att1.setHandy();
        board[att1.x][att1.y] += (N+M);

        // 레이저 공격 시도 실패했다면 포탄 공격
        if(!tryRiser(att1, att2)) {
            tryBoom(att2, att1);
        }

        // 공격자는 다시 피해 복구
        int pw = att1.pw;
        if(board[att1.x][att1.y]!=pw) board[att1.x][att1.y]=pw;

        // 여기서 부서진 포탑 한번 체크
        if(allBroken()) return false;


        // 각 공격의 관련자 리스트에 공격자도 넣어주기
        harts.add(att1.x*M+att1.y);

        // 포탑 정비
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(board[i][j] <= 0) continue;
                if(harts.contains(i*M+j)) continue;
                board[i][j] += 1;
            }
        }

        if(allBroken()) return false;

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        attTime = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(K-- > 0) {
            turn++;
            if(!play()) break;
        }
        // 가장 강한 공격력 출력
        int mx = Integer.MIN_VALUE;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                mx = Math.max(mx, board[i][j]);
            }
        }

        System.out.println(mx);
    }

    private static class Top implements Comparable<Top> {
        @Override
        public String toString() {
            return "Top [x=" + x + ", y=" + y + ", pw=" + pw + ", rt=" + rt + "]";
        }

        int x, y, pw, rt;			// 좌표, 공격력, 최근 공격 시간 (클수록 최근)

        public Top(int x, int y, int pw, int rt) {
            this.x = x;
            this.y = y;
            this.pw = pw;
            this.rt = rt;
        }

        public void setHandy() {
            this.pw += N+M;
        }

        @Override
        public int compareTo(Top o) {
            if(this.pw == o.pw) {
                if(o.rt==this.rt) {
                    if(o.x+o.y==this.x+this.y) {
                        return o.y - this.y;
                    }return (o.x+o.y)-(this.x+this.y);
                }return o.rt-this.rt;
            }return this.pw - o.pw;
        }

    }

    private static class Node{
        @Override
        public String toString() {
            return "Node [x=" + x + ", y=" + y + "]";
        }

        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

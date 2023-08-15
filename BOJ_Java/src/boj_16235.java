import java.io.*;
import java.util.*;

class Tree implements Comparable<Tree> {
    int x, y, age;

    public Tree(int x, int y, int age) {
        this.x = x;
        this.y = y;
        this.age = age;
    }

    @Override
    public int compareTo(Tree o) {
        return this.age - o.age;
    }
}

public class boj_16235 {
    static int N, M, K;
    static int[][] A;
    static int[][] farm;
    static Deque<Tree> trees;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        farm = new int[N][N];
        trees = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                farm[i][j] = 5;
            }
        }

        ArrayList<Tree> temp = new ArrayList<Tree>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            temp.add(new Tree(x, y, z));
        }

        Collections.sort(temp);     // 초기 나무 정ㅇ
        for (Tree t : temp) {
            trees.offer(t);
        }

        while (K > 0) {
            springAndSummer();
            fall();
            winter();
            K--;
        }
        System.out.println(trees.size());
    }

    static void springAndSummer() {
        Deque<Tree> growTrees = new ArrayDeque<>();
        ArrayList<Tree> deadTrees = new ArrayList<>();

        while (!trees.isEmpty()) {
            Tree tree = trees.poll();
            if (farm[tree.x][tree.y] >= tree.age) {     // 양분 먹은 나무는 나이 업
                farm[tree.x][tree.y] -= tree.age;
                tree.age++;
                growTrees.add(tree);
            } else {
                deadTrees.add(tree);                    // 양분 못먹으면 죽음
            }
        }

        // 살아남은 나무들로 trees 업데이트
        trees = growTrees;


        // 여름
        for (Tree t : deadTrees) {
            farm[t.x][t.y] += t.age/2;
        }
    }

    static void fall() {
        Deque<Tree> breedTrees = new ArrayDeque<>();

        while (!trees.isEmpty()) {
            Tree young = trees.pollFirst();                                 // 어린 나무들 먼저 빼서 정렬 유지
            if (young.age % 5 == 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = young.x + dx[i];
                    int ny = young.y + dy[i];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                        breedTrees.offerFirst(new Tree(nx, ny, 1));     // 새로운 나무는 앞으로 추가
                    }
                }
            }
            breedTrees.offerLast(young);                                    // (이 부분때문에 어린 순) 증식하는 어린 나무 순으로 다시 넣어줘야함
        }
        trees = breedTrees;                                                 // 새로운 나무들로 trees 업데이트
    }

    static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                farm[i][j] += A[i][j];
            }
        }
    }
}


///**
// * 초기 나무의 정렬
// * - 어린 나무 부터 양분을 먹어야함으로 정렬
// * - 이 후에는 모두 나이 1씩 먹고, 새로운 나무는 나이가 1임으로 동일한 나이증가를 가짐으로 이 후 정렬은 필요 없음
// * - 덱은 정렬 못함으로, 리스트로 정렬 흐 덱에 삽입
// */
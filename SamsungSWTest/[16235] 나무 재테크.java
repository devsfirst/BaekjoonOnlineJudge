import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, K;
    static int[][] A, nutrient;
    static ArrayList<Tree>[][] trees;
    static int[] moveX = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] moveY = {-1, 0, 1, -1, 1, -1, 0 ,1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N + 1][N + 1];
        nutrient = new int[N + 1][N + 1];
        trees = new ArrayList[N + 1][N + 1];

        /**
         * A: 매년 땅에 줄 양분
         * nutrient: 땅에 남아있는 양분. 초기엔 5
         * trees: (x, y) 좌표에 있는 나무 list
         */
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                nutrient[i][j] = 5;
                trees[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            trees[x][y].add(new Tree(x, y, z));
        }

        for (int i = 1; i <= K; i++) {
            /**
             * 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
             * 각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다.
             * 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
             * 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
             */
            ArrayList<Tree> deadTrees = new ArrayList<>();
            for (int a = 1; a <= N; a++) {
                for (int b = 1; b <= N; b++) {
                    // (a, b)에 나무가 없으면 pass
                    if (trees[a][b].size() == 0) continue;
                    // (a, b)에 나무가 1개 있으면 양분 줌
                    if (trees[a][b].size() == 1) {
                        Tree tree = trees[a][b].get(0);
                        if (nutrient[a][b] < tree.z) deadTrees.add(tree);
                        else {
                            nutrient[a][b] -= tree.z;
                            tree.z++;
                        }
                    }
                    // (a, b)에 나무가 여러 개 있으면 나이가 적은 순서대로 정렬 후 양분을 줌
                    else {
                        Collections.sort(trees[a][b]);
                        for (Tree tree : trees[a][b]) {
                            if (nutrient[a][b] < tree.z) deadTrees.add(tree);
                            else {
                                nutrient[a][b] -= tree.z;
                                tree.z++;
                            }
                        }
                    }
                }
            }

            /**
             * 여름에는 봄에 죽은 나무가 양분으로 변하게 된다.
             * 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점 아래는 버린다.
             */
            for (Tree deadTree : deadTrees) {
                int x = deadTree.x;
                int y = deadTree.y;
                trees[x][y].remove(deadTree);
                nutrient[x][y] += (deadTree.z / 2);
            }

            /**
             * 가을에는 나무가 번식한다.
             * 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
             * 어떤 칸 (r, c)와 인접한 칸은 (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1), (r+1, c-1), (r+1, c), (r+1, c+1) 이다.
             * 상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.
             */
            for (int a = 1; a <= N; a++) {
                for (int b = 1; b <= N; b++) {
                    int size = trees[a][b].size();
                    // 나무가 있으면
                    if (size > 0) {
                        for (int c = 0; c < size; c++) {
                            Tree tree = trees[a][b].get(c);
                            // 각 나무의 나이가 5의 배수이면
                            if (tree.z % 5 == 0) {
                                int x = tree.x;
                                int y = tree.y;
                                for (int d = 0; d < 8; d++) {
                                    // 나이가 1인 나무 생성
                                    if (x + moveX[d] >= 1 && x + moveX[d] <= N && y + moveY[d] >= 1 && y + moveY[d] <= N) {
                                        trees[x + moveX[d]][y + moveY[d]].add(new Tree(x + moveX[d], y + moveY[d], 1));
                                    }
                                }
                            }
                        }
                    }
                }
            }

            /**
             * 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다.
             * 각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.
             */
            for (int x = 1; x <= N; x++) {
                for (int y = 1; y <= N; y++) {
                    nutrient[x][y] += A[x][y];
                }
            }
        }

        int result = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (trees[i][j].size() > 0) result += trees[i][j].size();
            }
        }
        System.out.println(result);
    }

    static class Tree implements Comparable<Tree> {
        int x, y, z;

        public Tree(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public int compareTo(Tree tree) {
            return this.z - tree.z;
        }
    }
}
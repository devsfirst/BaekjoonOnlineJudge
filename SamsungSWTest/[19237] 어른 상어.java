import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, k, time;
    static Node[][] map;
    static Shark[] sharks;
    static boolean[] out;
    static int[] r = {0, -1, 1, 0, 0};
    static int[] c = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        init();

        int curDir, nextDir, priorityDir;
        int nr = 0, nc = 0;
        boolean noSmell;
        while (true) {
            time++;
            if (time > 1000) break;
            Node[][] newMap = new Node[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                newMap[i] = Arrays.copyOf(map[i], N + 1);
            }
            // 1초마다 모든 상어가 동시에 상하좌우로 인접한 칸 중 하나로 이동하고, 자신의 냄새를 그 칸에 뿌린다.
            // 냄새는 상어가 k번 이동하고 나면 사라진다.
            for (int i = M; i >= 1; i--) {
                // 쫓겨난 상어
                if (out[i]) continue;
                // 현재 상어가 바라보는 방향
                curDir = sharks[i].dir;
                nextDir = 0;
                noSmell = true;
                for (int j = 1; j <= 4; j++) {
                    // 각 상어가 이동 방향을 결정할 때는, 먼저 인접한 칸 중 아무 냄새가 없는 칸의 방향으로 잡는다.
                    // 이때 가능한 칸이 여러 개일 수 있는데, 그 경우에는 특정한 우선순위를 따른다.
                    // 우선순위는 상어마다 다를 수 있고, 같은 상어라도 현재 상어가 보고 있는 방향에 따라 또 다를 수 있다.
                    // 상어가 맨 처음에 보고 있는 방향은 입력으로 주어지고, 그 후에는 방금 이동한 방향이 보고 있는 방향이 된다.
                    priorityDir = sharks[i].priority[curDir][j];
                    nr = sharks[i].r + r[priorityDir];
                    nc = sharks[i].c + c[priorityDir];
                    if (nr < 1 || nr > N || nc < 1 || nc > N) continue;

                    // 우선순위대로 확인 후 냄새 없는 칸 확인 시 바로 이동
                    if (map[nr][nc].sharkNum == 0) {
                        nextDir = priorityDir;
                        noSmell = false;
                        break;
                    }
                }

                // 그런 칸이 없으면 자신의 냄새가 있는 칸의 방향으로 잡는다.
                if (noSmell) {
                    for (int j = 1; j <= 4; j++) {
                        priorityDir = sharks[i].priority[curDir][j];
                        nr = sharks[i].r + r[priorityDir];
                        nc = sharks[i].c + c[priorityDir];
                        if (nr < 1 || nr > N || nc < 1 || nc > N) continue;
                        if (map[nr][nc].sharkNum == i) {
                            nextDir = priorityDir;
                            break;
                        }
                    }
                }

                sharks[i].r = nr;
                sharks[i].c = nc;
                sharks[i].dir = nextDir;

                // 모든 상어가 이동한 후 한 칸에 여러 마리의 상어가 남아 있으면, 가장 작은 번호를 가진 상어를 제외하고 모두 격자 밖으로 쫓겨난다.
                if (newMap[nr][nc].smell == k + 1) {
                    out[newMap[nr][nc].sharkNum] = true;
                }
                newMap[nr][nc] = new Node(i, k + 1);
            }

            int leftSharkNum = 0;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    // 냄새가 0이면 넘어감
                    if (newMap[i][j].smell == 0) continue;
                    newMap[i][j].smell--;
                    // 냄새를 1 줄였는데 0이 된거면 (i, j)를 빈칸으로 만듦
                    if (newMap[i][j].smell == 0) newMap[i][j] = new Node(0, 0);
                    else if (newMap[i][j].smell == k) leftSharkNum++;
                }
            }

            // 남은 상어가 1마리면 그 상어는 1번 상어일 수밖에 없음
            if (leftSharkNum == 1) break;

            map = newMap;
        }

        if (time > 1000) System.out.println(-1);
        else System.out.println(time);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        sharks = new Shark[M + 1];
        map = new Node[N + 1][N + 1];
        out = new boolean[M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int s = Integer.parseInt(st.nextToken());
                if (s == 0) {
                    map[i][j] = new Node(0, 0);
                    continue;
                }
                map[i][j] = new Node(s, k);
                sharks[s] = new Shark(i, j);
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            sharks[i].dir = Integer.parseInt(st.nextToken());
        }

        // 상어마다
        for (int i = 1; i <= M; i++) {
            // 4방향 우선순위
            for (int j = 1; j <= 4; j++) {
                st = new StringTokenizer(br.readLine());
                // 각 방향마다 4개의 우선순위 값
                for (int k = 1; k <= 4; k++) {
                    sharks[i].priority[j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }
    }

    static class Node {
        int sharkNum, smell;

        public Node(int sharkNum, int smell) {
            this.sharkNum = sharkNum;
            this.smell = smell;
        }
    }

    static class Shark {
        int r, c, dir;
        int[][] priority;

        public Shark(int r, int c) {
            this.r = r;
            this.c = c;
            this.priority = new int[5][5];
        }
    }
}
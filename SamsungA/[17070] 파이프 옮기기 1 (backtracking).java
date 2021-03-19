import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static int N;
    static int[][] map;
    static int[] head = {1, 2};
    static int[] tail = {1, 1};
    static int dir;
    static int[] moveR = {0, 1, 1};
    static int[] moveC = {1, 0, 1};
    static final int DIR_ROW = 0;
    static final int DIR_COL = 1;
    static final int DIR_DIAGONAL = 2;
    static int result;

    public static void main(String[] args) throws IOException {
        init();

        backtracking();

        System.out.println(result);
    }

    private static void backtracking() {
        if (head[0] == N && head[1] == N) {
            result++;
            return;
        }

        int[] curHead = Arrays.copyOf(head, 2);
        int[] curTail = Arrays.copyOf(tail, 2);
        int curDir = dir;
        int nextHeadR, nextHeadC;
        boolean canMoveRow = false, canMoveCol = false;
        // 현재 방향이 세로가 아니면 가로로 진행
        if (curDir != DIR_COL) {
            nextHeadR = curHead[0] + moveR[DIR_ROW];
            nextHeadC = curHead[1] + moveC[DIR_ROW];
            if (nextHeadC <= N && map[nextHeadR][nextHeadC] == 0) {
                canMoveRow = true;
                move(DIR_ROW);
                backtracking();
                recovery(curHead, curTail, curDir);
            }
        }
        // 현재 방향이 가로가 아니면 세로로 진행
        if (curDir != DIR_ROW) {
            nextHeadR = curHead[0] + moveR[DIR_COL];
            nextHeadC = curHead[1] + moveC[DIR_COL];
            if (nextHeadR <= N && map[nextHeadR][nextHeadC] == 0) {
                canMoveCol = true;
                move(DIR_COL);
                backtracking();
                recovery(curHead, curTail, curDir);
            }
        }

        // 현재 방향에 관계 없이 대각선으로는 모두 가능
        nextHeadR = curHead[0] + moveR[DIR_DIAGONAL];
        nextHeadC = curHead[1] + moveC[DIR_DIAGONAL];
        if (canMoveRow) {
            if (canMoveCol) {
                if (map[nextHeadR][nextHeadC] == 0) {
                    move(DIR_DIAGONAL);
                    backtracking();
                    recovery(curHead, curTail, curDir);
                }
            } else {
                if (nextHeadR <= N && nextHeadC - 1 >= 1 && map[nextHeadR][nextHeadC - 1] == 0 && map[nextHeadR][nextHeadC] == 0) {
                    move(DIR_DIAGONAL);
                    backtracking();
                    recovery(curHead, curTail, curDir);
                }
            }
        } else if (canMoveCol) {
            if (nextHeadC <= N && nextHeadR - 1 >= 1 && map[nextHeadR - 1][nextHeadC] == 0 && map[nextHeadR][nextHeadC] == 0) {
                move(DIR_DIAGONAL);
                backtracking();
                recovery(curHead, curTail, curDir);
            }
        }
    }

    private static void recovery(int[] curHead, int[] curTail, int curDir) {
        head[0] = curHead[0];
        head[1] = curHead[1];
        tail[0] = curTail[0];
        tail[1] = curTail[1];
        dir = curDir;
    }

    private static void move(int direction) {
        tail[0] = head[0];
        tail[1] = head[1];
        // 가로로 이동
        if (direction != DIR_COL) {
            head[1]++;
            dir = DIR_ROW;
        }
        // 세로로 이동
        if (direction != DIR_ROW) {
            head[0]++;
            dir = DIR_COL;
        }

        if (direction == DIR_DIAGONAL) dir = DIR_DIAGONAL;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
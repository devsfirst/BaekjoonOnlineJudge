import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int H;
    static int[][] ladder;
    static boolean end = false;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new int[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            ladder[row][col] = col + 1;
            ladder[row][col + 1] = col;
        }

        for (int i = 0; i <= 3; i++) {
            if (dfs(1, 0, i)) {
                break;
            }
        }

        System.out.println(min > 3 ? -1 : min);
    }

    private static boolean dfs(int r, int ladderCount, int maxLadder) {
        if (ladderCheck()) {
            min = ladderCount;
            end = true;
            return true;
        }

        if (ladderCount == maxLadder) return false;

        for (int row = r; row <= H; row++) {
            for (int col = 1; col < N; col++) {
                // 사다리 놓을 수 있음
                if (ladder[row][col] == 0 && ladder[row][col + 1] == 0) {
                    // 사다리 놓고 dfs
                    ladder[row][col] = col + 1;
                    ladder[row][col + 1] = col;
                    dfs(row, ladderCount + 1, maxLadder);
                    // 위의 dfs 에서 사다리 놓는 방법 찾으면 더 이상 진행 X
                    if (end) return true;
                    // 사다리 회수
                    ladder[row][col] = 0;
                    ladder[row][col + 1] = 0;
                }
            }
        }

        return false;
    }

    private static boolean ladderCheck() {
        for (int ladderNum = 1; ladderNum <= N; ladderNum++) {
            int col = ladderNum;
            for (int row = 1; row <= H; row++) {
                if (ladder[row][col] != 0) {
                    col = ladder[row][col];
                }
            }
            if (col != ladderNum) return false;
        }
        return true;
    }
}
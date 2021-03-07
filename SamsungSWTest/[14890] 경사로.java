import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, L;
    static int[][] map;
    static int result;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        simulate();

        System.out.println(result);
    }

    private static void simulate() {
        boolean[][] rowVisit = new boolean[N + 1][N + 1];
        boolean[][] colVisit = new boolean[N + 1][N + 1];

        // 가로로 검사
        for (int r = 1; r <= N; r++) {
            boolean pass = true;
            int prev = map[r][1];
            for (int c = 2; c <= N; c++) {
                int cur = map[r][c];
                // 길을 지나갈 수 있으려면 길에 속한 모든 칸의 높이가 모두 같아야 한다.
                // 현재 높이가 이전 높이와 같을 경우
                if (prev == cur) {
                    prev = cur;
                    continue;
                }
                // 현재 높이가 이전 높이와 다른 경우
                if (prev < cur) {
                    // 올라가는 길에 경사로 놓을 수 있는지
                    if (!check(r, c, rowVisit, 1)) {
                        pass = false;
                        break;
                    }
                } else {
                    // 내려가는 길에 경사로 놓을 수 있는지
                    if (!check(r, c - 1, rowVisit, 2)) {
                        pass = false;
                        break;
                    }
                }
                prev = cur;
            }
            if (pass) result++;
        }

        // 세로로 검사
        for (int c = 1; c <= N; c++) {
            boolean pass = true;
            int prev = map[1][c];
            for (int r = 2; r <= N; r++) {
                int cur = map[r][c];
                // 길을 지나갈 수 있으려면 길에 속한 모든 칸의 높이가 모두 같아야 한다.
                // 현재 높이가 이전 높이와 다른 경우
                if (prev < cur) {
                    // 올라가는 길에 경사로 놓을 수 있는지
                    if (!check(r, c, colVisit, 3)) {
                        pass = false;
                        break;
                    }
                } else if (prev > cur){
                    // 내려가는 길에 경사로 놓을 수 있는지
                    if (!check(r - 1, c, colVisit, 4)) {
                        pass = false;
                        break;
                    }
                }
                prev = cur;
            }
            if (pass) result++;
        }
    }

    private static boolean check(int r, int c, boolean[][] visit, int opt) {
        int cur = map[r][c];
        int tmpR = r;
        int tmpC = c;
        int diff = 0;
        if (opt == 1) diff = cur - map[r][c - 1];
        else if (opt == 2) diff = cur - map[r][c + 1];
        else if (opt == 3) diff = cur - map[r - 1][c];
        else if (opt == 4) diff = cur - map[r + 1][c];

        if (Math.abs(diff) != 1) return false;

        for (int i = 1; i <= L; i++) {
            if (opt == 1) tmpC = c - i;
            else if (opt == 2) tmpC = c + i;
            else if (opt == 3) tmpR = r - i;
            else tmpR = r + i;
            // 경사로를 놓다가 범위를 벗어나는 경우
            if (tmpR < 1 || tmpR > N || tmpC < 1 || tmpC > N) return false;
            // 경사로를 놓은 곳에 또 경사로를 놓는 경우
            if (visit[tmpR][tmpC]) return false;
            // 낮은 칸과 높은 칸의 높이 차이는 1이어야 한다.
            // 경사로를 놓을 낮은 칸의 높이는 모두 같아야 하고, L개의 칸이 연속되어 있어야 한다.
            if (cur - map[tmpR][tmpC] != diff) return false;
        }

        tmpR = r;
        tmpC = c;
        for (int i = 1; i <= L; i++) {
            if (opt == 1) tmpC = c - i;
            else if (opt == 2) tmpC = c + i;
            else if (opt == 3) tmpR = r - i;
            else tmpR = r + i;
            visit[tmpR][tmpC] = true;
        }

        return true;
    }
}
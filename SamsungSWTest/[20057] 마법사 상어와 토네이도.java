import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[][] board;
    static int result;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        board = new int[N + 1][N + 1];
        int phase = 1;
        int[] location = {N / 2 + 1, N / 2 + 1};

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean end = false;
        while (true) {
            // 왼쪽
            for(int i = 1; i <= phase; i++) {
                simulate(location[0], location[1] - 1, 1);
                location[1]--;
                // 종료
                if (location[0] == 1 && location[1] == 1) {
                    end = true;
                    break;
                }
            }

            if (end) break;

            // 아래
            for (int i = 1; i <= phase; i++) {
                simulate(location[0] + 1, location[1], 2);
                location[0]++;
            }
            phase++;

            // 오른쪽
            for (int i = 1; i <= phase; i++) {
                simulate(location[0], location[1] + 1, 3);
                location[1]++;
            }

            // 위
            for (int i = 1; i <= phase; i++) {
                simulate(location[0] - 1, location[1], 4);
                location[0]--;
            }
            phase++;
        }

        System.out.println(result);
    }

    private static void simulate(int r, int c, int opt) {
        int sand = board[r][c];
        if (sand == 0) return;
        int s1 = (int) (sand * 0.01);
        int s2 = (int) (sand * 0.02);
        int s5 = (int) (sand * 0.05);
        int s7 = (int) (sand * 0.07);
        int s10 = (int) (sand * 0.1);
        int alpha = sand - 2 * (s1 + s2 + s7 + s10) - s5;
        board[r][c] = 0;

        if (opt == 1) {
            if (c - 1 >= 1) {
                board[r][c - 1] += alpha;
                if (r - 1 >= 1) board[r - 1][c - 1] += s10;
                else result += s10;
                if (r + 1 <= N) board[r + 1][c - 1] += s10;
                else result += s10;
                if (c - 2 >= 1) board[r][c - 2] += s5;
                else result += s5;
            } else result += (alpha + 2 * s10 + s5);

            if (r - 1 >= 1) {
                board[r - 1][c] += s7;
                if (r - 2 >= 1) board[r - 2][c] += s2;
                else result += s2;
            } else result += (s2 + s7);

            if (r + 1 <= N) {
                board[r + 1][c] += s7;
                if (r + 2 <= N) board[r + 2][c] += s2;
                else result += s2;
            } else result += (s2 + s7);

            if (c + 1 <= N) {
                if (r - 1 >= 1) board[r - 1][c + 1] += s1;
                else result += s1;
                if (r + 1 <= N) board[r + 1][c + 1] += s1;
                else result += s1;
            } else result += (2 * s1);
        } else if (opt == 2) {
            if (r + 1 <= N) {
                board[r + 1][c] += alpha;
                if (c - 1 >= 1) board[r + 1][c - 1] += s10;
                else result += s10;
                if (c + 1 <= N) board[r + 1][c + 1] += s10;
                else result += s10;
                if (r + 2 <= N) board[r + 2][c] += s5;
                else result += s5;
            } else result += (alpha + 2 * s10 + s5);

            if (c - 1 >= 1) {
                board[r][c - 1] += s7;
                if (c - 2 >= 1) board[r][c - 2] += s2;
                else result += s2;
            } else result += (s2 + s7);

            if (c + 1 <= N) {
                board[r][c + 1] += s7;
                if (c + 2 <= N) board[r][c + 2] += s2;
                else result += s2;
            } else result += (s2 + s7);

            if (r - 1 >= 1) {
                if (c - 1 >= 1) board[r - 1][c - 1] += s1;
                else result += s1;
                if (c + 1 <= N) board[r - 1][c + 1] += s1;
                else result += s1;
            } else result += (2 * s1);
        } else if (opt == 3) {
            if (c + 1 <= N) {
                board[r][c + 1] += alpha;
                if (r - 1 >= 1) board[r - 1][c + 1] += s10;
                else result += s10;
                if (r + 1 <= N) board[r + 1][c + 1] += s10;
                else result += s10;
                if (c + 2 <= N) board[r][c + 2] += s5;
                else result += s5;
            } else result += (alpha + 2 * s10 + s5);

            if (r - 1 >= 1) {
                board[r - 1][c] += s7;
                if (r - 2 >= 1) board[r - 2][c] += s2;
                else result += s2;
            } else result += (s2 + s7);

            if (r + 1 <= N) {
                board[r + 1][c] += s7;
                if (r + 2 <= N) board[r + 2][c] += s2;
                else result += s2;
            } else result += (s2 + s7);

            if (c - 1 >= 1) {
                if (r - 1 >= 1) board[r - 1][c - 1] += s1;
                else result += s1;
                if (r + 1 <= N) board[r + 1][c - 1] += s1;
                else result += s1;
            } else result += (2 * s1);
        } else {
            if (r - 1 >= 1) {
                board[r - 1][c] += alpha;
                if (c - 1 >= 1) board[r - 1][c - 1] += s10;
                else result += s10;
                if (c + 1 <= N) board[r - 1][c + 1] += s10;
                else result += s10;
                if (r - 2 >= 1) board[r - 2][c] += s5;
                else result += s5;
            } else result += (alpha + 2 * s10 + s5);

            if (c - 1 >= 1) {
                board[r][c - 1] += s7;
                if (c - 2 >= 1) board[r][c - 2] += s2;
                else result += s2;
            } else result += (s2 + s7);

            if (c + 1 <= N) {
                board[r][c + 1] += s7;
                if (c + 2 <= N) board[r][c + 2] += s2;
                else result += s2;
            } else result += (s2 + s7);

            if (r + 1 <= N) {
                if (c - 1 >= 1) board[r + 1][c - 1] += s1;
                else result += s1;
                if (c + 1 <= N) board[r + 1][c + 1] += s1;
                else result += s1;
            } else result += (2 * s1);
        }
    }
}
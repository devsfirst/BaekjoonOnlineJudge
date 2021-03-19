import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N + 1][N + 1];
        int[][][] dp = new int[3][N + 1][N + 1];

        // dp[0][r][c]: (r, c)에서 가로 방향인 경우
        // dp[1][r][c]: (r, c)에서 세로 방향인 경우
        // dp[2][r][c]: (r, c)에서 대각선 방향인 경우
        dp[0][1][2] = 1;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] == 1) continue;
                // (i, j) 의 방향이 가로이고 이전에 가로방향에서 온 경우
                if (j - 2 >= 1 && map[i][j - 1] == 0 && map[i][j - 2] == 0) dp[0][i][j] += dp[0][i][j - 1];
                // (i, j) 의 방향이 가로이고 이전에 대각선방향에서 온 경우
                if (j - 2 >= 1 && i - 1 >= 1 && map[i][j - 1] == 0 && map[i - 1][j - 2] == 0) dp[0][i][j] += dp[2][i][j - 1];

                // (i, j) 의 방향이 세로이고 이전에 세로방향에서 온 경우
                if (i - 2 >= 1 && map[i - 1][j] == 0 && map[i - 2][j] == 0) dp[1][i][j] =+ dp[1][i - 1][j];
                // (i, j) 의 방향이 세로이고 이전에 대각선방향에서 온 경우
                if (i - 2 >= 1 && j - 1 >= 1 && map[i - 1][j] == 0 && map[i - 2][j - 1] == 0) dp[1][i][j] += dp[2][i - 1][j];

                // (i, j) 의 방향이 대각선이고 이전에 가로방향에서 온 경우
                if (i - 1 >= 1 && j - 2 >= 1 && map[i - 1][j - 2] == 0 && map[i - 1][j - 1] == 0 && map[i - 1][j] == 0 && map[i][j - 1] == 0) dp[2][i][j] += dp[0][i - 1][j - 1];
                // (i, j) 의 방향이 대각선이고 이전에 세로방향에서 온 경우
                if (i - 2 >= 1 && j - 1 >= 1 && map[i - 1][j - 1] == 0 && map[i - 2][j - 1] == 0 && map[i - 1][j] == 0 && map[i][j - 1] == 0) dp[2][i][j] += dp[1][i - 1][j - 1];
                // (i, j) 의 방향이 대각선이고 이전에 대각선방향에서 온 경우
                if (i - 2 >= 1 && j - 2 >= 2 && map[i - 1][j - 1] == 0 && map[i - 2][j - 2] == 0 && map[i - 1][j] == 0 && map[i][j - 1] == 0) dp[2][i][j] += dp[2][i - 1][j - 1];
            }
        }

        System.out.println(dp[0][N][N] + dp[1][N][N] + dp[2][N][N]);
    }
}
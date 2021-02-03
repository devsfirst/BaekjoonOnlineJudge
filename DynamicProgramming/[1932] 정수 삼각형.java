import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[][] triangle = new int[n + 1][n + 1];
        int[][] dp = new int[n + 1][n + 1];

        triangle[1][1] = Integer.parseInt(br.readLine());
        dp[1][1] = triangle[1][1];

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i + 1; j++) {
                triangle[i + 1][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                // 왼쪽 끝
                if (j == 1) dp[i][j] = dp[i - 1][1] + triangle[i][j];
                // 오른쪽 끝
                else if (j == i) dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                // 왼쪽 끝과 오른쪽 끝의 사이
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + triangle[i][j];
            }
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            result = Math.max(result, dp[n][i]);
        }

        System.out.println(result);
    }
}
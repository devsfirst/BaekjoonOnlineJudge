import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];

        if (n == 1) {
            System.out.println(1);
            return;
        }

        dp[1] = 1;
        dp[2] = 3;
        for (int i = 3; i <= n; i++) {
            // dp[i - 1]     : 2x1 타일을 붙이는 경우
            // dp[i - 2] * 2 : 1x2, 2x2 타일을 붙이는 경우
            dp[i] = (dp[i - 1] + (dp[i - 2] * 2) % 10007) % 10007;
        }

        System.out.println(dp[n]);
    }
}
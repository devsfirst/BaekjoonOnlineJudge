import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] wine = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        if (n == 1) {
            System.out.println(wine[n]);
            return;
        }

        dp[1] = wine[1];
        dp[2] = wine[1] + wine[2];
        for (int i = 3; i <= n; i++) {
            // i번째 와인이 연속 첫번째 와인 : dp[i - 2] + wine[i] (i - 1번째를 건너뜀)
            // i번째 와인이 연속 두번째 와인 : dp[i - 3] + wine[i - 1] + wine[i] (i - 1번째 와인을 마시고, i - 1번째 와인을 마시기 위해 dp[i - 3])
            // i번째 와인이 연속 세번째 와인 : dp[i - 1]
            dp[i] = Math.max(Math.max(dp[i - 2], dp[i - 3] + wine[i - 1]) + wine[i], dp[i - 1]);
        }

        System.out.println(dp[n]);
    }
}
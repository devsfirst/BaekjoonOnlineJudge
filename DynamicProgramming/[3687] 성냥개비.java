import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        // 가장 작은 수를 저장하는 dp
        long[] dp = new long[101];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 0;
        dp[7] = 8;
        // 가장 작은 수 찾기
        // dp[i] = dp[i - j] + dp[j] (2 <= j <= 7) 중 최솟값
        for (int i = 8; i < 101; i++) {
            for (int j = 2; j <= 7; j++) {
                String s = String.valueOf(dp[i - j]) + dp[j];
                // 첫 자리에 6개를 사용해 0으로 만들어야 할 땐 0 대신 6으로 치환
                if (s.charAt(0) == '0') s = 6 + s.substring(1);
                dp[i] = Math.min(dp[i], Long.parseLong(s));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            // n개 성냥개비로 가장 작은 수
            // n이 6일 땐 0이 가장 앞에 올 수 없으므로 6을 사용
            if (n == 6) sb.append(6).append(" ");
            else sb.append(dp[n]).append(" ");

            // n개 성냥개비로 가장 큰 수
            // 홀수일 땐 7을 하나 추가 후 남은 개수만큼 1 추가
            if (n % 2 == 1) {
                sb.append(7);
                n -= 3;
            }
            while (n > 0) {
                sb.append(1);
                n -= 2;
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}

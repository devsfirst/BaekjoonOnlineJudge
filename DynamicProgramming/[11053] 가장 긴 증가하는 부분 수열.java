import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N + 1];
        int[] dp = new int[N + 1];
        int max = 1;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            int tmpMax = 1;
            for (int j = 1; j <= i; j++) {
                if (A[j] < A[i]) {
                    tmpMax = Math.max(tmpMax, dp[j] + 1);
                }
            }
            dp[i] = tmpMax;
            max = Math.max(max, tmpMax);
        }

        System.out.println(max);
    }
}
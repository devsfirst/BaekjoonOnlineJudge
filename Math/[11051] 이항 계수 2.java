import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] num = new int[N + 1][N + 1];

        // 동적 계획법을 이용한 이항계수
        for (int i = 0; i <= N; i++) {
            num[i][0] = num[i][i] = 1;
            for (int j = 1; j < i; j++) {
                num[i][j] = (num[i - 1][j] + num[i - 1][j - 1]) % 10007;
            }
        }

        System.out.println(num[N][K]);
    }
}
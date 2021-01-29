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
        int[] num = new int[N / 2 + 1];
        int tmp = N;

        num[0] = 1;
        for (int i = 1; i <= N / 2; i++) {
            num[i] = num[i - 1] * tmp / i ;
            tmp--;
        }

        System.out.println(num[Math.min(N - K, K)]);
    }
}
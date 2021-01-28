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
        boolean[] num = new boolean[1001];
        int remove = 0;

        for (int i = 2; i <= N; i++) {
            for (int j = 1; i * j <= N; j++) {
                if (!num[i * j]) {
                    num[i * j] = true;
                    remove++;
                }
                if (remove == K) {
                    System.out.println(i * j);
                    break;
                }
            }
            if (remove == K) break;
        }
    }
}
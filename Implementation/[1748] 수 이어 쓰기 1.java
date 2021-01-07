import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int len = 1;
        int mul = 1;
        int res = 0;
        int tmp;

        while (N > 0) {
            tmp = 9 * mul;
            if (N > tmp) {
                N -= tmp;
                res += tmp * len;
                mul *= 10;
                len++;
                continue;
            }
            res += N * len;
            break;
        }
        System.out.println(res);
    }
}
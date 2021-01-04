import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int test;

        for (int i = 0; i < T; i++) {
            test = Integer.parseInt(br.readLine());
            System.out.println(recursive(0, test));
        }
    }

    public static int recursive(int sum, int end) {
        if (sum == end) return 1;
        else if (sum< end) {
            return recursive(sum + 1, end) + recursive(sum + 2, end) + recursive(sum + 3, end);
        }
        return 0;
    }
}
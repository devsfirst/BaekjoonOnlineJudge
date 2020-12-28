import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        sol(N);
    }

    public static void sol(int N) {
        long a = 1, b = 1, c;

        for (int i = 0; i < N - 1; i++) {
            c = a + b;
            a = b;
            b = c;
        }

        System.out.println(2 * a + 2 * b);
    }
}
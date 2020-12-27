import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = 0;

        try {
            K = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        sol(K);
    }

    public static void sol(int K) {
        int a = 0, b = 1, c = 0;

        for (int i = 0; i < K - 1; i++) {
            c = a + b;
            a = b;
            b = c;
        }

        System.out.println(a + " " + b);
    }
}
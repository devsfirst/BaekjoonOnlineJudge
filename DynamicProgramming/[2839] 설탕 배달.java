import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = 0;

        try {
            n = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(sol(n));
    }

    public static int sol(int n) {
        int cnt;
        int min = 100000000;
        final int UNABLE = min;
        int quotient;
        int remainder;

        quotient = n / 5;
        for (int i = quotient; i >= 0; i--) {
            cnt = 0;
            remainder = n - (5 * i);
            if (remainder % 3 == 0) {
                cnt += remainder / 3;
                cnt += i;
                if (cnt < min) {
                    min = cnt;
                }
            }
        }

        if (min != UNABLE) {
            return min;
        }
        return -1;
    }
}
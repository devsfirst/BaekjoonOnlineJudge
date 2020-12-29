import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        sol(N, m);
    }

    public static void sol(int N, int m) {
        int btnAll = N;
        int btnEven = N / 2;
        int btnOdd = N % 2 == 0 ? N / 2 : N / 2 + 1;
        int btn3k = (N - 1) / 3 + 1;
        int num = 1;

        if (btnAll <= m) num++;

        if (N >= 2) {
            if (btnEven <= m) num++;
            if (btnOdd <= m) num++;
        }
        
        if (N >= 3) {
            if (btn3k <= m) num++;
            if (btnAll + btn3k <= m) num++;
            if (btnEven + btn3k <= m) num++;
            if (btnOdd + btn3k <= m) num++;
        }

        System.out.println(num);
    }
}
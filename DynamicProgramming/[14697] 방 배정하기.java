import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line;
        int A, B, C, N;

        line = br.readLine().split(" ");

        A = Integer.parseInt(line[0]);
        B = Integer.parseInt(line[1]);
        C = Integer.parseInt(line[2]);
        N = Integer.parseInt(line[3]);

        if (sol(A, B, C, N)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static boolean sol(int A, int B, int C, int N) {
        int quotientA, quotientB, quotientC;
        int mulA, mulB, mulC;
        int tmpA, tmpB;

        quotientA = N / A;
        for(int i = quotientA; i >= 0; i--) {
            mulA = (A * i);
            if (i != 0 && N % mulA == 0) return true;
            tmpA = N - mulA;
            quotientB = tmpA / B;

            for (int j = quotientB; j >= 0; j--) {
                mulB = (B * j);
                if (j != 0 && (N - mulA) % mulB == 0) return true;
                tmpB = tmpA - mulB;
                quotientC = tmpB / C;

                for (int k = quotientC; k >= 0; k--) {
                    mulC = (C * k);
                    if (k != 0 && tmpB % mulC == 0) return true;
                }
            }
        }
        return false;
    }
}

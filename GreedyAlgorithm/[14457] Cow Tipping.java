import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] cows = new int[N * N];
        int row, col, num = 0;
        String line;

        for (int i = 0; i < N; i++) {
            line = br.readLine();
            for (int j = 0; j < N; j++) {
                cows[N * i + j] = line.charAt(j) - '0';
            }
        }

        for (int i = N * N - 1; i >= 0; i--) {
            if (cows[i] == 1) {
                num++;
                row = i / N;
                col = i % N;
                for (int r = 0; r <= row; r++) {
                    for (int c = 0; c <= col; c++) {
                        cows[N * r + c] = cows[N * r + c] == 0 ? 1 : 0;
                    }
                }
            }
        }

        System.out.println(num);
    }
}
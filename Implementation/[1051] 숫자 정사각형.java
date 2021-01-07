import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);
        int[][] rec = new int[N][M];
        int size = Math.min(N, M);

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                rec[i][j] = line.charAt(j) - '0';
            }
        }

        size = maxRecSize(rec, N, M, size);

        System.out.println(size * size);

        br.close();
    }

    public static int maxRecSize(int[][] rec, int N, int M, int size) {
        for (int i = size; i > 1; i--) {
            int dist = i - 1;
            for (int row = 0; row + dist < N; row++) {
                for (int col = 0; col + dist < M; col++) {
                    if (rec[row][col] != rec[row + dist][col]) continue;
                    if (rec[row][col] != rec[row][col + dist]) continue;
                    if (rec[row][col] != rec[row + dist][col + dist]) continue;
                    return i;
                }
            }
        }
        return 1;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        int N, num, tmpX, tmpY;
        boolean[][] block = new boolean[1001][1001];

        for (int i = 0; i < C; i++) {
            N = Integer.parseInt(br.readLine());
            num = 0;
            for (int x = 2; x <= N; x++) {
                for (int y = 1; y < x; y++) {
                    if (!block[x][y]) {
                        num++;
                        tmpX = x;
                        tmpY = y;
                        while (tmpX <= N) {
                            block[tmpX][tmpY] = true;
                            tmpX += x;
                            tmpY += y;
                        }
                    }
                }
            }
            for (boolean[] blocks : block) {
                Arrays.fill(blocks, false);
            }
            System.out.println(2 * num + 3);
        }
    }
}
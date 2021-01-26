import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] num = new int[4][N];
        final int SUM_SIZE = N * N;
        long[] sumA = new long[SUM_SIZE];
        long[] sumB = new long[SUM_SIZE];
        int indexA = 0;
        int indexB = 0;
        long result = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                num[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            long tmp1 = num[0][i];
            long tmp2 = num[2][i];
            for (int j = 0; j < N; j++) {
                sumA[indexA++] = tmp1 + num[1][j];
                sumB[indexB++] = tmp2 + num[3][j];
            }
        }

        Arrays.sort(sumA);
        Arrays.sort(sumB);

        indexA = 0;
        indexB = sumB.length - 1;
        while (indexA < SUM_SIZE && indexB >= 0) {
            long a = sumA[indexA];
            long b = sumB[indexB];
            if (a + b == 0) {
                long tmpA = 0, tmpB = 0;
                while (indexA < SUM_SIZE && sumA[indexA] == a) {
                    indexA++;
                    tmpA++;
                }
                while (indexB >= 0 && sumB[indexB] == b) {
                    indexB--;
                    tmpB++;
                }
                result += tmpA * tmpB;
            } else if (a + b < 0) {
                indexA++;
            } else {
                indexB--;
            }
        }

        System.out.println(result);
    }
}
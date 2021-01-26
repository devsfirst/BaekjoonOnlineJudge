import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[][] minDp = new int[N][3];
        int[][] maxDp = new int[N][3];

        st = new StringTokenizer(br.readLine());
        int num1 = Integer.parseInt(st.nextToken());
        int num2 = Integer.parseInt(st.nextToken());
        int num3 = Integer.parseInt(st.nextToken());
        minDp[0][0] = maxDp[0][0] = num1;
        minDp[0][1] = maxDp[0][1] = num2;
        minDp[0][2] = maxDp[0][2] = num3;

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (j == 0) {
                    minDp[i][j] = Math.min(num + minDp[i - 1][0], num + minDp[i - 1][1]);
                    maxDp[i][j] = Math.max(num + maxDp[i - 1][0], num + maxDp[i - 1][1]);
                } else if (j == 1) {
                    minDp[i][j] = Math.min(num + minDp[i - 1][0], Math.min(num + minDp[i - 1][1], num + minDp[i - 1][2]));
                    maxDp[i][j] = Math.max(num + maxDp[i - 1][0], Math.max(num + maxDp[i - 1][1], num + maxDp[i - 1][2]));
                } else {
                    minDp[i][j] = Math.min(num + minDp[i - 1][1], num + minDp[i - 1][2]);
                    maxDp[i][j] = Math.max(num + maxDp[i - 1][1], num + maxDp[i - 1][2]);
                }
            }
        }

        int min = Math.min(minDp[N - 1][0], Math.min(minDp[N - 1][1], minDp[N - 1][2]));
        int max = Math.max(maxDp[N - 1][0], Math.max(maxDp[N - 1][1], maxDp[N - 1][2]));

        System.out.println(max + " " + min);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static int MAX = Integer.MIN_VALUE;
    private static final int[] count = new int[10001];
    private static int[] num;
    private static int numIndex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            MAX = Math.max(MAX, n);
            count[n]++;
        }

        num = new int[M + 1];

        backtracking(0);
    }

    private static void backtracking(int length) {
        if (length == M) {
            for (int i = 1; i <= M; i++) {
                System.out.print(num[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= MAX; i++) {
            if (count[i] != 0) {
                count[i]--;
                num[++numIndex] = i;
                backtracking(length + 1);
                count[i]++;
                num[numIndex--] = 0;
            }
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static StringBuilder sb = new StringBuilder();
    private static int N;
    private static int M;
    private static int max;
    private static int[] num;
    private static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        num = new int[N];
        count = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(num);
        max = num[N - 1];

        backtracking(0, 0);

        System.out.println(sb.toString());
    }

    private static void backtracking(int length, int index) {
        if (length == M) {
            for (int i = 0; i < N; i++) {
                if (count[i] > 0) {
                    sb.append(num[i]).append(" ");
                }
            }
            sb.append("\n");
            return;
        }

        boolean[] visit = new boolean[max + 1];

        for (int i = index; i < N; i++) {
            if (!visit[num[i]]) {
                visit[num[i]] = true;
                count[i]++;
                backtracking(length + 1, i + 1);
                count[i]--;
            }
        }
    }
}
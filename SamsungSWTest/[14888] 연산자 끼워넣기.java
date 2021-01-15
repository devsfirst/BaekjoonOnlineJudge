import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N;
    private static int[] num;
    private static int[] op = new int[4];
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        num = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        bfs(op[0], op[1], op[2], op[3], 0, num[0]);

        System.out.println(max);
        System.out.println(min);
    }

    private static void bfs(int plus, int minus, int mul, int div, int index, int result) {
        if (index == N - 1) {
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        if (plus != 0) bfs(plus - 1, minus, mul, div, index + 1, result + num[index + 1]);
        if (minus != 0) bfs(plus, minus - 1, mul, div, index + 1, result - num[index + 1]);
        if (mul != 0) bfs(plus, minus, mul - 1, div, index + 1, result * num[index + 1]);
        if (div != 0) bfs(plus, minus, mul, div - 1, index + 1, result / num[index + 1]);
    }
}

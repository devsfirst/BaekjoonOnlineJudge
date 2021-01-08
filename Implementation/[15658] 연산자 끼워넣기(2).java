import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        String line = br.readLine();
        N = Integer.parseInt(line);
        arr = new int[N];

        String[] split = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }

        split = br.readLine().split(" ");
        int plus = Integer.parseInt(split[0]);
        int minus = Integer.parseInt(split[1]);
        int mul = Integer.parseInt(split[2]);
        int div = Integer.parseInt(split[3]);
        dfs(1, arr[0], plus, minus, mul, div);

        System.out.println(max);
        System.out.println(min);

        br.close();
    }

    private static void dfs(int index, int sum, int plus, int minus, int mul, int div) {
        if (index == N) {
            if (sum > max) max = sum;
            if (sum < min) min = sum;
        }
        else {
            int num = arr[index];
            if (plus > 0) dfs(index + 1, sum + num, plus - 1, minus, mul, div);
            if (minus > 0) dfs(index + 1, sum - num, plus, minus - 1, mul, div);
            if (mul > 0) dfs(index + 1, sum * num, plus, minus, mul - 1, div);
            if (div > 0) dfs(index + 1, sum / num, plus, minus, mul, div - 1);
        }
    }
}
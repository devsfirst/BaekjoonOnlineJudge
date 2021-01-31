import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int K;
    private static int[] num = new int[8];
    private static boolean[][] visit = new boolean[1000001][11];
    private static int length;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        String s = "" + N;
        length = s.length();
        for (int i = 1; i <= length; i++) {
            num[i] = s.charAt(i - 1) - '0';
        }

        // 한 자리 수면 -1
        if (length <= 1) {
            System.out.println(-1);
        } else {
            backtracking(0);
            if (max == Integer.MIN_VALUE) System.out.println(-1);
            else System.out.println(max);
        }
    }

    private static void backtracking(int swap) {
        if (swap == K) {
            max = Math.max(max, toInt());
            return;
        }

        // swap만큼 이미 교환했을 때 이미 같은 수가 나온 경우면 진행X
        if (visit[toInt()][swap]) {
            return;
        }
        visit[toInt()][swap] = true;

        for (int i = 1; i <= length - 1; i++) {
            for (int j = i + 1; j <= length; j++) {
                // 맨 앞이 0이 안되도록
                if (i == 1 && num[j] == 0) continue;
                int tmp = num[i];
                num[i] = num[j];
                num[j] = tmp;
                backtracking(swap + 1);
                num[j] = num[i];
                num[i] = tmp;
            }
        }
    }

    private static int toInt() {
        int ret = 0;
        int mul = 1;
        for (int i = length; i >= 1; i--) {
            ret += num[i] * mul;
            mul *= 10;
        }
        return ret;
    }
}
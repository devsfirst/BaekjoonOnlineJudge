import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] input = new int[n + 1];
        int max = Integer.MIN_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            // max(현재값, 이전까지 가장 큰 연속합 + 현재값)
            int num = Integer.parseInt(st.nextToken());
            input[i] = Math.max(num, num + input[i - 1]);
            // 연속합 중 최대값
            max = Math.max(max, input[i]);
        }
        System.out.println(max);
    }
}
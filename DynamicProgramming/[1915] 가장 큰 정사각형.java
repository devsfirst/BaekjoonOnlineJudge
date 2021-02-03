import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] table = new int[n + 1][m + 1];
        int result = 0;

        for (int i = 1; i <= n; i++) {
            String s = br.readLine();
            for (int j = 1; j <= m; j++) {
                // 현재 위치에서 왼쪽 위, 위, 왼쪽을 확인한 후
                // 가장 작은 정사각형의 변의 길이 + 현재 위치 값
                // -> 현재 위치를 포함했을 때 정사각형이 만들어지는지
                int num = s.charAt(j - 1) - '0';
                if (num == 1) {
                    table[i][j] = Math.min(table[i - 1][j - 1],
                            Math.min(table[i - 1][j], table[i][j - 1]))
                            + num;
                    result = Math.max(result, table[i][j]);
                } else {
                    table[i][j] = num;
                }
            }
        }

        System.out.println(result * result);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();
        dfs(N, 0);
        System.out.println(min + " " + max);
    }

    private static void dfs(String N, int odd) {
        int len = N.length();
        //수의 각 자리 숫자 중에서 홀수의 개수를 종이에 적는다.
        for (int i = 0; i < len; i++) {
            if (N.charAt(i) % 2 == 1) odd++;
        }
        //수가 한 자리이면 더 이상 아무것도 하지 못하고 종료한다.
        if (len == 1) {
            max = Math.max(max, odd);
            min = Math.min(min, odd);
            return;
        }
        //수가 두 자리이면 2개로 나눠서 합을 구하여 새로운 수로 생각한다.
        if (len == 2) {
            int tmp = N.charAt(0) - '0' + N.charAt(1) - '0';
            String s = "" + tmp;
            dfs(s, odd);
        } else {
            //수가 세 자리 이상이면 임의의 위치에서 끊어서 3개의 수로 분할하고, 3개를 더한 값을 새로운 수로 생각한다.
            for (int i = 1; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    int tmp = Integer.parseInt(N.substring(0, i)) +
                              Integer.parseInt(N.substring(i, j)) +
                              Integer.parseInt(N.substring(j));
                    String s = "" + tmp;
                    dfs(s, odd);
                }
            }
        }
    }
}
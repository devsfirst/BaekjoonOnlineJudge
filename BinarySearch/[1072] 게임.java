import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long X = Integer.parseInt(st.nextToken());
        long Y = Integer.parseInt(st.nextToken());
        long rate = 100 * Y / X;
        int start = 0, end = 1000000000;
        int minWin = Integer.MAX_VALUE;

        while (start <= end) {
            int mid = (start + end) / 2;
            long cmpRate = 100 * (Y + mid) / (X + mid);

            if (cmpRate == rate) {
                start = mid + 1;
            } else if (cmpRate > rate) {
                minWin = Math.min(minWin, mid);
                end = mid - 1;
            }
        }

        if (minWin == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(minWin);
    }
}
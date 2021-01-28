import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String s = br.readLine().substring(2);
            StringBuilder sb = new StringBuilder();
            int len;
            int noLoopLen = 0;
            int loopStart = 0;
            long n, d, gcd;
            boolean loop = false;

            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (c != '(' && c != ')') {
                    sb.append(c);
                } else if (c == '(') {
                    loopStart = j;
                    loop = true;
                }
                if (!loop) {
                    noLoopLen++;
                }
            }

            len = sb.length();
            if (loop) {
                d = (long) (Math.pow(10, len) - Math.pow(10, noLoopLen));
                if (loopStart == 0) {
                    n = Long.parseLong(sb.toString());
                } else {
                    n = Long.parseLong(sb.toString()) - Long.parseLong(sb.substring(0, loopStart));
                }
            } else {
                d = (long) Math.pow(10, len);
                n = Long.parseLong(sb.toString());
            }

            gcd = gcd(d, n);
            n /= gcd;
            d /= gcd;

            System.out.println(n + "/" + d);
        }
    }

    private static long gcd(long a, long b) {
        while (a % b != 0) {
            long r = a % b;
            a = b;
            b = r;
        }
        return b;
    }
}
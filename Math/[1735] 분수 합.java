import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // a / b 형태
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        // c / d 형태
        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int gcd = gcd(Math.max(b, d), Math.min(b, d));

        // 공통분모
        int commonDenominator = b * d / gcd;
        a *= (commonDenominator / b);
        c *= (commonDenominator / d);

        // a/b와 c/d를 더해서 a/b 형태로 만듦
        a = a + c;
        b = commonDenominator;

        // a/b 형태를 기약분수로 만듦
        gcd = gcd(a, b);
        a /= gcd;
        b /= gcd;

        System.out.println(a + " " + b);
    }

    private static int gcd(int numerator, int denominator) {
        int remain;
        while (true) {
            remain = numerator % denominator;
            if (remain == 0) break;
            numerator = denominator;
            denominator = remain;
        }
        return denominator;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = br.readLine()) != null) {
            int n = Integer.parseInt(s);
            int tmp = 1;
            int cnt = 1;
            while (tmp % n != 0) {
                tmp %= n;
                tmp = tmp * 10 + 1;
                cnt++;
            }
            System.out.println(cnt);
        }
    }
}
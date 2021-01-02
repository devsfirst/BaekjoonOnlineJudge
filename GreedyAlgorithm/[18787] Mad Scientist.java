import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String A = br.readLine();
        String B = br.readLine();
        int num = 0;

        for (int i = 0; i < N; i++) {
            if (A.charAt(i) != B.charAt(i)) {
                num++;
                do {
                    i++;
                    if (i >= N) break;
                } while (A.charAt(i) != B.charAt(i));
            }
        }

        System.out.println(num);
    }
}
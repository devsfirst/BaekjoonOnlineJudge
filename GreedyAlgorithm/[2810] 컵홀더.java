import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();
        String seat = br.readLine();
        int L = 0;

        for (int i = 0; i < seat.length() - 1; i++) {
            if (seat.charAt(i) == 'L') {
                i++;
                L++;
            }
        }

        if (L == 0) System.out.println(Integer.parseInt(N) - L);
        else System.out.println(Integer.parseInt(N) - L + 1);
    }
}
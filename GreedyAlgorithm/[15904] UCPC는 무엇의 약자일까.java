import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        char[] UCPC = {'U', 'C', 'P', 'C'};
        int len = line.length();
        int index = 0;

        for (int i = 0; i < len; i++) {
            if (line.charAt(i) == UCPC[index]) {
                if (++index == 4) break;
            }
        }

        if (index == 4) System.out.println("I love UCPC");
        else System.out.println("I hate UCPC");
    }
}
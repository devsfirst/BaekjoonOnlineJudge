import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int[] num = new int[s.length() + 1];
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            num[i + 1] = s.charAt(i) - '0';
            sum += num[i + 1];
        }

        if (s.length() < 2) System.out.println(-1);
        else if (sum % 3 != 0) System.out.println(-1);
        else {
            Arrays.sort(num);
            if (num[1] != 0) System.out.println(-1);
            else {
                for (int i = s.length(); i >= 1; i--) {
                    System.out.print(num[i]);
                }
                System.out.println();
            }
        }
    }
}
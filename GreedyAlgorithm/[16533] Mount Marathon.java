import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] cards = br.readLine().split(" ");
        int num = 0, max, next;

        for (int i = N - 1; i >= 0; i--) {
            num++;
            if (i == 0) break;
            max = Integer.parseInt(cards[i]);
            while (i > 0) {
                next = Integer.parseInt(cards[i - 1]);
                if (next >= max) {
                    max = next;
                    i--;
                } else break;
            }
        }
        System.out.println(num);
    }
}
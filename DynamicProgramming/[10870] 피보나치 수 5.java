import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = 0;
        int a = 0, b = 1, c = 1;

        try {
            n = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (n < 2) {
            c = n;
        }
        else {
            for (int i = 1; i < n; i++) {
                c = a + b;
                a = b;
                b = c;
            }
        }

        System.out.println(c);
    }
}
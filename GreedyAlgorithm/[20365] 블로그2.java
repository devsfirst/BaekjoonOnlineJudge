import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int B = 0;
        int R = 0;

        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);
            if (c == 'R') {
                R++;
                while(i + 1 < N) {
                    if (s.charAt(i + 1) == 'B') break;
                    i++;
                }
            } else {
                B++;
                while (i + 1 < N) {
                    if (s.charAt(i + 1) == 'R') break;
                    i++;
                }
            }
        }

        System.out.println(Math.min(R, B) + 1);
    }
}
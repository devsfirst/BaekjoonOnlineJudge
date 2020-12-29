import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] milks = br.readLine().split(" ");
        final int S = 0;
        final int C = 1;
        final int B = 2;
        int prevMilk = B;
        int curMilk;
        int res = 0;

        for (int i = 0; i < N; i++) {
            curMilk = Integer.parseInt(milks[i]);
            if (prevMilk == B && curMilk == S) {
                prevMilk = S;
                res++;
            } else if (prevMilk == S && curMilk == C) {
                prevMilk = C;
                res++;
            } else if (prevMilk == C && curMilk == B) {
                prevMilk = B;
                res++;
            }
        }

        System.out.println(res);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int L = Integer.parseInt(split[1]);
        int K = Integer.parseInt(split[2]);
        int score = 0;
        ArrayList<Integer> left = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            split = br.readLine().split(" ");
            if (Integer.parseInt(split[1]) <= L) {
                if (K > 0) {
                    score += 140;
                    K--;
                }
                else break;
            }
            else left.add(Integer.parseInt(split[0]));
        }

        for (int i = 0; i < K; i++) {
            if (left.get(i) <= L) score += 100;
        }

        System.out.println(score);
    }
}
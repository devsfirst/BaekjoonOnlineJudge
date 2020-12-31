import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int appleNum = Integer.parseInt(br.readLine());
        int[] apple = new int[appleNum];
        int left = 1, right = Integer.parseInt(split[1]);
        int move = 0, dist;

        for (int i = 0; i < appleNum; i++) {
            apple[i] = Integer.parseInt(br.readLine());
            if (apple[i] > right) {
                dist = apple[i] - right;
                move += dist;
                left += dist;
                right += dist;
            }
            else if (apple[i] < left) {
                dist = left - apple[i];
                move += dist;
                left -= dist;
                right -= dist;
            }
        }

        System.out.println(move);
    }
}
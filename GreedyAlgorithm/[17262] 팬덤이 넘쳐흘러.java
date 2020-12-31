import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] split = br.readLine().split(" ");
        int maxLeft = Integer.parseInt(split[0]);
        int minRight = Integer.parseInt(split[1]);
        int left, right, dist;

        for (int i = 1; i < N; i++) {
            split = br.readLine().split(" ");
            left = Integer.parseInt(split[0]);
            right = Integer.parseInt(split[1]);
            if (left > maxLeft) maxLeft = left;
            if (right < minRight) minRight = right;
        }

        dist = maxLeft - minRight;
        if (dist <= 0) System.out.println(0);
        else System.out.println(dist);
    }
}
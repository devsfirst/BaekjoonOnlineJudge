import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] height = br.readLine().split(" ");
        int curMaxHeight = 0;
        int curHeight;
        int maxNum = 0;
        int num = 0;

        for (int i = 0; i < N; i++) {
            curHeight = Integer.parseInt(height[i]);
            if (curHeight > curMaxHeight) {
                curMaxHeight = curHeight;
                num = 0;
            } else {
                num++;
                if (maxNum < num) maxNum = num;
            }
        }

        System.out.println(maxNum);
    }
}
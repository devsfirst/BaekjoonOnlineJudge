import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        LinkedList<Integer>[] gears = new LinkedList[5];
        for (int i = 1; i <= 4; i++) {
            gears[i] = new LinkedList<>();
        }

        String line;
        line = br.readLine();
        for (int i = 0; i < 8; i++) gears[1].offer(line.charAt(i) - '0');
        line = br.readLine();
        for (int i = 0; i < 8; i++) gears[2].offer(line.charAt(i) - '0');
        line = br.readLine();
        for (int i = 0; i < 8; i++) gears[3].offer(line.charAt(i) - '0');
        line = br.readLine();
        for (int i = 0; i < 8; i++) gears[4].offer(line.charAt(i) - '0');

        int K = Integer.parseInt(br.readLine());
        int[] rotation = new int[5];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            Arrays.fill(rotation, 0);
            rotation[index] = direction;

            // index 기준 왼쪽
            int leftDirection = direction;
            for (int j = index - 1; j >= 1; j--) {
                LinkedList<Integer> gear = gears[j + 1];
                // 회전 X
                if (gear.get(6) == gears[j].get(2)) break;
                else {
                    leftDirection = -1 * leftDirection;
                    rotation[j] = leftDirection;
                }
            }
            // index 기준 오른쪽
            int rightDirection = direction;
            for (int j = index + 1; j <= 4; j++) {
                LinkedList<Integer> gear = gears[j - 1];
                // 회전 X
                if (gear.get(2) == gears[j].get(6)) break;
                else {
                    rightDirection = -1 * rightDirection;
                    rotation[j] = rightDirection;
                }
            }

            for (int j = 1; j <= 4; j++) {
                // 시계
                if (rotation[j] == 1) gears[j].offerFirst(gears[j].pollLast());
                // 반시계
                else if (rotation[j] == -1) gears[j].offer(gears[j].poll());
            }
        }

        long result = 0;
        for (int i = 1; i <= 4; i++) {
            LinkedList<Integer> gear = gears[i];
            if (gear.peek() == 1) result += Math.pow(2, i - 1);
        }

        System.out.println(result);
    }
}

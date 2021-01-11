import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N;
    private static int H;
    private static int[] height;
    private static int[] obstacle;
    private static int minObstacle;
    private static int num = 2;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        height = new int[N];
        obstacle = new int[H + 1];
        minObstacle = N / 2;

        for (int i = 0; i < N; i += 2) {
            height[i] = Integer.parseInt(br.readLine());
            height[i + 1] = Integer.parseInt(br.readLine());
        }

        binarySearch(1, H);

        System.out.println(minObstacle + " " + num);
    }

    public static void binarySearch(int start, int end) {
        int mid = (start + end) / 2;
        if (start > end || mid == start || mid == end) return;

        for (int i = 0; i < N; i += 2) {
            // 석순
            if (height[i] >= mid) obstacle[mid]++;
            // 종유석
            if (height[i + 1] <= mid) obstacle[mid]++;
        }

        if (obstacle[mid] == minObstacle) {
            num++;
        } else {
            minObstacle = obstacle[mid];
            num = 1;
        }
        binarySearch(start, mid);
        binarySearch(mid + 1, end);
    }
}

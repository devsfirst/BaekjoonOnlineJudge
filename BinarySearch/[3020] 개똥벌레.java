import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N;
    private static int H;
    private static int[] a;
    private static int[] b;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        a = new int[N / 2];
        b = new int[N / 2];
        int minObstacle = Integer.MAX_VALUE;
        int num = 0;

        for (int i = 0; i < N / 2; i++) {
            a[i] = Integer.parseInt(br.readLine());
            b[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(a);
        Arrays.sort(b);

        int start, end;
        int mid;
        int obstacle, obstacleA, obstacleB;
        for (int height = 1; height <= H; height++) {
            obstacleA = 0;
            obstacleB = 0;
            // 석순
            start = 0;
            end = N / 2 - 1;
            while (start <= end) {
                mid = (start + end) / 2;
                if (a[mid] >= height) {
                    obstacleA = a.length - mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            // 종유석
            start = 0;
            end = N / 2 - 1;
            while (start <= end) {
                mid = (start + end) / 2;
                if (height + b[mid] > H) {
                    obstacleB = b.length - mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            obstacle = obstacleA + obstacleB;
            if (obstacle < minObstacle) {
                minObstacle = obstacle;
                num = 1;
            }
            else if (obstacle == minObstacle) num++;
        }

        System.out.println(minObstacle + " " + num);
    }
}

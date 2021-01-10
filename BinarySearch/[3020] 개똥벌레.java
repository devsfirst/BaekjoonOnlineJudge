import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        arr = new int[N];
        int min = N / 2;
        int minNum = 2;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int start = 1;
        int end = H;
        int mid;
        int obstacle;
        // 높이가 1, H일때 장애물이 중간 높이보다 적은 경우 -> 끝부터 중간으로 검사
        // 높이가 1, H일때 장애물이 중간 높이보다 많은 경우 -> 중간부터 끝으로 검사
        while (start <= end) {
            mid = (start + end) / 2;
            obstacle = 0;

            for (int i = 0; i < N; i ++) {
                if (i % 2 == 0) {
                    // 석순
                    if (arr[i] <= mid) obstacle++;
                } else {
                    // 종유석
                    if (arr[i] + mid > H) obstacle++;
                }
            }

            // 더 적은 장애물
            if (obstacle < min) {
                min = obstacle;
                minNum = 1;
            }
        }

        System.out.println(min + " " + minNum);
    }
}

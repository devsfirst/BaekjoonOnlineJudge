import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] sum = new int[N + 1];
        int min = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sum[i] += sum[i - 1] + Integer.parseInt(st.nextToken());
        }

        if (sum[N] < S) System.out.println(0);
        else {
            for (int right = 1; right <= N; right++) {
                int start = 1;
                int end = right;
                int left = 0;

                while (start <= end) {
                    int mid = (start + end) / 2;

                    if (sum[right] - sum[mid] >= S) {
                        left = mid;
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                }

                if (sum[right] - sum[left] >= S) {
                    min = Math.min(min, right - left);
                }
            }
            System.out.println(min);
        }
    }
}
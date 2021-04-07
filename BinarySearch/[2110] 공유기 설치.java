import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] x = new int[N];
        int result = 0;
        for (int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(x);

        // 공유기를 놓을 수 있는 간격으로 이분 탐색
        int left = 1;
        int right = x[N - 1] - x[0];
        while (left <= right) {
            int mid = (left + right) / 2;

            int cnt = 1; // x[0]에 공유기를 놓고 시작
            int cmp = x[0]; // 인접한 공유기
            // x[i]에 공유기를 놓았을 때 간격(mid)을 유지할 수 있는 공유기 수
            for (int i = 1; i < N; i++) {
                if (x[i] - cmp >= mid) {
                    cnt++;
                    cmp = x[i]; // 공유기를 놓고 나면 다음엔 x[i]가 인접하게 됨
                }
            }
            
            if (cnt < C) right = mid - 1;
            else {
                // C개만큼 공유기를 놓을 수 있음
                left = mid + 1;
                result = mid;
            }
        }

        System.out.println(result);
    }
}
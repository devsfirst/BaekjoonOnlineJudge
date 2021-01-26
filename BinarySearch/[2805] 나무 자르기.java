import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] trees = new int[N];
        long start = 0;
        long end = 0;
        long maxHeight = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            if (trees[i] > end) end = trees[i];
        }

        while (start <= end) {
            long mid = (start + end) / 2;
            long height = 0;

            // 나무를 mid 높이에서 자르고 남은 것을 height에 추가
            for (int i = 0; i < trees.length; i++) {
                if (trees[i] > mid) height += trees[i] - mid;
            }

            // 부족한 경우
            if (height < M) {
                end = mid - 1;
            } else {
                if (maxHeight < mid) maxHeight = mid;
                start = mid + 1;
            }
        }

        System.out.println(maxHeight);
    }
}
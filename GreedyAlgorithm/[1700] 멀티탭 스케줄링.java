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
        int K = Integer.parseInt(st.nextToken());
        int[] tools = new int[K];
        int[] tap = new int[N];
        int size = 0;
        int result = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            tools[i] = Integer.parseInt(st.nextToken());
        }

        if (K <= N) {
            System.out.println(result);
            return;
        }

        for (int i = 0; i < K; i++) {
            // i번째 전기 용품 번호
            int num = tools[i];

            // 이미 멀티탭에 같은 전기 용품이 있으면 건너뜀
            if (exist(tap, num, N)) continue;

            // 멀티탭이 다 차지 않았으면 꽂음
            if (size < N) {
                tap[size++] = num;
                continue;
            }

            // i번째 전기 용품을 꽂으려고 함
            // 멀티탭에 꽂힌 전기 용품들이 i번째 이후로 처음 등장하는 index 계산
            int[] appear = new int[N];
            Arrays.fill(appear, 9999);
            for (int j = 0; j < N; j++) {
                for (int k = i + 1; k < K; k++) {
                    if (tap[j] == tools[k]) {
                        appear[j] = k;
                        break;
                    }
                }
            }

            // 멀티탭에 꽂힌 전기 용품 중 i번째 이후로 가장 늦게 등장하는 것을 제거
            int lateIndex = 0;
            for (int j = 1; j < N; j++) {
                if (appear[j] > appear[lateIndex]) lateIndex = j;
            }
            tap[lateIndex] = num;
            result++;
        }

        System.out.println(result);
    }

    private static boolean exist(int[] tap, int num, int N) {
        for (int i = 0; i < N; i++) {
            if (tap[i] == num) return true;
        }
        return false;
    }
}
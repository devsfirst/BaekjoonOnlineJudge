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
        int[] num = new int[N];
        int diffNum = N - 1;
        int[] diff = new int[diffNum];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        // 인접합 두 수의 차이
        for (int i = 0; i < diffNum; i++) {
            diff[i] = num[i + 1] - num[i];
        }

        // 차이를 오름차순으로 정렬
        Arrays.sort(diff);
        // N - K : K개 조에 한 명씩 이미 들어가있다고 생각,
        // 키 차이가 가장 적은 아이들을 한 조로 만듦
        int set = N - K;
        int result = 0;
        for (int i = 0; i < set; i++) {
            result += diff[i];
        }

        System.out.println(result);
    }
}
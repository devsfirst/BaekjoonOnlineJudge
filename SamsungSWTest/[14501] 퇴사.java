import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N;
    private static int[] T;
    private static int[] P;
    private static int maxNum = 0;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        T = new int[N + 1];
        P = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        // 1 ~ N 일
        for (int i = 1; i <= N; i++) {
            search(i, 0);
        }

        System.out.println(maxNum);
    }

    private static void search(int day, int num) {
        // day 번째 날 상담 불가
        if (day + T[day] > N + 1) return;

        // day 번째 날 상담 금액 받음
        num += P[day];
        if (num > maxNum) maxNum = num;

        // day 번째 날 상담 후 다음 상담일 검사
        for (int i = day + T[day]; i <= N; i++) {
            search(i, num);
        }
    }
}

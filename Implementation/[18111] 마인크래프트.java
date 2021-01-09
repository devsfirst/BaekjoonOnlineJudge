import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int[][] ground;
    private static int N;
    private static int M;
    private static int B;

    public static void main(String[] args) throws IOException {
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        B = Integer.parseInt(split[2]);
        ground = new int[N][M];
        int resHeight = 0;

        int minHeight = Integer.MAX_VALUE;
        int maxHeight = Integer.MIN_VALUE;
        int tmpHeight;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                tmpHeight = Integer.parseInt(st.nextToken());
                ground[i][j] = tmpHeight;
                if (minHeight > tmpHeight) minHeight = tmpHeight;
                if (maxHeight < tmpHeight) maxHeight = tmpHeight;
            }
        }

        int remove;
        int add;
        int act;
        int tmpTime;
        int time = Integer.MAX_VALUE;

        for (int height = minHeight; height <= maxHeight; height++) {
            remove = 0;
            add = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    act = height - ground[i][j];
                    // ground가 기준 높이보다 높음 -> act 음수 -> 블록 제거
                    // ground가 기준 높이보다 낮음 -> act 양수 -> 블록 추가
                    if (act < 0) remove += -act;
                    else add += act;
                }
            }

            // 블록 제거 먼저 한 후 추가에 필요한 블록 수만큼 인벤토리에 블록이 남아있는지 확인
            // 즉, height로 땅 고르기가 가능한지 확인
            if (remove + B >= add) {
                tmpTime = remove * 2 + add;
                if (tmpTime == time) {
                    if (height > resHeight) resHeight = height;
                } else if (tmpTime < time) {
                    resHeight = height;
                    time = tmpTime;
                }
            }
        }

        System.out.println(time + " " + resHeight);

        br.close();
    }
}
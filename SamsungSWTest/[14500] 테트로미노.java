import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int M;
    static int[][] paper;
    static boolean[][] visit;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        paper = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = true;
                search(i, j, paper[i][j], 1);
                visit[i][j] = false;
            }
        }

        System.out.println(max);
    }

    private static void search(int i, int j, int sum, int length) {
        if (length == 4) {
            max = Math.max(sum, max);
            return;
        }

        // ㅜ ㅓ ㅗ ㅏ
        if (length == 3) {
            // ㅣ 모양. 위에서 아래로 내려왔을 경우
            if (i - 1 >= 0 && visit[i - 1][j]) {
                if (j - 1 >= 0 && !visit[i - 1][j - 1]) max = Math.max(max, sum + paper[i - 1][j - 1]);
                if (j + 1 < M && !visit[i - 1][j + 1]) max = Math.max(max, sum + paper[i - 1][j + 1]);
            }
            // ㅣ 모양. 아래에서 위로 올라왔을 경우
            else if (i + 1 < N && visit[i + 1][j]) {
                if (j - 1 >= 0 && !visit[i + 1][j - 1]) max = Math.max(max, sum + paper[i + 1][j - 1]);
                if (j + 1 < M && !visit[i + 1][j + 1]) max = Math.max(max, sum + paper[i + 1][j + 1]);
            }
            // ㅡ 모양. 왼쪽에서 오른쪽으로 왔을 경우
            else if (j - 1 >= 0 && visit[i][j - 1]) {
                if (i - 1 >= 0 && !visit[i - 1][j - 1]) max = Math.max(max, sum + paper[i - 1][j - 1]);
                if (i + 1 < N && !visit[i + 1][j - 1]) max = Math.max(max, sum + paper[i + 1][j - 1]);
            }
            // ㅡ 모양. 오른쪽에서 왼쪽으로 왔을 경우
            else if (j + 1 < N && visit[i][j + 1]) {
                if (i - 1 >= 0 && !visit[i - 1][j + 1]) max = Math.max(max, sum + paper[i - 1][j + 1]);
                if (i + 1 < N && !visit[i + 1][j + 1]) max = Math.max(max, sum + paper[i + 1][j + 1]);
            }
        }

        if (i - 1 >= 0 && !visit[i - 1][j]) {
            visit[i - 1][j] = true;
            search(i - 1, j, sum + paper[i - 1][j], length + 1);
            visit[i - 1][j] = false;
        }
        if (i + 1 < N && !visit[i + 1][j]) {
            visit[i + 1][j] = true;
            search(i + 1, j, sum + paper[i + 1][j], length + 1);
            visit[i + 1][j] = false;
        }
        if (j - 1 >= 0 && !visit[i][j - 1]) {
            visit[i][j - 1] = true;
            search(i, j - 1, sum + paper[i][j - 1], length + 1);
            visit[i][j - 1] = false;
        }
        if (j + 1 < M && !visit[i][j + 1]) {
            visit[i][j + 1] = true;
            search(i, j + 1, sum + paper[i][j + 1], length + 1);
            visit[i][j + 1] = false;
        }
    }
}
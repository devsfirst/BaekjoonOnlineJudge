import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int N, M;
    private static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N + 1][N + 1];
        int INF = 1000000;
        int result = 0;

        for (int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], INF);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j]);
                }
            }
        }

        // i -> j
        for (int i = 1; i <= N; i++) {
            int know = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                // i에서 j로 갈 수 있으면 know++
                if (graph[i][j] != INF) {
                    know++;
                    // i에서 j로 갈 수 없으면 j에서 i로 갈 수 있는지 확인
                } else if (graph[j][i] != INF) {
                    know++;
                    // i, j 모두 갈 수 없으면(키 순서 모름) pass
                } else {
                    break;
                }
            }
            if (know == N - 1) result++;
        }

        System.out.println(result);
    }
}
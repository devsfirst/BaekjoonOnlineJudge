import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // R번 회전
        int boundary = Math.min(N, M) / 2;
        int[] current = new int[2];
        for (int i = 0; i < R; i++) {
            int row = N - 1;
            int col = M - 1;
            // 바깥 테두리부터 회전 후 안쪽 테두리로
            for (int j = 1; j <= boundary; j++) {
                int tmp = arr[j][j];
                current[0] = j;
                current[1] = j;
                for (int k = 0; k < col; k++) {
                    arr[current[0]][current[1]] = arr[current[0]][current[1] + 1];
                    current[1]++;
                }
                for (int k = 0; k < row; k++) {
                    arr[current[0]][current[1]] = arr[current[0] + 1][current[1]];
                    current[0]++;
                }
                for (int k = 0; k < col; k++) {
                    arr[current[0]][current[1]] = arr[current[0]][current[1] - 1];
                    current[1]--;
                }
                for (int k = 0; k < row - 1; k++) {
                    arr[current[0]][current[1]] = arr[current[0] - 1][current[1]];
                    current[0]--;
                }
                arr[current[0]][current[1]] = tmp;
                row -= 2;
                col -= 2;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
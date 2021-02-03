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
        int[][] table = new int[N + 1][N + 1];

        // (i, j) 위치에서 만들 수 있는 가장 큰 직사각형에 들어있는 수의 합 저장
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                table[i][j] = table[i - 1][j] + table[i][j - 1] - table[i - 1][j - 1]
                            + Integer.parseInt(st.nextToken());
            }
        }

        // 구하려는 공간의 합 = (구하려는 공간보다 더 큰 직사각형의 수의 합 - 구하려는 공간을 뺀 나머지 부분)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            //
            System.out.println(table[x2][y2] - table[x1 - 1][y2] - table[x2][y1 - 1] + table[x1 - 1][y1 - 1]);
        }
    }
}
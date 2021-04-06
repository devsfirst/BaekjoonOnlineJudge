import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int[][] X = new int[n + 1][n + 1];
            boolean clockwise = d > 0;
            d = Math.abs(d / 45);

            for (int r = 1; r <= n; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 1; c <= n; c++) {
                    X[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            int center = (n + 1) / 2;
            for (int j = 0; j < d; j++) {
                int[][] newX = new int[n + 1][n + 1];
                if (clockwise) {
                    for (int k = 1; k <= n; k++) {
                        //X의 주 대각선을 ((1,1), (2,2), …, (n, n)) 가운데 열 ((n+1)/2 번째 열)로 옮긴다.
                        newX[k][center] = X[k][k];
                        //X의 가운데 열을 X의 부 대각선으로 ((n, 1), (n-1, 2), …, (1, n)) 옮긴다.
                        newX[k][n + 1 - k] = X[k][center];
                        //X의 부 대각선을 X의 가운데 행 ((n+1)/2번째 행)으로 옮긴다.
                        newX[center][k] = X[n + 1 - k][k];
                        //X의 가운데 행을 X의 주 대각선으로 옮긴다.
                        newX[k][k] = X[center][k];
                    }
                } else {
                    for (int k = 1; k <= n; k++) {
                        //X의 주 대각선을 ((1,1), (2,2), …, (n, n)) 가운데 행 ((n+1)/2 번째 행)으로 옮긴다.
                        newX[center][k] = X[k][k];
                        //X의 가운데 열을 X의 주 대각선으로 ((1, 1), (2, 2), …, (n, n)) 옮긴다.
                        newX[k][k] = X[k][center];
                        //X의 부 대각선을 X의 가운데 열 ((n+1)/2번째 열)로 옮긴다.
                        newX[n + 1 - k][center] = X[n + 1 - k][k];
                        //X의 가운데 행을 X의 부 대각선으로 옮긴다.
                        newX[n + 1 - k][k] = X[center][k];
                    }
                }
                //X의 다른 원소의 위치는 변하지 않는다.
                for (int r = 1; r <= n; r++) {
                    for (int c = 1; c <= n; c++) {
                        if (newX[r][c] == 0) newX[r][c] = X[r][c];
                    }
                }
                X = newX;
            }

            for (int r = 1; r <= n; r++) {
                for (int c = 1; c <= n; c++) {
                    sb.append(X[r][c]).append(" ");
                }
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}
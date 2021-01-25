import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N;
    private static int[][] queens;
    private static boolean[] visitCol;
    private static int num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        queens = new int[N][2];
        visitCol = new boolean[N];

        for (int col = 0; col < N; col++) {
            queens[0][0] = 0;
            queens[0][1] = col;
            // 방문한 Col은 다음 번에 검사 안하도록
            visitCol[col] = true;
            dfs(1);
            visitCol[col] = false;
        }

        System.out.println(num);
    }

    private static void dfs(int queenNum) {
        if (queenNum == N) {
            num++;
            return;
        }

        // queenNum은 현재 체스판에 놓인 Queen의 수를 의미
        // 서로 공격하지 않으려면 각 Row마다 1개의 Queen만 있어야 함
        // -> queenNum이 곧 다음에 놓을 Queen의 Row를 의미
        for (int col = 0; col < N; col++) {
            if (visitCol[col]) continue;
            boolean noAttack = true;
            for (int k = 0; k < queenNum; k++) {
                int[] location = queens[k];
                if (isSameDiagonal(queenNum, col, location[0], location[1])) {
                    noAttack = false;
                    break;
                }
            }
            // 공격 안하는 Col 찾음
            if (noAttack) {
                queens[queenNum][0] = queenNum;
                queens[queenNum][1] = col;
                visitCol[col] = true;
                dfs(queenNum + 1);
                visitCol[col] = false;
            }
        }
    }

    private static boolean isSameDiagonal(int row1, int col1, int row2, int col2) {
        return Math.abs(row1 - row2) == Math.abs(col1 - col2);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[] moveRow = {0, 0, 0, -1, 1};
        int[] moveCol = {0, 1, -1, 0, 0};
        int[] dice = new int[7];
        int[] diceLocation = {x, y};

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int direction = Integer.parseInt(st.nextToken());

            if (canMove(diceLocation, moveRow, moveCol, direction, N, M)) {
                move(dice, diceLocation, moveRow, moveCol, direction);

                if (map[diceLocation[0]][diceLocation[1]] == 0) map[diceLocation[0]][diceLocation[1]] = dice[6];
                else {
                    dice[6] = map[diceLocation[0]][diceLocation[1]];
                    map[diceLocation[0]][diceLocation[1]] = 0;
                }

                System.out.println(dice[1]);
            }
        }
        br.close();
    }

    private static boolean canMove(int[] diceLocation, int[] moveRow, int[] moveCol, int direction, int N, int M) {
        return diceLocation[0] + moveRow[direction] >= 0 && diceLocation[0] + moveRow[direction] <= N - 1
                && diceLocation[1] + moveCol[direction] >= 0 && diceLocation[1] + moveCol[direction] <= M - 1;
    }

    private static void move(int[] dice, int[] diceLocation, int[] moveRow, int[] moveCol, int direction) {
        int bottomNum = dice[6];
        switch (direction) {
            case 1:
                dice[6] = dice[3];
                dice[3] = dice[1];
                dice[1] = dice[4];
                dice[4] = bottomNum;
                break;
            case 2:
                dice[6] = dice[4];
                dice[4] = dice[1];
                dice[1] = dice[3];
                dice[3] = bottomNum;
                break;
            case 3:
                dice[6] = dice[2];
                dice[2] = dice[1];
                dice[1] = dice[5];
                dice[5] = bottomNum;
                break;
            case 4:
                dice[6] = dice[5];
                dice[5] = dice[1];
                dice[1] = dice[2];
                dice[2] = bottomNum;
                break;
        }
        diceLocation[0] += moveRow[direction];
        diceLocation[1] += moveCol[direction];
    }
}
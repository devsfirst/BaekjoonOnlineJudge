import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] board;
    private static int[] x = {-1, 0, 1, 1};
    private static int[] y = {1, 1, 1, 0};

    public static void main(String[] args) throws IOException {
        board = new int[20][20];

        for (int i = 1; i < 20; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 1; j < 20; j++) {
                board[i][j] = Integer.parseInt(split[j - 1]);
            }
        }

        for (int i = 1; i < 20; i++) {
            for (int j = 1; j < 20; j++) {
                if (board[i][j] == 1 || board[i][j] == 2) {
                    if (dfs(i, j, board[i][j], 1, 0) && (board[i - x[0]][j - y[0]] != board[i][j]) ||
                            dfs(i, j, board[i][j], 1, 1) && (board[i - x[1]][j - y[1]] != board[i][j])||
                            dfs(i, j, board[i][j], 1, 2) && (board[i - x[2]][j - y[2]] != board[i][j])||
                            dfs(i, j, board[i][j], 1, 3) && (board[i - x[3]][j - y[3]] != board[i][j])) {
                        System.out.println(board[i][j]);
                        System.out.println(i + " " + j);
                        return;
                    }
                }
            }
        }
        System.out.println(0);

        br.close();
    }

    private static boolean dfs(int r, int c, int piece, int continuous, int direction) {
        if (r > 19 || c > 19) {
            if (continuous == 6) return true;
            return false;
        }

        if (continuous == 6) {
            if (board[r][c] != piece) {
                return true;
            }
        }

        if (board[r][c] == piece) {
            return dfs(r + x[direction], c + y[direction], piece, continuous + 1, direction);
        }

        return false;
    }
}
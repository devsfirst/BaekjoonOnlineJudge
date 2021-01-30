import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    private static int[][] board = new int[9][9];
    private static final ArrayList<int[]> zeros = new ArrayList<>();
    private static int zeroNum;
    private static boolean find;
    private static final boolean[][] rowImpossible = new boolean[9][10];
    private static final boolean[][] colImpossible = new boolean[9][10];
    private static final boolean[][] subImpossible = new boolean[9][10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    zeros.add(new int[]{i, j});
                    zeroNum++;
                } else {
                    rowImpossible[i][board[i][j]] = true;
                    colImpossible[j][board[i][j]] = true;
                    subImpossible[i / 3 * 3 + j / 3][board[i][j]] = true;
                }
            }
        }

        backtracking(0, 0);
    }

    private static void backtracking(int zeroCount, int zerosIndex) {
        if (zeroCount == zeroNum) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
            find = true;
            return;
        }

        int[] zeroLocation = zeros.get(zerosIndex);
        int row = zeroLocation[0];
        int col = zeroLocation[1];

        for (int i = 1; i <= 9; i++) {
            if (!rowImpossible[row][i] && !colImpossible[col][i] && !subImpossible[row / 3 * 3 + col / 3][i]) {
                rowImpossible[row][i] = true;
                colImpossible[col][i] = true;
                subImpossible[row / 3 * 3 + col / 3][i] = true;
                board[row][col] = i;
                backtracking(zeroCount + 1, zerosIndex + 1);
                if (find) return;
                rowImpossible[row][i] = false;
                colImpossible[col][i] = false;
                subImpossible[row / 3 * 3 + col / 3][i] = false;
                board[row][col] = 0;
            }
        }
    }
}
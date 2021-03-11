import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int MAX_MOVE = 5;
    static int N;
    static int[] r = {0, 1, -1, 0, 0};
    static int[] c = {0, 0, 0, 1, -1};
    static int result;

    public static void main(String[] args) throws IOException {
        int[][] board = init();

        simulate(board, 0);

        System.out.println(result);
    }

    private static void simulate(int[][] board, int move) {
        if (move == MAX_MOVE) {
            result = Math.max(result, findMax(board));
            return;
        }

        // 보드를 4방향으로 이동
        for (int d = 1; d <= 4; d++) {
            // 각 방향에 대해 이동을 마친 새 보드판으로 다음 이동 진행
            simulate(moveBoard(board, d), move + 1);
        }
    }

    private static int[][] moveBoard(int[][] board, int opt) {
        int[][] newBoard = new int[N + 1][N + 1];
        // currentPos : 현재 위치
        // nextPos : 현재 위치 값과 비교할 다음 수의 위치
        // newBoardPos : 새로 만들어지는 보드에 수를 놓는 위치
        int[] currentPos, nextPos, newBoardPos;

        for (int i = 0; i < N; i++) {
            currentPos = initPos(opt);
            newBoardPos = initPos(opt);

            if (opt == 1) {
                currentPos[1] += i;
                newBoardPos[1] += i;
            }
            else if (opt == 2) {
                currentPos[1] -= i;
                newBoardPos[1] -= i;
            }
            else if (opt == 3) {
                currentPos[0] += i;
                newBoardPos[0] += i;
            }
            else{
                currentPos[0] -= i;
                newBoardPos[0] -= i;
            }

            currentPos = findCurrentPos(board, currentPos, opt);
            if (currentPos == null) continue;

            // opt == 1 : 위로 움직임 -> 위에 있는 수부터 아래로 검사
            // opt == 2 : 아래로 움직임 -> 아래에 있는 수부터 위로 검사
            // opt == 3 : 왼쪽으로 움직임 -> 왼쪽에 있는 수부터 오른쪽으로 검사
            // opt == 4 : 오른쪽으로 움직임 -> 오른쪽에 있는 수부터 왼쪽으로 검사
            while (currentPos != null) {
                nextPos = findNextPos(board, currentPos, opt);
                // 비교 할 0이 아닌 다음 수가 없음 -> 현재 위치의 수를 다음 보드에 위치시키고 종료
                if (nextPos == null) {
                    newBoard[newBoardPos[0]][newBoardPos[1]] = board[currentPos[0]][currentPos[1]];
                    break;
                }

                // 합쳐지는 수를 찾음
                if (board[currentPos[0]][currentPos[1]] == board[nextPos[0]][nextPos[1]]) {
                    newBoard[newBoardPos[0]][newBoardPos[1]] = 2 * board[currentPos[0]][currentPos[1]];
                    // 현재 위치를 nextPos 를 기준으로 다음 위치로 변경
                    currentPos = findNextPos(board, nextPos, opt);
                } else {
                    // 합쳐지는 수를 찾지 못함
                    newBoard[newBoardPos[0]][newBoardPos[1]] = board[currentPos[0]][currentPos[1]];
                    currentPos = nextPos;
                }
                newBoardPos[0] += r[opt];
                newBoardPos[1] += c[opt];
            }
        }
        return newBoard;
    }

    private static int[] findPos(int[][] board, int[] pos, int opt) {
        if (pos[0] < 1 || pos[0] > N || pos[1] < 1 || pos[1] > N) return null;
        while (board[pos[0]][pos[1]] == 0) {
            pos[0] += r[opt];
            pos[1] += c[opt];
            if (pos[0] < 1 || pos[0] > N || pos[1] < 1 || pos[1] > N) return null;
        }
        return pos;
    }

    private static int[] findCurrentPos(int[][] board, int[] currentPos, int opt) {
        return findPos(board, currentPos, opt);
    }

    private static int[] findNextPos(int[][] board, int[] currentPos, int opt) {
        int[] nextPos = new int[]{currentPos[0] + r[opt], currentPos[1] + c[opt]};
        return findPos(board, nextPos, opt);
    }

    private static int[] initPos(int opt) {
        if (opt % 2 == 1) return new int[]{1, 1};
        else return new int[]{N, N};
    }

    private static int findMax(int[][] board) {
        int max = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (board[i][j] == 0) continue;
                max = Math.max(max, board[i][j]);
            }
        }

        return max;
    }

    private static int[][] init() throws IOException {
        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        return board;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int score;
    static int[][] greenBlock = {{0, 0}, {0, 0}, {0, 1}, {1, 0}};
    static int[][] blueBlock = {{0, 0}, {0, 0}, {1, 0}, {0, 1}};
    static int[][] greenBoard = new int[6][4];
    static int[][] blueBoard = new int[6][4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            simulate(t, x, y);
        }

        StringBuilder sb = new StringBuilder();
        System.out.println(sb.append(score).append("\n").append(remain()).toString());
    }

    private static int remain() {
        int num = 0;
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 4; c++) {
                if (greenBoard[r][c] == 1) num++;
                if (blueBoard[r][c] == 1) num++;
            }
        }
        return num;
    }

    private static void simulate(int t, int x, int y) {
        move(t, x, y);
        find(greenBoard);
        find(blueBoard);
        clean(greenBoard);
        clean(blueBoard);
    }

    private static void move(int t, int x, int y) {
        // 파란 부분을 회전하여 초록색 부분처럼 생각
        // -> 보드 색에 따라 2칸짜리 각 블록이 늘어나는 방향이 다름
        int[] greenStart = {0, y};
        int[] greenEnd = {greenBlock[t][0], y + greenBlock[t][1]};
        while (greenBoard[greenStart[0]][greenStart[1]] != 1 && greenBoard[greenEnd[0]][greenEnd[1]] != 1) {
            greenStart[0]++;
            greenEnd[0]++;
            if (greenEnd[0] > 5) break;
        }
        greenBoard[greenStart[0] - 1][greenStart[1]] = 1;
        greenBoard[greenEnd[0] - 1][greenEnd[1]] = 1;

        int[] blueStart = {0, x};
        int[] blueEnd = {blueBlock[t][0], x + blueBlock[t][1]};
        while (blueBoard[blueStart[0]][blueStart[1]] != 1 && blueBoard[blueEnd[0]][blueEnd[1]] != 1) {
            blueStart[0]++;
            blueEnd[0]++;
            if (blueEnd[0] > 5) break;
        }
        blueBoard[blueStart[0] - 1][blueStart[1]] = 1;
        blueBoard[blueEnd[0] - 1][blueEnd[1]] = 1;
    }

    private static void find(int[][] board) {
        int row = 5;
        while (row >= 2) {
            boolean isFull = true;
            // 가득 찬 행이 있는지 확인
            for (int col = 0; col < 4; col++) {
                if (board[row][col] == 0) {
                    isFull = false;
                    break;
                }
            }

            // 가득 찬 행이 없으면 행 번호 줄여서 확인
            if (!isFull) row--;
            // 가득 찬 행이 있으면 제거하고 한칸 내린 후 다시 그 행부터 확인
            else {
                score++;
                for (int r = row; r > 0; r--) {
                    board[r] = board[r - 1];
                }
                board[0] = new int[4];
            }
        }
    }

    private static void clean(int[][] board) {
        int num = 0;
        // 블록이 있는 연한색 부분의 수
        for (int r = 0; r < 2; r++) {
            for (int c = 0; c < 4; c++) {
                if (board[r][c] == 1) {
                    num++;
                    break;
                }
            }
        }

        // 연한색 부분의 수만큼 아래로 내림
        for (int r = 5; r >= 2; r--) {
            board[r] = board[r - num];
        }

        // 아래로 내린 후 맨 위 두 줄을 새 값으로 생성
        for (int i = 0; i < 2; i++) {
            board[i] = new int[4];
        }
    }
}
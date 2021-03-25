import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static int[][] board;
    static int[] leftPaper;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[10][10];
        leftPaper = new int[5];
        Arrays.fill(leftPaper, 5);
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void dfs(int index, int attach) {
        // 1 찾기
        while (index < 100) {
            if (board[index / 10][index % 10] == 1) break;
            index++;
        }
        // 10x10 보드 모두 확인 완료
        if (index == 100) {
            if (fullAttached()) {
                min = Math.min(min, attach);
            }
            return;
        }
        // 1을 발견한 위치 (r, c)
        int r = index / 10;
        int c = index % 10;
        for (int length = 5; length >= 1; length--) {
            // length x length 크기 색종이를 놓을 수 없음
            if (r + length > 10 || c + length > 10) continue;
            // length x length 크기가 모두 1인지 확인
            if (canMakeSquare(r, c, length)) {
                // 현재 위치에 색종이 놓아봄
                attachPaper(r, c, length, 0);
                leftPaper[length - 1]--;
                dfs(index + length, attach + 1);
                attachPaper(r, c, length, 1);
                leftPaper[length - 1]++;
            }
        }
    }

    private static boolean canMakeSquare(int r, int c, int length) {
        if (leftPaper[length - 1] == 0) return false;
        for (int a = 0; a < length; a++) {
            int nr = r + a;
            for (int b = 0; b < length; b++) {
                int nc = c + b;
                if (board[nr][nc] == 0) return false;
            }
        }
        return true;
    }

    private static void attachPaper(int r, int c, int length, int num) {
        for (int a = 0; a < length; a++) {
            int nr = r + a;
            for (int b = 0; b < length; b++) {
                int nc = c + b;
                board[nr][nc] = num;
            }
        }
    }

    private static boolean fullAttached() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == 1) return false;
            }
        }
        return true;
    }
}
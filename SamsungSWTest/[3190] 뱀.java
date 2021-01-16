import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[][] board = new int[N + 2][N + 2];
        boolean[][] apple = new boolean[N + 1][N + 1];

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            apple[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
        }

        int L = Integer.parseInt(br.readLine());
        int[] time = new int[L];
        char[] rotation = new char[L];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            rotation[i] = st.nextToken().charAt(0);
        }

        ArrayList<Node> snake = new ArrayList<>();
        // 1 : 오른쪽 2 : 아래 3: 왼쪽 4 : 위쪽
        int[] moveX = {0, 0, 1, 0, -1};
        int[] moveY = {0, 1, 0, -1, 0};
        int dir = 1;
        int result = 0;
        snake.add(new Node(1, 1));
        board[1][1] = 1;
        int beforeX, beforeY;
        int afterX, afterY;
        int timeRotationIndex = 0;
        while (true) {
            // second 초 동안 움직임
            beforeX = snake.get(0).getX();
            beforeY = snake.get(0).getY();
            afterX = beforeX + moveX[dir];
            afterY = beforeY + moveY[dir];
            result++;

            // 머리 이동 시 종료 조건
            if (afterX > N || afterX < 1 || afterY > N || afterY < 1 || board[afterX][afterY] == 1) {
                break;
            }

            board[beforeX][beforeY] = 0;
            // 사과 O
            if (apple[afterX][afterY]) {
                if (snake.size() == 1) snake.add(new Node(beforeX, beforeY));
                else snake.add(1, new Node(beforeX, beforeY));
                board[beforeX][beforeY] = 1;
                apple[afterX][afterY] = false;
            }
            // 사과 X -> 몸통 꼬리 움직임
            else {
                int last = snake.size() - 1;
                // 꼬리 좌표에 없음 처리
                board[snake.get(last).getX()][snake.get(last).getY()] = 0;
                for (int i = last; i > 0; i--) {
                    // 몸통, 꼬리를 머리 쪽으로 움직임
                    snake.get(i).setX(snake.get(i - 1).getX());
                    snake.get(i).setY(snake.get(i - 1).getY());
                    board[snake.get(i).getX()][snake.get(i).getY()] = 1;
                }
            }

            // 머리 이동
            snake.get(0).setX(afterX);
            snake.get(0).setY(afterY);
            board[afterX][afterY] = 1;

            // 머리 방향 전환
            if (timeRotationIndex < L) {
                if (time[timeRotationIndex] == result) {
                    if (rotation[timeRotationIndex] == 'L') {
                        dir -= 1;
                        if (dir == 0) dir = 4;
                    } else {
                        dir += 1;
                        if (dir == 5) dir = 1;
                    }
                    timeRotationIndex++;
                }
            }
        }

        System.out.println(result);
    }

    public static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static int result = Integer.MAX_VALUE;
    static int[] hole = {0, 0};
    static int[] moveR = {0, -1, 1, 0, 0};
    static int[] moveC = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        Node node = init();

        simulate(node);

        if (result > 10) System.out.println(-1);
        else System.out.println(result);
    }

    private static void simulate(Node node) {
        LinkedList<Node> linkedList = new LinkedList<>();
        linkedList.add(node);
        while (linkedList.size() != 0) {
            Node tmpNode = linkedList.remove();
            if (tmpNode.num > 10) return;
            for (int opt = 1; opt <= 4; opt++) {
                Node newNode = new Node(tmpNode.red, tmpNode.blue, tmpNode.board, tmpNode.num + 1);
                if (newNode.tilt(opt)) {
                    //  빨간 구슬이 구멍에 빠지면 성공이지만, 파란 구슬이 구멍에 빠지면 실패이다.
                    if (newNode.redEnd) {
                        // 빨간 구슬과 파란 구슬이 동시에 구멍에 빠져도 실패이다.
                        if (newNode.blueEnd) continue;
                        result = newNode.num;
                        return;
                    } else if (newNode.blueEnd) continue;
                    linkedList.add(newNode);
                }
            }
        }
    }

    private static Node init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        char[][] tmpBoard = new char[N + 1][M + 1];
        int[] redPos = {0, 0};
        int[] bluePos = {0, 0};

        for (int i = 1; i <= N; i++) {
            String s = br.readLine();
            for (int j = 1; j <= M; j++) {
                tmpBoard[i][j] = s.charAt(j - 1);
                if (tmpBoard[i][j] == 'R') {
                    redPos[0] = i;
                    redPos[1] = j;
                } else if (tmpBoard[i][j] == 'B') {
                    bluePos[0] = i;
                    bluePos[1] = j;
                } else if (tmpBoard[i][j] == 'O') {
                    hole[0] = i;
                    hole[1] = j;
                }
            }
        }

        return new Node(redPos, bluePos, tmpBoard, 0);
    }

    static class Node {
        int[] red, blue;
        char[][] board;
        int num;
        boolean redEnd, blueEnd;

        public Node(int[] red, int[] blue, char[][] board, int num) {
            this.red = new int[]{red[0], red[1]};
            this.blue = new int[]{blue[0], blue[1]};
            this.board = new char[N + 1][M + 1];
            for (int i = 1; i <= N; i++) {
                this.board[i] = Arrays.copyOf(board[i], M + 1);
            }
            this.num = num;
        }

        public boolean tilt(int opt) {
            boolean move, isTilt = false;
            int r = moveR[opt];
            int c = moveC[opt];
            int pr, pc, nr, nc;

            do {
                move = false;

                if (!redEnd) {
                    pr = red[0];
                    pc = red[1];
                    nr = red[0] + r;
                    nc = red[1] + c;
                    if (nr > 1 && nr < N && nc > 1 && nc < M) {
                        if (this.board[nr][nc] == '.' || this.board[nr][nc] == 'O') {
                            red[0] = nr;
                            red[1] = nc;
                            this.board[pr][pc] = '.';
                            this.board[nr][nc] = 'R';
                            if (red[0] == hole[0] && red[1] == hole[1]) {
                                this.board[nr][nc] = 'O';
                                redEnd = true;
                            }
                            move = true;
                            isTilt = true;
                        }
                    }
                }

                if (!blueEnd) {
                    pr = blue[0];
                    pc = blue[1];
                    nr = blue[0] + r;
                    nc = blue[1] + c;
                    if (nr > 1 && nr < N && nc > 1 && nc < M) {
                        if (this.board[nr][nc] == '.' || this.board[nr][nc] == 'O') {
                            blue[0] = nr;
                            blue[1] = nc;
                            this.board[pr][pc] = '.';
                            this.board[nr][nc] = 'B';
                            if (blue[0] == hole[0] && blue[1] == hole[1]) {
                                this.board[nr][nc] = 'O';
                                blueEnd = true;
                            }
                            move = true;
                            isTilt = true;
                        }
                    }
                }
            } while(move);

            return isTilt;
        }
    }
}
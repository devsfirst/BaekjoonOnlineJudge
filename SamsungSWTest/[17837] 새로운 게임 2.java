import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

    static int N, K;
    static Chess[] chess;
    static int[][] boardColor;
    static Board[][] board;
    static Stack<Integer> stack = new Stack<>();
    static int[] moveR = {0, 0, 0, -1, 1};
    static int[] moveC = {0, 1, -1, 0, 0};
    static final int WHITE = 0;
    static final int RED = 1;
    static final int BLUE = 2;
    static boolean end = false;

    public static void main(String[] args) throws IOException {
        init();
        int turn = simulate();
        if (turn <= 1000) System.out.println(turn);
        else System.out.println(-1);
    }

    private static int simulate() {
        int turn = 0;
        int dir, sr, sc, nr, nc;

        while (true) {
            turn++;
            if (turn > 1000) break;
            for (int id = 1; !end && id <= K; id++) {
                Chess chs = chess[id];
                dir = chs.d;
                sr = chs.r;
                sc = chs.c;
                nr = sr + moveR[dir];
                nc = sc + moveC[dir];
                // 체스판을 벗어나는 경우에는 파란색과 같은 경우이다.
                if (nr < 1 || nr > N || nc < 1 || nc > N || boardColor[nr][nc] == BLUE) {
                    // 파란색인 경우에는 A번 말의 이동 방향을 반대로 하고 한 칸 이동한다.
                    if (dir == 1) dir = 2;
                    else if (dir == 2) dir = 1;
                    else if (dir == 3) dir = 4;
                    else dir = 3;
                    nr = sr + moveR[dir];
                    nc = sc + moveC[dir];
                    chs.d = dir;
                    // 방향을 반대로 바꾼 후에 이동하려는 칸이 파란색인 경우에는 이동하지 않고 가만히 있는다.
                    if (nr < 1 || nr > N || nc < 1 || nc > N || boardColor[nr][nc] == BLUE) { }
                    else if (boardColor[nr][nc] == WHITE) moveWhite(chs, sr, sc, nr, nc);
                    else if (boardColor[nr][nc] == RED) moveRed(chs, sr, sc, nr, nc);
                } else {
                    if (boardColor[nr][nc] == WHITE) moveWhite(chs, sr, sc, nr, nc);
                    else if (boardColor[nr][nc] == RED) moveRed(chs, sr, sc, nr, nc);
                }
            }
            if (end) break;
        }

        return turn;
    }

    private static void moveWhite(Chess chs, int sr, int sc, int nr, int nc) {
        // 이동하려는 칸이 흰색인 경우에는 그 칸으로 이동한다.
        // 이동하려는 칸에 말이 이미 있는 경우에는 가장 위에 A번 말을 올려놓는다.
        // A번 말의 위에 다른 말이 있는 경우에는 A번 말과 위에 있는 모든 말이 이동한다.
        moveNext(chs, sr, sc, nr, nc);
    }

    private static void moveRed(Chess chs, int sr, int sc, int nr, int nc) {
        board[sr][sc].top = chs.prev;
        int nextId = chs.id;

        // 빨간색인 경우에는 이동한 후에 A번 말과 그 위에 있는 모든 말의 쌓여있는 순서를 반대로 바꾼다.
        int topChess = 0;
        while (nextId != 0) {
            topChess = nextId;
            stack.add(nextId);
            nextId = chess[nextId].next;
        }

        while (stack.size() != 0) {
            chs = chess[stack.pop()];
            chs.prev = board[sr][sc].top;
            if (chs.prev != 0) chess[chs.prev].next = chs.id;
            board[sr][sc].top = chs.id;
        }
        chs.next = 0;

        moveNext(chess[topChess], sr, sc, nr, nc);
    }

    private static void moveNext(Chess chs, int sr, int sc, int nr, int nc) {
        if (chs.prev != 0) chess[chs.prev].next = 0;
        board[sr][sc].top = chs.prev;
        chs.prev = board[nr][nc].top;
        if (chs.prev != 0) chess[chs.prev].next = chs.id;
        while (chs != null) {
            chs.r = nr;
            chs.c = nc;
            board[sr][sc].num--;
            board[nr][nc].top = chs.id;
            board[nr][nc].num++;
            chs = chess[chs.next];
            if (board[nr][nc].num == 4) {
                end = true;
                break;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        boardColor = new int[N + 1][N + 1];
        board = new Board[N + 1][N + 1];
        chess = new Chess[K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                boardColor[i][j] = Integer.parseInt(st.nextToken());
                board[i][j] = new Board();
            }
        }

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            chess[i] = new Chess(i, r, c, d);
            board[r][c].num++;
            board[r][c].top = i;
        }
    }

    static class Board {
        int num, top;

        public Board() {
            this.num = 0;
            this.top = 0;
        }
    }

    static class Chess {
        int id, r, c, d, next, prev;

        public Chess(int id, int r, int c, int d) {
            this.id = id;
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}
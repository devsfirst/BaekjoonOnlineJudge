import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static final int GOAL = 21;
    static Node[] nodes;
    static Piece[] pieces;
    static int[] move;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        init();

        simulate(0, 1);

        System.out.println(result);
    }

    private static void simulate(int sum, int moveIndex) {
        if (moveIndex > 10) {
            result = Math.max(result, sum);
            return;
        }

        for (Piece piece : pieces) {
            if (piece.isEnd) continue;
            int currentPosition = piece.position;
            int nextPosition = nodes[currentPosition].next[move[moveIndex]];

            if (nodes[nextPosition].hasPiece) {
                if (nextPosition == GOAL) piece.isEnd = true;
                else continue;
            }
            piece.position = nextPosition;
            nodes[currentPosition].hasPiece = false;
            nodes[nextPosition].hasPiece = true;
            simulate(sum + nodes[nextPosition].point, moveIndex + 1);
            if (nextPosition == GOAL) piece.isEnd = false;
            piece.position = currentPosition;
            nodes[currentPosition].hasPiece = true;
            nodes[nextPosition].hasPiece = false;
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        pieces = new Piece[4];
        for (int i = 0; i < 4; i++) {
            pieces[i] = new Piece(0);
        }

        move = new int[11];
        for (int i = 1; i <= 10; i++) {
            move[i] = Integer.parseInt(st.nextToken());
        }

        nodes = new Node[33];
        for (int i = 0; i < 33; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < 21; i++) {
            nodes[i].point = 2 * i;
        }
        nodes[22].point = 13;
        nodes[23].point = 16;
        nodes[24].point = 19;
        nodes[25].point = 22;
        nodes[26].point = 24;
        nodes[27].point = 28;
        nodes[28].point = 27;
        nodes[29].point = 26;
        nodes[30].point = 25;
        nodes[31].point = 30;
        nodes[32].point = 35;

        nodes[0].next = new int[]{0, 1, 2, 3, 4, 5};
        nodes[1].next = new int[]{0, 2, 3, 4, 5, 6};
        nodes[2].next = new int[]{0, 3, 4, 5, 6, 7};
        nodes[3].next = new int[]{0, 4, 5, 6, 7, 8};
        nodes[4].next = new int[]{0, 5, 6, 7, 8, 9};
        nodes[5].next = new int[]{0, 22, 23, 24, 30, 31};
        nodes[6].next = new int[]{0, 7, 8, 9, 10, 11};
        nodes[7].next = new int[]{0, 8, 9, 10, 11, 12};
        nodes[8].next = new int[]{0, 9, 10, 11, 12, 13};
        nodes[9].next = new int[]{0, 10, 11, 12, 13, 14};
        nodes[10].next = new int[]{0, 25, 26, 30, 31, 32};
        nodes[11].next = new int[]{0, 12, 13, 14, 15, 16};
        nodes[12].next = new int[]{0, 13, 14, 15, 16, 17};
        nodes[13].next = new int[]{0, 14, 15, 16, 17, 18};
        nodes[14].next = new int[]{0, 15, 16, 17, 18, 19};
        nodes[15].next = new int[]{0, 27, 28, 29, 30, 31};
        nodes[16].next = new int[]{0, 17, 18, 19, 20, GOAL};
        nodes[17].next = new int[]{0, 18, 19, 20, GOAL, GOAL};
        nodes[18].next = new int[]{0, 19, 20, GOAL, GOAL, GOAL};
        nodes[19].next = new int[]{0, 20, GOAL, GOAL, GOAL, GOAL};
        nodes[20].next = new int[]{0, GOAL, GOAL, GOAL, GOAL, GOAL};
        nodes[21].next = new int[]{0, GOAL, GOAL, GOAL, GOAL, GOAL};
        nodes[22].next = new int[]{0, 23, 24, 30, 31, 32};
        nodes[23].next = new int[]{0, 24, 30, 31, 32, 20};
        nodes[24].next = new int[]{0, 30, 31, 32, 20, GOAL};
        nodes[25].next = new int[]{0, 26, 30, 31, 32, 20};
        nodes[26].next = new int[]{0, 30, 31, 32, 20, GOAL};
        nodes[27].next = new int[]{0, 28, 29, 30, 31, 32};
        nodes[28].next = new int[]{0, 29, 30, 31, 32, 20};
        nodes[29].next = new int[]{0, 30, 31, 32, 20, GOAL};
        nodes[30].next = new int[]{0, 31, 32, 20, GOAL, GOAL};
        nodes[31].next = new int[]{0, 32, 20, GOAL, GOAL, GOAL};
        nodes[32].next = new int[]{0, 20, GOAL, GOAL, GOAL, GOAL};
    }

    static class Node {
        int num;
        int point;
        int[] next;
        boolean hasPiece;

        public Node(int num) {
            this.num = num;
        }
    }

    static class Piece {
        int position;
        boolean isEnd;

        public Piece(int position) {
            this.position = position;
        }
    }
}
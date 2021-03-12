import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

    static int R, C, M;
    static Shark[][] board;
    static ArrayList<Shark> sharks = new ArrayList<>();
    static int[] moveR = {0, -1, 1, 0, 0};
    static int[] moveC = {0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        init();

        System.out.println(simulate());
    }

    private static int simulate() {
        int sharkSize = 0;
        int fisher = 0;
        while (true) {
            Shark[][] newBoard = new Shark[R + 1][C + 1];
            ArrayList<Shark> newSharks = new ArrayList<>();
            // 1. 낚시왕이 오른쪽으로 한 칸 이동한다.
            fisher++;

            // 낚시왕은 가장 오른쪽 열의 오른쪽 칸에 이동하면 이동을 멈춘다.
            if (fisher > C) break;

            // 서있는 열에서 땅과 제일 가까운 상어 찾기
            sharkSize = catchShark(fisher, sharkSize);

            // 3. 상어가 이동한다.
            moveShark(newBoard, newSharks);

            board = newBoard;
            sharks = newSharks;
        }

        return sharkSize;
    }

    private static void moveShark(Shark[][] newBoard, ArrayList<Shark> newSharks) {
        // 상어는 입력으로 주어진 속도로 이동하고, 속도의 단위는 칸/초이다.
        for (Shark shark : sharks) {
            int s = shark.s;
            int d = shark.d;
            int pr = shark.r, pc = shark.c;
            int nr = pr, nc = pc;

            for (int move = 1; move <= s; move++) {
                pr = shark.r;
                pc = shark.c;
                nr = pr + moveR[d];
                nc = pc + moveC[d];
                // 상어가 이동하려고 하는 칸이 격자판의 경계를 넘는 경우에는 방향을 반대로 바꿔서 속력을 유지한채로 이동한다.
                if (nr < 1 || nr > R || nc < 1 || nc > C) {
                    if (d == 1) d = 2;
                    else if (d == 2) d = 1;
                    else if (d == 3) d = 4;
                    else d = 3;
                    shark.d = d;
                    nr = pr + moveR[d];
                    nc = pc + moveC[d];
                }
                shark.r = nr;
                shark.c = nc;
            }

            // 상어가 이동을 마친 후에 한 칸에 상어가 두 마리 이상 있을 수 있다.
            if (newBoard[nr][nc] != null) {
                // 이때는 크기가 가장 큰 상어가 나머지 상어를 모두 잡아먹는다.
                Shark tmpShark = newBoard[nr][nc];
                if (tmpShark.z < shark.z) {
                    newBoard[nr][nc] = shark;
                    newSharks.remove(tmpShark);
                    newSharks.add(shark);
                }
            } else {
                newBoard[nr][nc] = shark;
                newSharks.add(shark);
            }
        }
    }

    private static int catchShark(int fisher, int sharkSize) {
        for (int r = 1; r <= R; r++) {
            if (board[r][fisher] == null) continue;
            // 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다.
            sharkSize += board[r][fisher].z;
            // 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
            sharks.remove(board[r][fisher]);
            board[r][fisher] = null;
            break;
        }

        return sharkSize;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new Shark[R + 1][C + 1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d =  Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            if (d == 1 || d == 2) s %= (R - 1) * 2;
            else s %= (C - 1) * 2;
            board[r][c] = new Shark(r, c, s, d, z);
            sharks.add(board[r][c]);
        }
    }

    static class Shark {
        int r, c, s, d, z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}
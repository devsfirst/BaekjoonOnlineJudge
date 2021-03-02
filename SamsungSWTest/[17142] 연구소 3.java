import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static int[][] board;
    static int[] virusRow, virusCol;
    static int virusIndex;
    static boolean[] active;
    static int[] r = {-1, 1, 0, 0};
    static int[] c = {0, 0, -1, 1};
    static int totalInfectNum;
    static int time = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        virusRow = new int[10];
        virusCol = new int[10];
        active = new boolean[10];
        totalInfectNum = N * N;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) {
                    virusRow[virusIndex] = i;
                    virusCol[virusIndex++] = j;
                    totalInfectNum--;
                } else if (board[i][j] == 1) {
                    totalInfectNum--;
                }
            }
        }

        backtracking(0, 0);

        if (time == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(time);
    }

    private static void backtracking(int activeVirus, int index) {
        if (activeVirus == M) {
            time = Math.min(time, simulate());
            return;
        }

        for (int i = index; i < virusIndex; i++) {
            if (!active[i]) {
                active[i] = true;
                backtracking(activeVirus + 1, i + 1);
                active[i] = false;
            }
        }
    }

    private static int simulate() {
        int t = 0;
        int infectNum = 0;
        boolean isInfect;
        boolean[][] prevInfectBoard = new boolean[N][N];
        ArrayList<int[]> prevList = new ArrayList<>();

        // 처음 바이러스 좌표
        for (int i = 0; i < virusIndex; i++) {
            if (active[i]) {
                prevList.add(new int[]{virusRow[i], virusCol[i]});
                prevInfectBoard[virusRow[i]][virusCol[i]] = true;
            }
        }

        if (totalInfectNum == 0) return 0;

        while (true) {
            t++;
            isInfect = false;

            // 1초 후 상태와 전파한 좌표
            boolean[][] nextInfectBoard = new boolean[N][N];
            ArrayList<int[]> nextList = new ArrayList<>();

            // prevList에 있는 모든 좌표에 대해 감염 진행 -> 1초 지나는 것
            while (prevList.size() != 0) {
                int[] tmp = prevList.remove(prevList.size() - 1);
                int i = tmp[0];
                int j = tmp[1];

                if (board[i][j] != 1 && prevInfectBoard[i][j]) {
                    nextInfectBoard[i][j] = true;
                    for (int dir = 0; dir < 4; dir++) {
                        // 범위
                        if (i + r[dir] < 0 || i + r[dir] >= N || j + c[dir] < 0 || j + c[dir] >= N) continue;
                        // 벽
                        if (board[i + r[dir]][j + c[dir]] == 1) continue;
                        // 이미 감염됨
                        if (prevInfectBoard[i + r[dir]][j + c[dir]]) continue;
                        if (nextInfectBoard[i + r[dir]][j + c[dir]]) continue;

                        isInfect = true;
                        // 비활성 바이러스
                        if (board[i + r[dir]][j + c[dir]] != 2) infectNum++;
                        nextInfectBoard[i + r[dir]][j + c[dir]] = true;
                        nextList.add(new int[]{i + r[dir], j + c[dir]});
                    }
                }
            }

            // 감염돼야할 곳 모두 감염됨
            if (infectNum == totalInfectNum) break;

            // 모두 감염되지 않았는데 바이러스 전파 안됨
            if (!isInfect) return Integer.MAX_VALUE;

            prevInfectBoard = nextInfectBoard;
            prevList = nextList;
        }

        return t;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static int N, M, K;
    static int[][] A;
    static boolean[] used;
    static Rotation[] rotationInfo;
    static Rotation[] rotations;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();

        backtracking(0);

        System.out.println(min);
    }

    private static void backtracking(int rotationSeq) {
        if (rotationSeq == K) {
            simulate();
            return;
        }

        for (int i = 0; i < K; i++) {
            if (!used[i]) {
                used[i] = true;
                rotations[rotationSeq] = rotationInfo[i];
                backtracking(rotationSeq + 1);
                used[i] = false;
            }
        }
    }

    private static void simulate() {
        int[][] copyA = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            copyA[i] = Arrays.copyOf(A[i], M + 1);
        }

        rotate(copyA);
        findMin(copyA);
    }

    private static void rotate(int[][] copyA) {
        // K개 (r, c, s) rotation 정보
        for (int i = 0; i < K; i++) {
            Rotation rotation = rotations[i];
            int s = rotation.s;
            int r = rotation.r - s;
            int c = rotation.c - s;
            int sideLength = 2 * s; // 한 변에서 회전할 배열 요소 길이
            for (int j = 0; j < s; j++) {
                // 시작 위치 (currentR, currentC)
                int currentR = r + j;
                int currentC = c + j;
                int tmp = copyA[currentR][currentC];
                for (int k = 0; k < sideLength; k++) copyA[currentR][currentC] = copyA[++currentR][currentC];
                for (int k = 0; k < sideLength; k++) copyA[currentR][currentC] = copyA[currentR][++currentC];
                for (int k = 0; k < sideLength; k++) copyA[currentR][currentC] = copyA[--currentR][currentC];
                for (int k = 0; k < sideLength - 1; k++) copyA[currentR][currentC] = copyA[currentR][--currentC];
                copyA[currentR][currentC] = tmp;
                sideLength -= 2;
            }
        }
    }

    private static void findMin(int[][] copyA) {
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= M; j++) {
                sum += copyA[i][j];
            }
            min = Math.min(min, sum);
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N + 1][M + 1];
        used = new boolean[K];
        rotationInfo = new Rotation[K];
        rotations = new Rotation[K];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            rotationInfo[i] = new Rotation(r, c, s);
        }
    }

    static class Rotation {
        int r, c, s;

        public Rotation(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
}
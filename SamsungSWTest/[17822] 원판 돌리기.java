import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, T;
    static int[] x, d, k;
    static int[][] circle;
    static int result;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        x = new int[T + 1];
        d = new int[T + 1];
        k = new int[T + 1];
        circle = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                circle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st.nextToken());
            k[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= T; i++) {
            // 번호가 xi의 배수인 원판을 di방향으로 ki칸 회전시킨다. di가 0인 경우는 시계 방향, 1인 경우는 반시계 방향이다.
            int xi = x[i];
            int di = d[i];
            int ki = k[i];

            for (int c = xi; c <= N; c += xi) {
                int[] tmpCircle = new int[M + 1];
                if (di == 0) {
                    // 시계 방향
                    for (int index = 1; index <= M; index++) {
                        int tmp = (index + ki) % M;
                        if (tmp == 0) tmp = M;
                        //else if (tmp > M) tmp = (tmp + 1) % mod;
                        tmpCircle[tmp] = circle[c][index];
                    }
                } else {
                    // 반시계 방향
                    for (int index = 1; index <= M; index++) {
                        int tmp = (index - ki) % M;
                        if (tmp == 0) tmp = M;
                        else if (tmp < 0) tmp = M + tmp;
                        tmpCircle[tmp] = circle[c][index];
                    }
                }
                circle[c] = tmpCircle;
            }

            int[][] newCircle = new int[N + 1][M + 1];
            for (int j = 1; j <= N; j++) {
                newCircle[j] = Arrays.copyOf(circle[j], M + 1);
            }

            // 원판에 수가 남아 있으면, 인접하면서 수가 같은 것을 모두 찾는다.
            // 그러한 수가 있는 경우에는 원판에서 인접하면서 같은 수를 모두 지운다.
            boolean find = false;
            for (int a = 1; a <= N; a++) {
                for (int b = 1; b <= M; b++) {
                    int num = circle[a][b];
                    newCircle[a][b] = num;
                    if (num == 0) continue;

                    if (check1(a, b, num, newCircle)) find = true;
                    if (check2(a, b, num, newCircle)) find = true;
                }
            }
            circle = newCircle;

            //  없는 경우에는 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
            if (!find) {
                double avg = average();
                for (int a = 1; a <= N; a++) {
                    for (int b = 1; b <= M; b++) {
                        if (circle[a][b] == 0) continue;
                        if (circle[a][b] > avg) circle[a][b]--;
                        else if (circle[a][b] < avg) circle[a][b]++;
                    }
                }
            }
        }

        // 원판을 T번 회전시킨 후 원판에 적힌 수의 합을 구해보자.
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                result += circle[i][j];
            }
        }

        System.out.println(result);
    }

    private static double average() {
        double sum = 0;
        int num = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (circle[i][j] != 0) num++;
                sum += circle[i][j];
            }
        }
        return sum / num;
    }

    private static boolean check1(int a, int b, int num, int[][] newCircle) {
        boolean ret = false;
        // (i, 1)은 (i, 2), (i, M)과 인접하다.
        if (b == 1) {
            if (num == circle[a][2]) {
                newCircle[a][b] = 0;
                newCircle[a][2] = 0;
                ret = true;
            }
            if (num == circle[a][M]) {
                newCircle[a][b] = 0;
                newCircle[a][M] = 0;
                ret = true;
            }
            // (i, M)은 (i, M-1), (i, 1)과 인접하다.
        } else if (b == M) {
            if (num == circle[a][M - 1]) {
                newCircle[a][b] = 0;
                newCircle[a][M - 1] = 0;
                ret = true;
            }
            if (num == circle[a][1]) {
                newCircle[a][b] = 0;
                newCircle[a][1] = 0;
                ret = true;
            }
            // (i, j)는 (i, j-1), (i, j+1)과 인접하다. (2 ≤ j ≤ M-1)
        } else {
            if (num == circle[a][b - 1]) {
                newCircle[a][b] = 0;
                newCircle[a][b - 1] = 0;
                ret = true;
            }
            if (num == circle[a][b + 1]) {
                newCircle[a][b] = 0;
                newCircle[a][b + 1] = 0;
                ret = true;
            }
        }

        return ret;
    }

    private static boolean check2(int a, int b, int num, int[][] newCircle) {
        boolean ret = false;
        // (1, j)는 (2, j)와 인접하다.
        if (a == 1) {
            if (num == circle[2][b]) {
                newCircle[a][b] = 0;
                newCircle[2][b] = 0;
                ret = true;
            }
            // (N, j)는 (N-1, j)와 인접하다.
        } else if (a == N) {
            if (num == circle[N - 1][b]) {
                newCircle[a][b] = 0;
                newCircle[N - 1][b] = 0;
                ret = true;
            }
            // (i, j)는 (i-1, j), (i+1, j)와 인접하다. (2 ≤ i ≤ N-1)
        } else {
            if (num == circle[a - 1][b]) {
                newCircle[a][b] = 0;
                newCircle[a - 1][b] = 0;
                ret = true;
            }
            if (num == circle[a + 1][b]) {
                newCircle[a][b] = 0;
                newCircle[a + 1][b] = 0;
                ret = true;
            }
        }

        return ret;
    }
}
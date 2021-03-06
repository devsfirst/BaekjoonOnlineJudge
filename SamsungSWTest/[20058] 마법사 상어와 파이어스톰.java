import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, Q, size;
    static int[][] ice;
    static int[] r = {-1, 0 , 1, 0};
    static int[] c = {0, 1, 0, -1};
    static boolean[][] visit;
    static int tmpMaxIce;
    static int result1, result2;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        size = (int) Math.pow(2, N);
        ice = new int[size + 1][size + 1];
        visit = new boolean[size + 1][size + 1];

        for (int i = 1; i <= size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= size; j++) {
                ice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= Q; i++) {
            rotate(Integer.parseInt(st.nextToken()));
            checkAdjacent();
        }

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                result1 += ice[i][j];
                if (!visit[i][j] && ice[i][j] != 0) {
                    visit[i][j] = true;
                    tmpMaxIce = 1;
                    maxIce(i, j);
                }
            }
        }

        System.out.println(result1);
        System.out.println(result2);
    }

    private static void rotate(int L) {
        // 부분 격자 개수
        int grid = (int) (size * size / Math.pow(2, 2 * L));
        // 부분 격자의 한 변의 길이
        int sideLength = (int) Math.pow(2, L);
        int[] startPosition = {1, 1};
        // 부분 격자마다 회전 진행
        // 부분 격자의 가장 바깥쪽 테두리부터 회전
        for (int gridNum = 1; gridNum <= grid; gridNum++) {
            // 한 테두리 내의 현재 위치
            int[] location = {startPosition[0], startPosition[1]};
            int tmpSideLength = sideLength;
            while (tmpSideLength > 1) {
                int rotationNum = tmpSideLength - 1;
                for (int rotation = 0; rotation < rotationNum; rotation++) {
                    int tmp = ice[location[0]][location[1]];
                    for (int r = 0; r < rotationNum; r++) {
                        ice[location[0]][location[1]] = ice[location[0] + 1][location[1]];
                        location[0]++;
                    }

                    for (int r = 0; r < rotationNum; r++) {
                        ice[location[0]][location[1]] = ice[location[0]][location[1] + 1];
                        location[1]++;
                    }

                    for (int r = 0; r < rotationNum; r++) {
                        ice[location[0]][location[1]] = ice[location[0] - 1][location[1]];
                        location[0]--;
                    }

                    for (int r = 0; r < rotationNum - 1; r++) {
                        ice[location[0]][location[1]] = ice[location[0]][location[1] - 1];
                        location[1]--;
                    }

                    ice[location[0]][location[1]] = tmp;
                    location[1]--;
                }
                location[0]++;
                location[1]++;
                tmpSideLength -= 2;
            }

            startPosition[1] += sideLength;
            if (startPosition[1] > size) {
                startPosition[0] += sideLength;
                startPosition[1] = 1;
            }
        }
    }

    private static void checkAdjacent() {
        int[][] newIce = new int[size + 1][size + 1];
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (ice[i][j] == 0) {
                    newIce[i][j] = 0;
                    continue;
                }
                int adjacent = 0;
                for (int d = 0; d < 4; d++) {
                    int nextR = i + r[d];
                    int nextC = j + c[d];
                    if (nextR < 1 || nextR > size || nextC < 1 || nextC > size) continue;
                    if (ice[nextR][nextC] > 0) adjacent++;
                }
                if (adjacent < 3) newIce[i][j] = ice[i][j] - 1;
                else newIce[i][j] = ice[i][j];
            }
        }
        ice = newIce;
    }

    private static void maxIce(int i, int j) {
        for (int d = 0; d < 4; d++) {
            int nextR = i + r[d];
            int nextC = j + c[d];
            if (nextR < 1 || nextR > size || nextC < 1 || nextC > size) continue;
            if (!visit[nextR][nextC] && ice[nextR][nextC] != 0) {
                visit[nextR][nextC] = true;
                tmpMaxIce++;
                result2 = Math.max(result2, tmpMaxIce);
                maxIce(nextR, nextC);
            }
        }
    }
}
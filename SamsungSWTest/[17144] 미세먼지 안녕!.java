import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int R, C, T;
    static int[][] map;
    static int[][] newMap;
    static int upperWind;
    static int lowerWind;
    static int[] moveR = {-1, 0, 1, 0};
    static int[] moveC = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R + 1][C + 1];

        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 공기청정기 위치
                if (map[i][j] == -1 && upperWind == 0) {
                    upperWind = i;
                    lowerWind = i + 1;
                }
            }
        }

        for (int i = 0; i < T; i++) {
            newMap = new int[R + 1][C + 1];
            /**
             * 1. 미세먼지가 확산된다. 확산은 미세먼지가 있는 모든 칸에서 동시에 일어난다.
             * (r, c)에 있는 미세먼지는 인접한 네 방향으로 확산된다.
             * 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다.
             * 확산되는 양은 Ar,c/5이고 소수점은 버린다.
             * (r, c)에 남은 미세먼지의 양은 Ar,c - (Ar,c/5)×(확산된 방향의 개수) 이다.
             * */
            diffusion();

            /**
             * 2. 공기청정기가 작동한다.
             * 공기청정기에서는 바람이 나온다.
             * 위쪽 공기청정기의 바람은 반시계방향으로 순환하고, 아래쪽 공기청정기의 바람은 시계방향으로 순환한다.
             * 바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동한다.
             * 공기청정기에서 부는 바람은 미세먼지가 없는 바람이고, 공기청정기로 들어간 미세먼지는 모두 정화된다.
             * */
            airFresher();

            map = newMap;
        }

        int result = 0;
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                result += map[i][j];
            }
        }

        System.out.println(result);
    }

    private static void airFresher() {
        // 위쪽 공기청정기
        // 1열 처리
        for (int i = upperWind - 1; i >= 2; i--) newMap[i][1] = newMap[i - 1][1];
        // 1행 처리
        for (int j = 1; j < C; j++) newMap[1][j] = newMap[1][j + 1];
        // C열 처리
        for (int i = 1; i < upperWind; i++) newMap[i][C] = newMap[i + 1][C];
        // upperWind행 처리
        for (int j = C; j >= 3; j--) newMap[upperWind][j] = newMap[upperWind][j - 1];
        newMap[upperWind][2] = 0;

        // 아래쪽 공기청정기
        // 1열 처리
        for (int i = lowerWind + 1; i < R; i++) newMap[i][1] = newMap[i + 1][1];
        // R행 처리
        for (int j = 1; j < C; j++) newMap[R][j] = newMap[R][j + 1];
        // C열 처리
        for (int i = R; i > lowerWind; i--) newMap[i][C] = newMap[i - 1][C];
        // lowerWind행 처리
        for (int j = C; j >= 3; j--) newMap[lowerWind][j] = newMap[lowerWind][j - 1];
        newMap[lowerWind][2] = 0;
    }

    private static void diffusion() {
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (map[i][j] > 0) {
                    // 확산된 방향
                    int dir = 0;
                    for (int d = 0; d < 4; d++) {
                        // 확산될 곳 범위 검사
                        if (i + moveR[d] >= 1 && i + moveR[d] <= R && j + moveC[d] >= 1 && j + moveC[d] <= C) {
                            // 확산될 곳에 공기청정기가 있으면 넘어감
                            if (j + moveC[d] == 1 && (i + moveR[d] == upperWind || i + moveR[d] == lowerWind)) continue;
                            newMap[i + moveR[d]][j + moveC[d]] += (map[i][j] / 5);
                            dir++;
                        }
                    }
                    if (dir != 0) {
                        newMap[i][j] += (map[i][j] - (map[i][j] / 5 * dir));
                    }
                }
            }
        }
    }
}
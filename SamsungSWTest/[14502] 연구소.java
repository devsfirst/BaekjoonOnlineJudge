import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visit;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int a = 0; a < N; a++) {
            for (int b = 0; b < M; b++) {
                if (map[a][b] == 0) {
                    visit[a][b] = true;
                    map[a][b] = 3;
                    for (int c = 0; c < N; c++) {
                        for (int d = 0; d < M; d++) {
                            if (map[c][d] == 0 && !visit[c][d]) {
                                visit[c][d] = true;
                                map[c][d] = 3;
                                for (int e = 0; e < N; e++) {
                                    for (int f = 0; f < M; f++) {
                                        if (map[e][f] == 0 && !visit[e][f]) {
                                            visit[e][f] = true;
                                            map[e][f] = 3;
                                            for (int i = 0; i < N; i++) {
                                                for (int j = 0; j < M; j++) {
                                                    if (map[i][j] == 2) {
                                                        if (i + 1 < N) spread(i + 1, j);
                                                        if (i - 1 >= 0) spread(i - 1, j);
                                                        if (j + 1 < M) spread(i, j + 1);
                                                        if (j - 1 >= 0) spread(i, j - 1);
                                                    }
                                                }
                                            }
                                            count();
                                            recovery();
                                            map[e][f] = 0;
                                            visit[e][f] = false;
                                        }
                                    }
                                }
                                map[c][d] = 0;
                                visit[c][d] = false;
                            }
                        }
                    }
                    map[a][b] = 0;
                    visit[a][b] = false;
                }
            }
        }

        System.out.println(max);
    }

    private static void recovery() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 4) {
                    map[i][j] = 0;
                }
            }
        }
    }

    private static void count() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }
        max = Math.max(max, count);
    }

    private static void spread(int i, int j) {
        if (map[i][j] == 0) {
            map[i][j] = 4;

            if (i + 1 < N) spread(i + 1, j);
            if (i - 1 >= 0) spread(i - 1, j);
            if (j + 1 < M) spread(i, j + 1);
            if (j - 1 >= 0) spread(i, j - 1);
        }
    }
}
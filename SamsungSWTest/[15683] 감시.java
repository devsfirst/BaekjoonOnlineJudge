import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int cameraNum = 0;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int map[][] = new int[N][M];
        boolean visit[][] = new boolean[N][M];
        Camera[] cameras = new Camera[8];
        int wall = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j * 2) - '0';
                if (map[i][j] == 6) wall++;
                else if (map[i][j] != 0) {
                    cameras[cameraNum] = new Camera(i, j, map[i][j]);
                    cameraNum++;
                }
            }
        }

        dfs(map, visit, cameras, 0);

        System.out.println(N * M - max - cameraNum - wall);
    }

    private static void dfs(int[][] map, boolean[][] visit, Camera[] cameras, int index) {
        if (index == cameraNum) {
            int visitNum = 0;
            for (int i = 0; i < visit.length; i++) {
                for (int j = 0; j < visit[0].length; j++) {
                    if (visit[i][j]) {
                        visitNum++;
                    }
                }
            }
            max = Math.max(max, visitNum);
            return;
        }

        int cameraR = cameras[index].getRow();
        int cameraC = cameras[index].getCol();

        switch (cameras[index].getNum()) {
            case 1:
                for (int dir = 0; dir < 4; dir++) {
                    boolean[][] newVisit = new boolean[visit.length][visit[0].length];
                    for (int a = 0; a < newVisit.length; a++) {
                        System.arraycopy(visit[a], 0, newVisit[a], 0, visit[0].length);
                    }
                    search(map, newVisit, cameraR, cameraC, dir);
                    dfs(map, newVisit, cameras, index + 1);
                }
                break;
            case 2:
                for (int dir = 0; dir < 2; dir++) {
                    boolean[][] newVisit = new boolean[visit.length][visit[0].length];
                    for (int a = 0; a < newVisit.length; a++) {
                        System.arraycopy(visit[a], 0, newVisit[a], 0, visit[0].length);
                    }
                    search(map, newVisit, cameraR, cameraC, dir);
                    search(map, newVisit, cameraR, cameraC, dir + 2);
                    dfs(map, newVisit, cameras, index + 1);
                }
                break;
            case 3:
                for (int dir = 0; dir < 4; dir++) {
                    boolean[][] newVisit = new boolean[visit.length][visit[0].length];
                    for (int a = 0; a < newVisit.length; a++) {
                        System.arraycopy(visit[a], 0, newVisit[a], 0, visit[0].length);
                    }
                    search(map, newVisit, cameraR, cameraC, dir);
                    search(map, newVisit, cameraR, cameraC, (dir + 1) % 4);
                    dfs(map, newVisit, cameras, index + 1);
                }
                break;
            case 4:
                for (int dir = 0; dir < 4; dir++) {
                    boolean[][] newVisit = new boolean[visit.length][visit[0].length];
                    for (int a = 0; a < newVisit.length; a++) {
                        System.arraycopy(visit[a], 0, newVisit[a], 0, visit[0].length);
                    }
                    search(map, newVisit, cameraR, cameraC, dir);
                    search(map, newVisit, cameraR, cameraC, (dir + 1) % 4);
                    search(map, newVisit, cameraR, cameraC, (dir + 2) % 4);
                    dfs(map, newVisit, cameras, index + 1);
                }
                break;
            case 5:
                boolean[][] newVisit = new boolean[visit.length][visit[0].length];
                for (int a = 0; a < newVisit.length; a++) {
                    System.arraycopy(visit[a], 0, newVisit[a], 0, visit[0].length);
                }
                search(map, newVisit, cameraR, cameraC, 0);
                search(map, newVisit, cameraR, cameraC, 1);
                search(map, newVisit, cameraR, cameraC, 2);
                search(map, newVisit, cameraR, cameraC, 3);
                dfs(map, newVisit, cameras, index + 1);
                break;
        }
    }

    private static void search(int[][] map, boolean[][] visit, int i, int j, int dir) {
        switch (dir) {
            case 0:
                for (int r = i - 1; r >= 0; r--) {
                    if (map[r][j] == 6) break;
                    if (map[r][j] == 0 && !visit[r][j]) visit[r][j] = true;
                }
                break;
            case 1:
                for (int c = j + 1; c < M; c++) {
                    if (map[i][c] == 6) break;
                    if (map[i][c] == 0 && !visit[i][c]) visit[i][c] = true;
                }
                break;
            case 2:
                for (int r = i + 1; r < N; r++) {
                    if (map[r][j] == 6) break;
                    if (map[r][j] == 0 && !visit[r][j]) visit[r][j] = true;
                }
                break;
            case 3:
                for (int c = j - 1; c >= 0; c--) {
                    if (map[i][c] == 6) break;
                    if (map[i][c] == 0 && !visit[i][c]) visit[i][c] = true;
                }
                break;
        }
    }

    public static class Camera {
        private final int row;
        private final int col;
        private final int num;

        public Camera(int row, int col, int num) {
            this.row = row;
            this.col = col;
            this.num = num;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getNum() {
            return num;
        }
    }
}
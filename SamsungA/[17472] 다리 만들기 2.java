import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

    static int N, M, island;
    static int[][] map;
    static boolean[][] connect;
    static int[] moveR = {-1, 1, 0 , 0};
    static int[] moveC = {0 , 0, -1, 1};
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();

        dfs(0, 0);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void dfs(int index, int bridgeLength) {
        if (allIslandConnected()) {
            min = Math.min(min, bridgeLength);
            return;
        }

        // map[index / M][index % M]가 섬인 index 를 찾음
        for (int i = index; i < N * M; i++) {
            int r = i / M;
            int c = i % M;
            if (map[r][c] == 0) continue;
            int thisIsland = map[r][c];
            // 섬의 한 좌표에서 4방향으로 다른 섬이 있는지 확인
            for (int d = 0; d < 4; d++) {
                // 현재 섬 - 건너 섬 까지의 다리 길이
                int length = distance(r, c, d);
                // 다리를 놓을 수 있음
                if (length >= 2) {
                    // 이미 두 섬 사이에 다리가 있으면 건너뜀
                    if (d == 0) {
                        if (connect[thisIsland][map[r + moveR[d] * length - 1][c]] && connect[map[r + moveR[d] * length - 1][c]][thisIsland]) continue;
                    } else if (d == 1) {
                        if (connect[thisIsland][map[r + moveR[d] * length + 1][c]] && connect[map[r + moveR[d] * length + 1][c]][thisIsland]) continue;
                    } else if (d == 2) {
                        if (connect[thisIsland][map[r][c + moveC[d] * length - 1]] && connect[map[r][c + moveC[d] * length - 1]][thisIsland]) continue;
                    } else {
                        if (connect[thisIsland][map[r][c + moveC[d] * length + 1]] && connect[map[r][c + moveC[d] * length + 1]][thisIsland]) continue;
                    }
                    // 현재 섬 - 건너 섬 연결
                    connectionProc(thisIsland, r, c, d, length, true);
                    dfs(i + 1, bridgeLength + length);
                    // 현재 섬 - 건너 섬 연결 해제
                    connectionProc(thisIsland, r, c, d, length, false);
                }
            }
        }
    }

    private static void connectionProc(int thisIsland, int r, int c, int d, int length, boolean conn) {
        if (d == 0) {
            connect[thisIsland][map[r + moveR[d] * length - 1][c]] = conn;
            connect[map[r + moveR[d] * length - 1][c]][thisIsland] = conn;
        }
        else if (d == 1) {
            connect[thisIsland][map[r + moveR[d] * length + 1][c]] = conn;
            connect[map[r + moveR[d] * length + 1][c]][thisIsland] = conn;
        }
        else if (d == 2) {
            connect[thisIsland][map[r][c + moveC[d] * length - 1]] = conn;
            connect[map[r][c + moveC[d] * length - 1]][thisIsland] = conn;
        }
        else {
            connect[thisIsland][map[r][c + moveC[d] * length + 1]] = conn;
            connect[map[r][c + moveC[d] * length + 1]][thisIsland] = conn;
        }
    }

    private static int distance(int r, int c, int d) {
        int thisIsland = map[r][c];
        int nr = r;
        int nc = c;
        int distance;
        int maxDistance;
        if (d == 0 || d == 1) maxDistance = N - 1;
        else maxDistance = M - 1;
        // 다른 섬과 이어지고 이어졌을 때 다리 길이가 2 이상이면 true
        for (distance = 1; distance <= maxDistance; distance++) {
            nr = nr + moveR[d];
            nc = nc + moveC[d];
            if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == thisIsland) return 0;
            if (map[nr][nc] > 0) break;
        }

        return distance <= maxDistance ? distance - 1 : 0;
    }

    private static boolean allIslandConnected() {
        boolean[] check = new boolean[island + 1];
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        check[1] = true;
        while (linkedList.size() != 0) {
            int tmpIsland = linkedList.remove();
            for (int i = 1; i <= island; i++) {
                if (connect[tmpIsland][i] && !check[i]) {
                    check[i] = true;
                    linkedList.add(i);
                }
            }
        }

        for (int i = 1; i <= island; i++) {
            if (!check[i]) return false;
        }
        return true;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        grouping();
        connect = new boolean[island + 1][island + 1];
    }

    private static void grouping() {
        boolean[][] visit = new boolean[N][M];

        // (r, c) 가 1번 섬이면 map[r][c]가 1이 되도록
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (visit[r][c]) continue;
                if (map[r][c] == 1) {
                    ++island;
                    mark(r, c, island, visit);
                }
            }
        }
    }

    private static void mark(int r, int c, int island, boolean[][] visit) {
        map[r][c] = island;
        visit[r][c] = true;
        for (int d = 0; d < 4; d++) {
            int nr = r + moveR[d];
            int nc = c + moveC[d];
            if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
            if (map[nr][nc] == 1 && !visit[nr][nc]) mark(nr, nc, island, visit);
        }
    }
}
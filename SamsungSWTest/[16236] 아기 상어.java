import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[][] map;
    static int[][] time;
    static int[] pos = {0, 0};
    static int[] moveR = {-1, 0, 0, 1};
    static int[] moveC = {0, -1, 1, 0};
    static int size = 2;
    static int eat;
    static boolean end;
    static final int INF = 99999999;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        time = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    pos[0] = i;
                    pos[1] = j;
                    map[i][j] = 0;
                }
            }
        }

        int result = 0;
        while (true) {
            for (int i = 0; i < N; i++) {
                Arrays.fill(time[i], INF);
            }
            time[pos[0]][pos[1]] = 0;
            bfs();
            result += time[pos[0]][pos[1]];
            if (end) break;
        }

        System.out.println(result);
    }

    private static void bfs() {
        PriorityQueue<Fish> queue = new PriorityQueue<>();
        LinkedList<int[]> linkedList = new LinkedList<>();
        linkedList.add(pos);
        time[pos[0]][pos[1]] = 0;

        while (linkedList.size() != 0) {
            int[] tmpPos = linkedList.remove();
            int r = tmpPos[0];
            int c = tmpPos[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + moveR[d];
                int nc = c + moveC[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (time[nr][nc] != INF) continue;
                if (map[nr][nc] > size) continue;

                linkedList.add(new int[]{nr, nc});
                time[nr][nc] = time[r][c] + 1;
                if (map[nr][nc] > 0 && map[nr][nc] < size) {
                    queue.add(new Fish(nr, nc, map[nr][nc], time[nr][nc]));
                }
            }
        }

        if (queue.size() == 0) end = true;
        else {
            Fish fish = queue.remove();
            int r = fish.r;
            int c = fish.c;
            eat++;
            pos[0] = r;
            pos[1] = c;
            map[r][c] = 0;
            if (eat == size) {
                size++;
                eat = 0;
            }
        }
    }

    static class Fish implements Comparable<Fish> {
        int r, c, size, time;

        public Fish(int r, int c, int size, int time) {
            this.r = r;
            this.c = c;
            this.size = size;
            this.time = time;
        }

        @Override
        public int compareTo(Fish fish) {
            if (time - fish.time > 0) {
                return 1;
            }
            else {
                if (r - fish.r > 0) return 1;
                else if (r == fish.r) {
                    if (c - fish.c > 0) return 1;
                    else if (c == fish.c) return 0;
                    else return -1;
                }
                else return -1;
            }
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int R;
    private static int C;
    private static char[][] map;
    private static final int INF = Integer.MAX_VALUE;
    private static int[][] min;
    private static int[] moveR = {-1, 0, 1, 0};
    private static int[] moveC = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        min = new int[R][C];
        map = new char[R][C];
        List<int[]> waterList = new ArrayList<>();
        int[] start = {0, 0};

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                min[i][j] = INF;
                if (map[i][j] == '*') {
                    min[i][j] = 0;
                    waterList.add(new int[]{i, j});
                }
                else if (map[i][j] == 'S') {
                    min[i][j] = 0;
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        // 물에 대해서 BFS
        for (int[] water : waterList) {
            bfs(water, '*');
        }

        // 고슴도치에 대해서 BFS
        bfs(start, 'S');
    }

    public static void bfs(int[] location, char object) {
        LinkedList<int[]> linkedList = new LinkedList<>();
        linkedList.add(location);

        while (linkedList.size() != 0) {
            location = linkedList.poll();
            int r = location[0];
            int c = location[1];
            if (map[r][c] == 'D') {
                if (object == 'S') {
                    System.out.println(min[r][c]);
                    return;
                }
            }

            // min : 해당 좌표에 물이나 고슴도치가 가장 빠르게 도달할 수 있는 시간
            // 물에 대해 먼저 BFS 진행하여 min 을 저장,
            // 고슴도치에 대해 BFS 진행 시 물에서 했던 min 보다 작은 값이 나오면 해당 좌표에 더 빨리 도착할 수 있음
            for (int i = 0; i < 4; i++) {
                if (r + moveR[i] < 0 || r + moveR[i] >= R || c + moveC[i] < 0 || c + moveC[i] >= C) continue;
                if (map[r + moveR[i]][c + moveC[i]] == 'X') continue;
                if (min[r + moveR[i]][c + moveC[i]] > min[r][c] + 1) {
                    if (map[r + moveR[i]][c + moveC[i]] == 'D' && object == '*') continue;
                    min[r + moveR[i]][c + moveC[i]] = min[r][c] + 1;
                    linkedList.add(new int[]{r + moveR[i], c + moveC[i]});
                }
            }
        }

        if (object == 'S') {
            System.out.println("KAKTUS");
        }
    }
}
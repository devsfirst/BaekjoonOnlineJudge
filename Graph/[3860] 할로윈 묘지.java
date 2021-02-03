import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static ArrayList<Edge> edges;
    static int[][] dist;
    static final int INF = Integer.MAX_VALUE;
    static int[] moveR = {-1, 0, 1, 0};
    static int[] moveC = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            if (W == 0 && H == 0) break;

            int[][] map = new int[W][H];
            edges = new ArrayList<>();
            dist = new int[W][H];
            for (int i = 0; i < W; i++) {
                for (int j = 0; j < H; j++) {
                    dist[i][j] = INF;
                }
            }
            dist[0][0] = 0;

            int G = Integer.parseInt(br.readLine());
            for (int i = 0; i < G; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                // 묘비 -> 이동 불가
                map[x][y] = -1;
            }

            int E = Integer.parseInt(br.readLine());
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new Edge(x1, y1, x2, y2, T));
                // 귀신 구멍 -> (x2, y2) 로만 가야 함
                map[x1][y1] = 1;
            }

            for (int i = 0; i < W; i++) {
                for (int j = 0; j < H; j++) {
                    // 묘비
                    if (map[i][j] == - 1) continue;
                    // 귀신 구멍
                    if (map[i][j] == 1) continue;
                    // 끝 지점
                    if (i == W - 1 && j == H - 1) continue;

                    for (int k = 0; k < 4; k++) {
                        int newR = i + moveR[k];
                        int newC = j + moveC[k];
                        if (newR < 0 || newR >= W || newC < 0 || newC >= H) continue;
                        if (map[newR][newC] == -1) continue;
                        edges.add(new Edge(i, j, newR, newC, 1));
                    }
                }
            }

            for (int i = 0; i < W * H - 1; i++) {
                for (Edge edge : edges) {
                    if (dist[edge.fromX][edge.fromY] == INF) continue;
                    if (dist[edge.toX][edge.toY] > dist[edge.fromX][edge.fromY] + edge.time) {
                        dist[edge.toX][edge.toY] = dist[edge.fromX][edge.fromY] + edge.time;
                    }
                }
            }

            boolean isCycle = false;
            for (int i = 0; i < W * H - 1; i++) {
                for (Edge edge : edges) {
                    if (dist[edge.fromX][edge.fromY] == INF) continue;
                    if (dist[edge.toX][edge.toY] > dist[edge.fromX][edge.fromY] + edge.time) {
                        isCycle = true;
                    }
                }
            }

            if (isCycle) System.out.println("Never");
            else if (dist[W - 1][H - 1] == INF) System.out.println("Impossible");
            else System.out.println(dist[W - 1][H - 1]);
        }
    }

    static class Edge {
        int fromX, fromY;
        int toX, toY;
        int time;

        public Edge(int fromX, int fromY, int toX, int toY, int time) {
            this.fromX = fromX;
            this.fromY = fromY;
            this.toX = toX;
            this.toY = toY;
            this.time = time;
        }
    }
}
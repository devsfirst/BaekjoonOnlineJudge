import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static boolean[][] visit;
    static List<Home> homes = new ArrayList<>();
    static List<Chicken> chickens = new ArrayList<>();
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N + 1][N + 1];
        visit = new boolean[N + 1][N + 1];


        for (int i = 1; i < N + 1; i++) {
            String s = br.readLine();
            for (int j = 1; j <= N; j++) {
                map[i][j] = s.charAt((j - 1) * 2) - '0';
                if (map[i][j] == 1) {
                    homes.add(new Home(i, j));
                } else if (map[i][j] == 2) {
                    chickens.add(new Chicken(i, j));
                }
            }
        }

        dfs(0, 0);

        System.out.println(min);
    }

    private static void dfs(int index, int chickenNum) {
        if (chickenNum == M) {
            int sum = 0;
            for (Home home : homes) {
                int minDist = 99999999;
                for (Chicken chicken : chickens) {
                    if (visit[chicken.getRow()][chicken.getCol()]) {
                        int dist = Math.abs(chicken.getRow() - home.getRow()) + Math.abs(chicken.getCol() - home.getCol());
                        if (dist < minDist) {
                            minDist = dist;
                        }
                    }
                }
                sum += minDist;
            }
            min = Math.min(min, sum);
            return;
        }

        for (int i = index; i < chickens.size(); i++) {
            Chicken chicken = chickens.get(i);
            int chickenRow = chicken.getRow();
            int chickenCol = chicken.getCol();
            if (!visit[chickenRow][chickenCol]) {
                visit[chickenRow][chickenCol] = true;
                dfs(i + 1,chickenNum + 1);
                visit[chickenRow][chickenCol] = false;
            }
        }
    }

    public static class Home {
        private final int row;
        private final int col;

        public Home(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }

    public static class Chicken {
        private int row;
        private int col;

        public Chicken(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
}
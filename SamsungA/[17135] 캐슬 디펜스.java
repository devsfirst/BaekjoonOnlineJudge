import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static int N, M, D;
    static int[][] initMap;
    static int[] archer = new int[4];
    static List<Enemy> enemies = new ArrayList<>();
    static int totalEnemy;
    static int maxKill;

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 1; i <= M - 2; i++) {
            dfs(1, i);
        }
        System.out.println(maxKill);
    }

    private static void dfs(int archerNumber, int col) {
        archer[archerNumber] = col;
        // 성을 적에게서 지키기 위해 궁수 3명을 배치하려고 한다.
        // 궁수는 성이 있는 칸에 배치할 수 있고, 하나의 칸에는 최대 1명의 궁수만 있을 수 있다.
        if (archerNumber == 3) {
            simulate();
            return;
        }

        for (int i = col + 1; i <= M; i++) {
            dfs(archerNumber + 1, i);
        }
    }

    private static void simulate() {
        int kill = 0;
        List<Enemy> newEnemies = new ArrayList<>();
        for (Enemy enemy : enemies) {
            newEnemies.add(new Enemy(enemy.r, enemy.c));
        }

        // 모든 적이 격자판에서 제외되면 게임이 끝난다.
        while (newEnemies.size() != 0) {
            // 각각의 턴마다 궁수는 적 하나를 공격할 수 있고, 모든 궁수는 동시에 공격한다.
            kill += attack(newEnemies);
            // 궁수의 공격이 끝나면, 적이 이동한다.
            move(newEnemies);
        }
        maxKill = Math.max(maxKill, kill);
    }

    private static void move(List<Enemy> enemyList) {
        List<Enemy> deadEnemies = new ArrayList<>();
        // 적은 아래로 한 칸 이동하며, 성이 있는 칸으로 이동한 경우에는 게임에서 제외된다.
        for (Enemy enemy : enemyList) {
            if (enemy.r == N) deadEnemies.add(enemy);
            else enemy.r++;
        }

        for (Enemy deadEnemy : deadEnemies) {
            enemyList.remove(deadEnemy);
        }
    }

    private static int attack(List<Enemy> enemyList) {
        int kill = 0;
        int archerR = N + 1;
        List<Enemy> deadEnemies = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            int archerC = archer[i];
            Enemy minDistEnemy = enemyList.get(0);
            int minDist = (archerR - minDistEnemy.r) + Math.abs(archerC - minDistEnemy.c);

            for (int j = 1; j < enemyList.size(); j++) {
                Enemy getEnemy = enemyList.get(j);
                int dist = (archerR - getEnemy.r) + Math.abs(archerC - getEnemy.c);
                // 궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고,
                // 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격한다.
                if (dist <= minDist) {
                    if (dist == minDist) getEnemy = minDistEnemy.c < getEnemy.c ? minDistEnemy : getEnemy;
                    minDist = dist;
                    minDistEnemy = getEnemy;
                }
            }

            if (minDist <= D && !deadEnemies.contains(minDistEnemy)) deadEnemies.add(minDistEnemy);
        }
        // 같은 적이 여러 궁수에게 공격당할 수 있다. 공격받은 적은 게임에서 제외된다.
        for (Enemy deadEnemy : deadEnemies) {
            enemyList.remove(deadEnemy);
            kill++;
        }

        return kill;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        initMap = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                initMap[i][j] = Integer.parseInt(st.nextToken());
                if (initMap[i][j] == 1) {
                    totalEnemy++;
                    enemies.add(new Enemy(i, j));
                }
            }
        }
    }

    static class Enemy {
        int r, c;

        public Enemy(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
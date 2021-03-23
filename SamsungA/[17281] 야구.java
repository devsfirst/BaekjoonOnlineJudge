import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N;
    static int[][] hitResult;
    static int[] lineup = new int[10];
    static boolean[] add = new boolean[10];
    static int currentHitter;
    static int maxScore;

    public static void main(String[] args) throws IOException {
        init();

        // 아인타는 자신이 가장 좋아하는 선수인 1번 선수를 4번 타자로 미리 결정했다.
        add[1] = true;
        lineup[4] = 1;
        dfs(1);

        System.out.println(maxScore);
    }

    private static void dfs(int lineupPosition) {
        if (lineupPosition == 10) {
            simulate();
            return;
        }

        if (lineupPosition == 4) lineupPosition++;

        // i 번 선수
        for (int i = 2; i <= 9; i++) {
            if (!add[i]) {
                add[i] = true;
                lineup[lineupPosition] = i;
                dfs(lineupPosition + 1);
                add[i] = false;
            }
        }
    }

    private static void simulate() {
        int score = 0;
        currentHitter = 1;
        // N이닝
        for (int inning = 1; inning <= N; inning++) {
            score += attack(inning);
        }
        maxScore = Math.max(maxScore, score);
    }

    private static int attack(int inning) {
        int out = 0;
        int score = 0;
        boolean[] base = new boolean[4];
        while (out != 3) {
            int hitter = lineup[currentHitter];
            int result = hitResult[inning][hitter];
            switch (result) {
                case 0:
                    out++;
                    break;
                case 1:
                    if (base[3]) score++;
                    base[3] = base[2];
                    base[2] = base[1];
                    base[1] = true;
                    break;
                case 2:
                    if (base[3]) score++;
                    if (base[2]) score++;
                    base[3] = base[1];
                    base[2] = true;
                    base[1] = false;
                    break;
                case 3:
                    if (base[3]) score++;
                    if (base[2]) score++;
                    if (base[1]) score++;
                    base[3] = true;
                    base[2] = false;
                    base[1] = false;
                    break;
                case 4:
                    score++;
                    if (base[3]) score++;
                    if (base[2]) score++;
                    if (base[1]) score++;
                    base[3] = false;
                    base[2] = false;
                    base[1] = false;
                    break;
            }
            currentHitter++;
            if (currentHitter > 9) currentHitter = 1;
        }
        return score;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        hitResult = new int[N + 1][10];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                hitResult[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
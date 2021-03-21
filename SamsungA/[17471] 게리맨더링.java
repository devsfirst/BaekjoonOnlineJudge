import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

    static int N;
    static int totalPopulation;
    static int[] population;
    static int[] group;
    static ArrayList<Integer>[] neighborList;
    static int diff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();

        dfs(1);

        if (diff == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(diff);
    }

    private static void dfs(int area) {

        if (area == N + 1) {
            if (isTwoSet()) {
                int sum1 = 0, sum2 = 0;
                for (int i = 1; i <= N; i++) {
                    if (group[i] == 1) sum1 += population[i];
                    else sum2 += population[i];
                }
                diff = Math.min(diff, Math.abs(sum1 - sum2));
            }
            return;
        }

        // 1번 선거구에 포함하여 검사
        group[area] = 1;
        dfs(area + 1);
        // 2번 선거구에 포함하여 검사
        group[area] = 2;
        dfs(area + 1);
    }

    private static boolean isTwoSet() {
        boolean[] visit = new boolean[N + 1];
        int groupCount = 0;
        for (int i = 1; i <= N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                groupCount++;
                findSameSet(i, group[i], visit);
            }
        }

        return groupCount == 2;
    }

    private static void findSameSet(int i, int groupNum, boolean[] visit) {
        for (Integer neighbor: neighborList[i]) {
            if (group[neighbor] == groupNum && !visit[neighbor]) {
                visit[neighbor] = true;
                findSameSet(neighbor, groupNum, visit);
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        group = new int[N + 1];
        neighborList = new ArrayList[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            totalPopulation += population[i];
            neighborList[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int neighbor = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= neighbor; j++) {
                neighborList[i].add(Integer.parseInt(st.nextToken()));
            }
        }
    }
}
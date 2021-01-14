import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static List<Integer> listA = new ArrayList<>();
    private static List<Integer> listB = new ArrayList<>();
    private static int N;
    private static int[][] graph;
    private static boolean[] visit;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N / 2; i++) {
            visit = new boolean[N + 1];
            search(i);
            listA.clear();
        }

        System.out.println(min);
    }

    private static void search(int index) {
        listA.add(index);
        visit[index] = true;

        if (listA.size() != N / 2) {
            for (int i = index + 1; i <= N; i++) {
                if (!visit[i]) {
                    search(i);
                }
            }
        } else {
            int sumA = 0;
            int sumB = 0;

            for (int i = 1; i <= N; i++) {
                if (!visit[i]) {
                    listB.add(i);
                }
            }

            for (int i = 0; i < N / 2; i++) {
                for (int j = 0; j < N / 2; j++) {
                    sumA += graph[listA.get(i)][listA.get(j)];
                    sumB += graph[listB.get(i)][listB.get(j)];
                }
            }

            min = Math.min(Math.abs(sumA - sumB), min);

            listB.clear();
        }
        listA.remove(listA.size() - 1);
        visit[index] = false;
    }
}

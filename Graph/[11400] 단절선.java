import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int V, E;
    private static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    private static int[] visitNum;
    private static boolean[] visit;
    private static int dfsVisitNum;
    private static ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        visitNum = new int[V + 1];
        visit = new boolean[V + 1];

        for (int i = 0; i <= V; i++) {
            adjList.add(new ArrayList<>());
            result.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        dfs(1, 0);

        int num = 0;
        for (int i = 1; i <= V; i++) {
            num += result.get(i).size();
        }
        System.out.println(num);
        for (int i = 1; i <= V; i++) {
            Collections.sort(result.get(i));
            for (Integer integer : result.get(i)) {
                System.out.println(i + " " + integer);
            }
        }
    }

    private static int dfs(int node, int prev) {
        visit[node] = true;
        visitNum[node] = ++dfsVisitNum;

        int ret = visitNum[node];

        for (Integer next : adjList.get(node)) {
            // node와 부모가 이어져 있을 때는 건너뛰어야 함
            if (next == prev) continue;

            // 다른 경로를 통해 이미 방문했으면 visitNum 검사
            if (visit[next]) {
                ret = Math.min(ret, visitNum[next]);
                continue;
            }

            // 다음의 visitNum이 node의 visitNum보다 크면 이 둘 사이의 간선이 단절선
            int nextMin = dfs(next, node);
            if (nextMin > visitNum[node]) {
                result.get(Math.min(node, next)).add(Math.max(node, next));
            }

            // child를 통해 가져온 가장 낮은 visitNum과 node의 visitNum 중 최솟값 선택
            ret = Math.min(ret, nextMin);
        }

        return ret;
    }
}
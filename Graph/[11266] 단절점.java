import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int V, E;
    private static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    private static int[] visitNum;
    private static boolean[] visit;
    private static int dfsVisitNum;
    private static boolean[] result;
    private static int resultNum;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        visitNum = new int[V + 1];
        visit = new boolean[V + 1];
        result = new boolean[V + 1];

        for (int i = 0; i <= V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        for (int i = 1; i <= V; i++) {
            // 연결되지 않은 그래프가 있으면 dfs를 여러 번 실행
            dfsVisitNum = 0;
            if (!visit[i]) {
                dfs(i);
            }
        }

        System.out.println(resultNum);
        for (int i = 1; i <= V; i++) {
            if (result[i]) System.out.print(i + " ");
        }
    }

    private static int dfs(int node) {
        visit[node] = true;
        visitNum[node] = ++dfsVisitNum;
        boolean isRoot = dfsVisitNum == 1;

        int ret = visitNum[node];
        int child = 0;

        for (Integer next :adjList.get(node)){
            // 다른 경로를 통해 이미 방문했으면 visitNum 검사
            if (visit[next]){
                ret = Math.min(ret, visitNum[next]);
                continue;
            }

            // 다른 경로를 통해 방문하지 않았으면 child이고
            // child를 통해 dfs하여 가장 낮은 visitNum을 가져옴
            child++;
            int nextMin = dfs(next);

            // node가 root가 아닌 경우 -> node를 통해 dfs한 결과 node보다 visitNum이 낮은 곳으로 못가면 단절점
            if (!isRoot && !(nextMin < visitNum[node])) {
                if (!result[node]) {
                    result[node] = true;
                    resultNum++;
                }
            }

            // child를 통해 가져온 가장 낮은 visitNum과 node의 visitNum 중 최솟값 선택
            ret = Math.min(ret, nextMin);
        }

        // node가 root인 경우 -> 자식이 둘 이상이면 단절점
        if (isRoot) {
            if (child >= 2) {
                if (!result[node]) {
                    result[node] = true;
                    resultNum++;
                }
            }
        }

        return ret;
    }
}
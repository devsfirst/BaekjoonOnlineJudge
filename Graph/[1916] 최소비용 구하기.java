import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static int N, M;
    static int[] cost;
    static List<Edge>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        cost = new int[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(cost, 99999999);
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[s].add(new Edge(d, c));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int source = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        bfs(source);

        System.out.println(cost[dest]);
    }

    private static void bfs(int source) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(source);
        cost[source] = 0;
        while (linkedList.size() != 0) {
            int s = linkedList.remove();
            int sourceCost = cost[s];
            for (Edge edge : list[s]) {
                int d = edge.d;
                int destCost = edge.c;
                if (cost[d] > sourceCost + destCost) {
                    cost[d] = sourceCost + destCost;
                    linkedList.add(d);
                }
            }
        }
    }

    static class Edge {
        int d, c;

        public Edge(int d, int c) {
            this.d = d;
            this.c = c;
        }
    }
}
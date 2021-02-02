import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int V, E, start;
    private static PriorityQueue<Node> queue = new PriorityQueue<>();
    private static ArrayList<ArrayList<Node>> connection = new ArrayList<>();
    private static int[] distance;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());
        distance = new int[V + 1];
        visit = new boolean[V + 1];
        final int INF = 9999999;

        for (int i = 0; i <= V; i++) {
            connection.add(new ArrayList<>());
            distance[i] = INF;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            connection.get(u).add(new Node(v, w));
        }

        distance[start] = 0;
        queue.offer(new Node(start, 0));
        while (queue.size() != 0) {
            Node node = queue.poll();
            int nodeNum = node.getV();

            if (visit[nodeNum]) continue;
            visit[nodeNum] = true;

            for (Node next : connection.get(nodeNum)) {
                if (distance[next.getV()] > distance[nodeNum] + next.getCost()) {
                    distance[next.getV()] = distance[nodeNum] + next.getCost();
                    queue.offer(new Node(next.getV(), distance[next.getV()]));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (distance[i] == INF) System.out.println("INF");
            else System.out.println(distance[i]);
        }
    }

    private static class Node implements Comparable<Node> {
        private final int v;
        private final int cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        public int getV() {
            return v;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(Node node) {
            return cost - node.getCost();
        }
    }
}
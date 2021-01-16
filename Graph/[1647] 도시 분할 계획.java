import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static PriorityQueue<Node> road = new PriorityQueue<>();
    private static int[] root;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        root = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            root[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            road.offer(new Node(a, b, c));
        }

        int result = 0;
        int maxCost = 0;
        while (road.size() != 0) {
            Node n = road.poll();
            int a = n.getA();
            int b = n.getB();
            int cost = n.getCost();

            if (find(a) != find(b)) {
                union(a, b);
                result += cost;
                maxCost = cost;
            }
        }

        System.out.println(result - maxCost);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) root[b] = a;
        else root[a] = b;
    }

    private static int find(int r) {
        if (r != root[r]) root[r] = find(root[r]);
        return root[r];
    }

    public static class Node implements Comparable<Node> {
        private int a;
        private int b;
        private int cost;

        public Node(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(Node node) {
            return this.cost - node.getCost();
        }
    }
}
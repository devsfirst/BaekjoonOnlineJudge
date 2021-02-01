import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int[] set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        set = new int[N + 1];
        Edge[] edges = new Edge[M];
        int cost = 0;

        for (int i = 1; i <= N; i++) {
            set[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(a, b, c);
        }
        Arrays.sort(edges);

        for (int i = 0; i < M; i++) {
            int a = edges[i].getA();
            int b = edges[i].getB();
            int c = edges[i].getC();

            if (find(a) != find(b)) {
                union(a, b);
                cost += c;
            }
        }

        System.out.println(cost);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) set[b] = a;
        else set[a] = b;
    }

    private static int find(int i) {
        if (set[i] == i) return i;
        set[i] = find(set[i]);
        return set[i];
    }

    private static class Edge implements Comparable<Edge> {
        private final int a;
        private final int b;
        private final int c;

        public Edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int getC() {
            return c;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.c - edge.getC();
        }
    }
}
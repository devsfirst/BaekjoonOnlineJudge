import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        set = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            set[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int opt = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (opt == 0) {
                union(a, b);
            } else {
                if (isSameSet(a, b)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
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

    private static boolean isSameSet(int a, int b) {
        return find(a) == find(b);
    }
}
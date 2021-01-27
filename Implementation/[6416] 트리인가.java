import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int[] indegree = new int[20];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int test = 1;
        int u, v;
        boolean esc = false;
        boolean check = false;
        Arrays.fill(indegree, -1);
        while (true) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());

                if (u == -1 && v == -1) {
                    esc = true;
                    break;
                }

                if (u == 0 && v == 0) {
                    if (isTree()) System.out.println("Case " + test + " is a tree.");
                    else System.out.println("Case " + test + " is not a tree.");
                    check = true;
                    break;
                } else {
                    // u가 루트일 수 있으니 -1이면 0으로
                    if (indegree[u] < 0) indegree[u] = 0;
                    if (indegree[v] < 0) indegree[v] = 0;
                    indegree[v]++;
                }
            }
            if (esc) break;

            if (check) {
                test++;
                Arrays.fill(indegree, -1);
                check = false;
            }
        }
    }

    private static boolean isTree() {
        int zero = 0;
        int node = 0;
        int degree = 0;
        for (int i = 0; i < 20; i++) {
            // 루트는 indegree가 0
            if (indegree[i] == 0) {
                node++;
                zero++;
                if (zero > 1) {
                    return false;
                }
            }
            else if (indegree[i] == 1) {
                node++;
                degree++;
                // indegree가 1보다 크면 유일 경로 X
            } else if (indegree[i] > 1) {
                return false;
            }
        }
        // 노드 개수 0인 트리
        if (node == 0) return true;

        // degree + 1 == node가 아니면 유일 경로가 없을 수 있음
        return degree + 1 == node;
    }
}
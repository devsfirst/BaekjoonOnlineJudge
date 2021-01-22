import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int[] prime = new int[10000];
    static int[] visit = new int[10000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        // 0 : 소수
        // 1 : 소수X
        for (int i = 2; i < 10000; i++) {
            if (prime[i] == 1) continue;
            for (int j = 2 * i; j < 10000; j += i) {
                prime[j] = 1;
            }
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            bfs(A, B);
            Arrays.fill(visit, 0);
        }
    }

    private static void bfs(int A, int B) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.offer(A);
        visit[A] = 1;

        while (linkedList.size() != 0) {
            A = linkedList.poll();
            if (A == B) {
                System.out.println(visit[A] - 1);
                return;
            }

            for (int i = 1; i <= 4; i++) {
                for (int j = 0; j < 10; j++) {
                    if (i == 4 && j == 0) continue;

                    String s = "" + A;
                    int nextA = Integer.parseInt(s.substring(0, 4 - i) + j + s.substring(5 - i));

                    if (prime[nextA] == 0) {
                        if (visit[nextA] == 0 || visit[nextA] > visit[A] + 1) {
                            linkedList.offer(nextA);
                            visit[nextA] = visit[A] + 1;
                        }
                    }
                }
            }
        }
        System.out.println("Impossible");
    }
}
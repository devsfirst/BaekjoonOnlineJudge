import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int pair = Integer.parseInt(br.readLine());
        boolean[][] connection = new boolean[N + 1][N + 1];
        boolean[] visit = new boolean[N + 1];
        int com1, com2;
        int virus = 0;
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < pair; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            com1 = Integer.parseInt(st.nextToken());
            com2 = Integer.parseInt(st.nextToken());
            connection[com1][com2] = connection[com2][com1] = true;
        }

        linkedList.add(1);
        visit[1] = true;
        while (linkedList.size() != 0) {
            int com = linkedList.remove();
            virus++;
            for (int infected = 1; infected <= N; infected++) {
                if (connection[com][infected] && !visit[infected]) {
                    visit[infected] = true;
                    linkedList.add(infected);
                }
            }
        }

        System.out.println(virus - 1);
    }
}

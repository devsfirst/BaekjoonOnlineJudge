import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        final int BELT_SIZE = N * 2;
        boolean[] robots = new boolean[N + 1];
        LinkedList<Integer> durability = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < BELT_SIZE; i++) {
            durability.offer(Integer.parseInt(st.nextToken()));
        }

        int level = 0;
        while (true) {
            level++;

            // 벨트 회전
            durability.offerFirst(durability.pollLast());
            for (int i = N - 1; i > 0; i--) {
                if (robots[i - 1]) {
                    robots[i] = true;
                    robots[i - 1] = false;
                }
            }
            robots[N - 1] = false;

            for (int i = N - 1; i > 0; i--) {
                // 이전 칸에 로봇이 있고 다음 칸에 로봇X, 내구도 1 이상이면 로봇 옮김
                if (robots[i - 1] && !robots[i] && durability.get(i) > 0) {
                        robots[i - 1] = false;
                        robots[i] = true;
                        durability.set(i, durability.get(i) - 1);
                }
            }

            // 올라가는 위치에 로봇이 비었고 내구도가 0이 아니면 로봇 올림
            if (!robots[0] && durability.get(0) > 0) {
                robots[0] = true;
                durability.set(0, durability.get(0) - 1);
            }

            int num = 0;
            for (Integer d : durability) {
                if (d <= 0) num++;
            }
            if (num >= K) break;
        }

        System.out.println(level);
    }
}

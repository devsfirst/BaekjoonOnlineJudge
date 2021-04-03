import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Schedule> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            queue.add(new Schedule(S, E));
        }

        boolean[][] calendar = new boolean[N + 1][366];
        while (queue.size() != 0) {
            Schedule schedule = queue.remove();
            int S = schedule.S;
            int E = schedule.E;
            for (int i = 1; i <= N; i++) {
                if (calendar[i][S]) continue;
                for (int j = S; j <= E; j++) {
                    calendar[i][j] = true;
                }
                break;
            }
        }

        int w = 0, h = 0, result = 0;
        boolean continuous = false;
        for (int c = 1; c <= 365; c++) {
            continuous = false;
            for (int r = 1; r <= N; r++) {
                if (calendar[r][c]) {
                    continuous = true;
                    h = Math.max(h, r);
                }
            }

            if (continuous) w++;
            else {
                result += w * h;
                w = 0;
                h = 0;
            }
        }
        if (continuous) result += w * h;

        System.out.println(result);
    }

    static class Schedule implements Comparable<Schedule> {
        int S, E;

        public Schedule(int s, int e) {
            S = s;
            E = e;
        }

        @Override
        public int compareTo(Schedule node) {
            if (S == node.S) return node.E - E;
            return S - node.S;
        }
    }
}
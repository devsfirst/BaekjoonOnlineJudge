import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, L, R;
    static int[][] A;
    static int[] moveR = {-1, 0, 1, 0};
    static int[] moveC = {0, 1, 0, -1};
    static boolean[][] add;
    static boolean isMoved;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        add = new boolean[N][N];
        int move = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            isMoved = false;

            // 아직 연합이 되지 않은 좌표에서 bfs 시작
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!add[i][j]) {
                        bfs(i, j);
                    }
                }
            }

            // 연합이 되지 않으면 반복 종료
            if (!isMoved) break;

            move++;
            for (int i = 0; i < N; i++) {
                Arrays.fill(add[i], false);
            }
        }

        System.out.println(move);
    }

    private static void bfs(int i, int j) {
        // (i, j)에서 bfs를 시작했을 때 연합이 되는 좌표를 담는 ArrayList
        ArrayList<Integer> arrayList = new ArrayList<>();

        // bfs에 사용할 LinkedList
        LinkedList<Integer> linkedList = new LinkedList<>();
        // 2차원 좌표를 1차원으로 저장
        linkedList.add(i * N + j);
        int sum = 0;
        int num = 0;

        while (linkedList.size() != 0) {
            int tmp = linkedList.remove();
            int r = tmp / N;
            int c = tmp % N;

            // 북, 동, 남, 서 방향으로 확인
            for (int d = 0; d < 4; d++) {
                // 배열 범위 검사
                if (r + moveR[d] >= 0 && r + moveR[d] < N && c + moveC[d] >= 0 && c + moveC[d] < N) {
                    // 아직 더해지지 않은 곳과 인구수 차이가 L ~ R 사이에 있는지 검사
                    if (!add[r + moveR[d]][c + moveC[d]]) {
                        int diff = Math.abs(A[r][c] - A[r + moveR[d]][c + moveC[d]]);
                        if (L <= diff && diff <= R) {
                            // 기준이 되는 좌표가 연합에 더해지지 않은경우
                            if (!add[r][c]) {
                                add[r][c] = true;
                                sum += A[r][c];
                                num++;
                                arrayList.add(r * N + c);
                            }
                            // 기준으로부터 d 방향에 해당하는 곳을 연합에 더해줌
                            add[r + moveR[d]][c + moveC[d]] = true;
                            sum += A[r + moveR[d]][c + moveC[d]];
                            num++;
                            linkedList.add((r + moveR[d]) * N + (c + moveC[d]));
                            arrayList.add((r + moveR[d]) * N + (c + moveC[d]));
                        }
                    }
                }
            }
        }

        // 연합이 생긴 경우
        if (num > 0) {
            isMoved = true;
            int population = sum / num;
            // arrayList에는 (i, j)를 기준으로 연합이 된 좌표가 들어있음
            while (arrayList.size() != 0) {
                int tmp = arrayList.remove(0);
                int r = tmp / N;
                int c = tmp % N;
                A[r][c] = population;
            }
        }
    }
}
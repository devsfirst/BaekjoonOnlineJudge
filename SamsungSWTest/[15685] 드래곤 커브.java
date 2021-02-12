import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static boolean[][] visit = new boolean[101][101];
    static ArrayList<Integer> directions;
    static int[] moveX = {1, 0, -1, 0};
    static int[] moveY = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            directions = new ArrayList<>();
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            // 0세대
            directions.add(d);
            int startX = x, startY = y;
            int endX = x + moveX[d], endY = y + moveY[d];
            visit[startX][startY] = true;
            visit[endX][endY] = true;

            // 1세대 시작
            for (int j = 1; j <= g; j++) {
                // j 세대에서 새로 추가할 커브는 directions의 (2^(j-1) - 1) 인덱스부터 index 0까지
                // 이번 세대에서 추가할 커브의 개수는 이전 세대의 커브 개수와 같음
                // 이번 세대에서 추가할 각 커브는 이전 세대까지 만들었던 각 커브의 방향에 + 1을 한 방향으로 만듦
                for (int index = (int) Math.pow(2, j - 1) - 1; index >= 0; index--) {
                    // 새로 추가되는 커브의 방향
                    int dir = (directions.get(index) + 1) % 4;
                    startX = endX;
                    startY = endY;
                    // 새로 추가한 한 커브의 끝
                    endX = startX + moveX[dir];
                    endY = startY + moveY[dir];
                    visit[endX][endY] = true;
                    directions.add(dir);
                }
            }
        }

        int result = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (visit[i][j] && visit[i + 1][j] && visit[i][j + 1] && visit[i + 1][j + 1]) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }
}
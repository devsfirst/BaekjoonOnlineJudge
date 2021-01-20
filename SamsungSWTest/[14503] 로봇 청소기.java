import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        boolean[][] clean = new boolean[N][M];
        int[] backR = {1, 0, -1, 0};
        int[] backC = {0, -1, 0, 1};

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j * 2) - '0';
            }
        }

        int result = 0;

        while (true) {
            // 현재 위치 청소
            if (!clean[r][c]) {
                clean[r][c] = true;
                result++;
            }

            boolean findNotClean = false;
            // 현재 방향 기준, 왼쪽 방향부터 4방향 탐색
            for (int i = 0; i < 4; i++) {
                if (findNotClean) break;
                switch (direction) {
                    case 0:
                        if (c - 1 >= 0 && !clean[r][c - 1] && map[r][c - 1] != 1) {
                            c--;
                            findNotClean = true;
                        }
                        direction = 3;
                        break;
                    case 1:
                        if (r - 1 >= 0 && !clean[r - 1][c] && map[r - 1][c] != 1) {
                            r--;
                            findNotClean = true;
                        }
                        direction = 0;
                        break;
                    case 2:
                        if (c + 1 < M && !clean[r][c + 1] && map[r][c + 1] != 1) {
                            c++;
                            findNotClean = true;
                        }
                        direction = 1;
                        break;
                    case 3:
                        if (r + 1 < N && !clean[r + 1][c] && map[r + 1][c] != 1) {
                            r++;
                            findNotClean = true;
                        }
                        direction = 2;
                        break;
                }
            }

            if (findNotClean) continue;

            int moveR = r + backR[direction];
            int moveC = c + backC[direction];
            if (moveR < 0 || moveR >= N || moveC < 0 || moveC >= M || map[moveR][moveC] == 1) break;

            r += backR[direction];
            c += backC[direction];
        }

        System.out.println(result);
    }
}
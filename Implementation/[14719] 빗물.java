import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[][] world = new int[H + 1][W + 1];
        int rain = 0;

        // (r, c) 가 기둥이면 1
        st = new StringTokenizer(br.readLine());
        for (int c = 1; c <= W; c++) {
            int block = Integer.parseInt(st.nextToken());
            for (int r = H; r > H - block; r--) {
                world[r][c] = 1;
            }
        }

        // 맨 아래 행부터 왼쪽 -> 오른쪽으로 진행
        // left : 왼쪽에서 출발 시 가장 먼저 나오는 기둥의 col
        // right : 오른쪽에서 출발 시 가장 먼저 나오는 기둥의 col
        // 이후 left ~ right 사이에 나오는 0의 개수로 해당 row에서 가지는 빗물 계산
        for (int r = H; r >= 1; r--) {
            int left = 1, right = W;
            while (left <= W && world[r][left] == 0) left++;
            while (right >= 1 && world[r][right] == 0) right--;
            for (int c = left + 1; c <= right - 1; c++) {
                if (world[r][c] == 0) rain++;
            }
        }

        System.out.println(rain);
    }
}
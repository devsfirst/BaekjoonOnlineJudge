import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[][] city;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        city = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //  선거구를 나누는 방법은 다음과 같다.
        //  기준점 (x, y)와 경계의 길이 d1, d2를 정한다.
        //  d1, d2 ≥ 1
        //  1 ≤ x < x+d1+d2 ≤ N
        //  1 ≤ y-d1 < y < y+d2 ≤ N)
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if (x + d1 + d2 > N) continue;
                        if (1 <= y - d1 && y - d1 < y && y < y + d2 && y + d2 <= N) {
                            simulate(x, y, d1, d2);
                        }
                    }
                }
            }
        }

        System.out.println(result);
    }

    private static void simulate(int x, int y, int d1, int d2) {
        int[] population = new int[6];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        // 각 행의 좌우 경계 좌표
        int[] leftBoundary = {x + 1, y - 1};
        int[] rightBoundary = {x + 1, y + 1};

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                //다음 칸은 경계선이다.
                //(x, y), (x+1, y-1), ..., (x+d1, y-d1)
                //(x, y), (x+1, y+1), ..., (x+d2, y+d2)
                //(x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
                //(x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
                //경계선과 경계선의 안에 포함되어있는 곳은 5번 선거구이다.
                //5번 선거구에 포함되지 않은 구역 (r, c)의 선거구 번호는 다음 기준을 따른다.
                //  1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
                //  2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
                //  3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
                //  4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
                // 경계 시작
                if (r == x && c == y) population[5] += city[r][c];
                // 왼쪽 경계
                else if (r == leftBoundary[0] && c == leftBoundary[1]) population[5] += city[r][c];
                // 오른쪽 경계
                else if (r == rightBoundary[0] && c == rightBoundary[1]) {
                    population[5] += city[r][c];
                    // 각 행의 좌우 경계 좌표 조정
                    if (leftBoundary[0] < x + d1) leftBoundary[1]--;
                    else leftBoundary[1]++;
                    if (rightBoundary[0] < x + d2) rightBoundary[1]++;
                    else rightBoundary[1]--;
                    leftBoundary[0]++;
                    rightBoundary[0]++;
                }
                // 경계 사이
                else if (r == leftBoundary[0] && leftBoundary[1] < c && c < rightBoundary[1]) population[5] += city[r][c];
                // 1번 선거구
                else if (r < x + d1 && c <= y) population[1] += city[r][c];
                // 2번 선거구
                else if (r <= x + d2 && y < c) population[2] += city[r][c];
                // 3번 선거구
                else if (x + d1 <= r && c < y - d1 + d2) population[3] += city[r][c];
                // 4번 선거구
                else if (x + d2 < r && y - d1 + d2 <= c) population[4] += city[r][c];
            }
        }

        // 구역 (r, c)의 인구는 A[r][c]이고, 선거구의 인구는 선거구에 포함된 구역의 인구를 모두 합한 값이다.
        // 선거구를 나누는 방법 중에서, 인구가 가장 많은 선거구와 가장 적은 선거구의 인구 차이의 최솟값을 구해보자.
        for (int i = 1; i <= 5; i++) {
            max = Math.max(max, population[i]);
            min = Math.min(min, population[i]);
        }
        result = Math.min(result, max - min);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static final int SHARK = 17;
    static final int FISH_NUM = 16;
    static int[] moveR = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] moveC = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[4][4];
        Fish[] fish = new Fish[17];
        fish[0] = new Fish(0, -1, -1, -1, false);
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                map[i][j] = a;
                fish[a] = new Fish(a, i, j, b, true);
            }
        }

        // 청소년 상어는 (0, 0)에 있는 물고기를 먹고, (0, 0)에 들어가게 된다.
        // 상어의 방향은 (0, 0)에 있던 물고기의 방향과 같다. 이후 물고기가 이동한다.
        Fish f = fish[map[0][0]];
        f.alive = false;
        Shark shark = new Shark(0, 0, f.d);
        map[0][0] = SHARK;

        simulate(shark, map, fish, f.num);
        System.out.println(result);
    }

    private static void simulate(Shark shark, int[][] map, Fish[] fish, int sum) {
        result = Math.max(result, sum);
        move(map, fish);

        int r = shark.r;
        int c = shark.c;
        int d = shark.d;
        for(int i = 1; i <= 3; i++) {
            int nr = r + (i * moveR[d]);
            int nc = c + (i * moveC[d]);
            if (nr < 0 || nr > 3 || nc < 0 || nc > 3) break;
            if (map[nr][nc] == 0) continue;

            int[][] newMap = new int[4][4];
            for (int j = 0; j < 4; j++) {
                newMap[j] = Arrays.copyOf(map[j], 4);
            }
            Fish[] newFish = new Fish[17];
            for (int j = 0; j < 17; j++) {
                newFish[j] = new Fish(fish[j].num, fish[j].r, fish[j].c, fish[j].d, fish[j].alive);
            }

            Fish f = newFish[newMap[nr][nc]];
            f.alive = false;
            newMap[r][c] = 0;
            newMap[nr][nc] = SHARK;
            simulate(new Shark(nr, nc, f.d), newMap, newFish, sum + f.num);
        }
    }

    private static void move(int[][] map, Fish[] fish) {
        // 물고기는 번호가 작은 물고기부터 순서대로 이동한다.
        for (int i = 1; i <= FISH_NUM; i++) {
            if (!fish[i].alive) continue;
            int r = fish[i].r;
            int c = fish[i].c;
            int d = fish[i].d;
            for (int j = 0; j < 9; j++) {
                int nr = r + moveR[d];
                int nc = c + moveC[d];
                // 이동할 수 있는 칸은 빈 칸과 다른 물고기가 있는 칸, 이동할 수 없는 칸은 상어가 있거나, 공간의 경계를 넘는 칸이다.
                if (nr < 0 || nr > 3 || nc < 0 || nc > 3 || map[nr][nc] == SHARK) {
                    // 각 물고기는 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전시킨다.
                    d++;
                    if (d > 8) d = 1;
                    continue;
                }

                fish[i].d = d;
                map[r][c] = 0;
                // 물고기가 다른 물고기가 있는 칸으로 이동할 때는 서로의 위치를 바꾸는 방식으로 이동한다.
                if (map[nr][nc] != 0) {
                    Fish swapFish = fish[map[nr][nc]];
                    swapFish.r = fish[i].r;
                    swapFish.c = fish[i].c;
                    map[fish[i].r][fish[i].c] = swapFish.num;
                }
                fish[i].r = nr;
                fish[i].c = nc;
                map[nr][nc] = fish[i].num;
                break;
            }
        }
    }

    static class Shark {
        int r, c, d;

        public Shark(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    static class Fish {
        int num, r, c, d;
        boolean alive;

        public Fish(int num, int r, int c, int d, boolean alive) {
            this.num = num;
            this.r = r;
            this.c = c;
            this.d = d;
            this.alive = alive;
        }
    }
}
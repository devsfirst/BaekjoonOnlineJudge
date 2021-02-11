import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, K;
    static int[] moveR = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] moveC = {0, 1, 1, 1, 0, -1, -1, -1};
    static ArrayList<ArrayList<ArrayList<FireBall>>> map = new ArrayList<>();
    static ArrayList<FireBall> ballList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
            for (int j = 0; j <= N; j++) {
                map.get(i).add(new ArrayList<>());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            ballList.add(new FireBall(r, c, m, s, d));
        }

        for (int i = 1; i <= K; i++) {
            /**
             * 1. 모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다.
             *    이동하는 중에는 같은 칸에 여러 개의 파이어볼이 있을 수도 있다.
             */
            move();

            /**
             * 2. 이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다.
             *    1. 같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
             *    2. 파이어볼은 4개의 파이어볼로 나누어진다.
             *    3. 나누어진 파이어볼의 질량, 속력, 방향은 다음과 같다.
             *       1. 질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋이다.
             *       2. 속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.
             *       3. 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다.
             *    4. 질량이 0인 파이어볼은 소멸되어 없어진다.
             */
            afterMove();
        }

        int result = 0;
        for (FireBall fireBall : ballList) {
            result += fireBall.m;
        }

        System.out.println(result);
    }

    private static void afterMove() {
        // (a, b)에 2개 이상의 파이어볼이 있는지 확인
        for (int a = 1; a <= N; a++) {
            for (int b = 1; b <= N; b++) {
                // 2개 이상의 파이어볼이 있는 칸
                if (map.get(a).get(b).size() >= 2) {
                    int r = map.get(a).get(b).get(0).r;
                    int c = map.get(a).get(b).get(0).c;
                    int sameNum = map.get(a).get(b).size();
                    int sumM = 0;
                    int sumS = 0;
                    int dir = map.get(a).get(b).get(0).d % 2;
                    boolean same = true;

                    // 같은 칸에 있는 파이어볼의 정보 합산
                    for (FireBall fireBall : map.get(a).get(b)) {
                        sumM += fireBall.m;
                        sumS += fireBall.s;
                        if (dir != (fireBall.d % 2)) {
                            same = false;
                        }
                    }

                    // (a, b)로 모인 파이어볼 제거
                    for (FireBall fireBall : map.get(a).get(b)) {
                        ballList.remove(fireBall);
                    }

                    // 질량이 0이 아니면 새로운 파이어볼 4개로 나눠짐
                    if (sumM / 5 != 0) {
                        for (int k = 0; k < 4; k++) {
                            // 방향이 모두 같음
                            if (same) ballList.add(new FireBall(r, c, sumM / 5, sumS / sameNum, k * 2));
                                // 방향이 다름
                            else ballList.add(new FireBall(r, c, sumM / 5, sumS / sameNum, k * 2 + 1));
                        }
                    }
                }
                // (a, b)의 검사 끝났으면 다음 명령 움직임을 위해 해당 좌표를 비움
                map.get(a).get(b).clear();
            }
        }
    }

    private static void move() {
        for (FireBall fireBall : ballList) {
            // row 옮김
            if (fireBall.r + moveR[fireBall.d] * fireBall.s < 1) {
                fireBall.r = N - (Math.abs(fireBall.r + moveR[fireBall.d] * fireBall.s) % N);
            } else if (fireBall.r + moveR[fireBall.d] * fireBall.s > N) {
                if ((fireBall.r + moveR[fireBall.d] * fireBall.s) % N == 0) {
                    fireBall.r = N;
                } else {
                    fireBall.r = (fireBall.r + moveR[fireBall.d] * fireBall.s) % N;
                }
            } else {
                fireBall.r = fireBall.r + moveR[fireBall.d] * fireBall.s;
            }

            // column 옮김
            if (fireBall.c + moveC[fireBall.d] * fireBall.s < 1) {
                fireBall.c = N - (Math.abs(fireBall.c + moveC[fireBall.d] * fireBall.s) % N);
            } else if (fireBall.c + moveC[fireBall.d] * fireBall.s > N) {
                if ((fireBall.c + moveC[fireBall.d] * fireBall.s) % N == 0) {
                    fireBall.c = N;
                } else {
                    fireBall.c = (fireBall.c + moveC[fireBall.d] * fireBall.s) % N;
                }
            } else {
                fireBall.c = fireBall.c + moveC[fireBall.d] * fireBall.s;
            }

            // 파이어볼이 이동한 좌표에 파이어볼이 있음을 알림
            map.get(fireBall.r).get(fireBall.c).add(fireBall);
        }
    }

    static class FireBall {
        int r, c, m, s, d;

        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}
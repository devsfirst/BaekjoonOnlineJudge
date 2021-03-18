import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static final int ORANGE = 0;
    static final int GREEN = 1;
    static final int YELLOW = 2;
    static final int BLUE = 3;
    static final int WHITE = 4;
    static final int RED = 5;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            char[][][] cube = makeCube();
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                String s = st.nextToken();
                char plane = s.charAt(0);
                char dir = s.charAt(1);
                simulate(cube, plane, dir);
            }
            result(sb, cube[WHITE]);
        }
        System.out.println(sb.toString());
    }

    private static void simulate(char[][][] cube, char plane, char dir) {
        switch (plane) {
            case 'U':
                rotateCube(cube[WHITE], dir);
                sideRotateCube(cube, WHITE, dir);
                break;
            case 'D':
                rotateCube(cube[YELLOW], dir);
                sideRotateCube(cube, YELLOW, dir);
                break;
            case 'F':
                rotateCube(cube[RED], dir);
                sideRotateCube(cube, RED, dir);
                break;
            case 'B':
                rotateCube(cube[ORANGE], dir);
                sideRotateCube(cube, ORANGE, dir);
                break;
            case 'L':
                rotateCube(cube[GREEN], dir);
                sideRotateCube(cube, GREEN, dir);
                break;
            case 'R':
                rotateCube(cube[BLUE], dir);
                sideRotateCube(cube, BLUE, dir);
                break;
        }
    }

    private static void sideRotateCube(char[][][] cube, int color, char dir) {
        switch (color) {
            case ORANGE:
                if (dir == '+') {
                    char tmp = cube[WHITE][0][0];
                    cube[WHITE][0][0] = cube[BLUE][0][2];
                    cube[BLUE][0][2] = cube[YELLOW][0][0];
                    cube[YELLOW][0][0] = cube[GREEN][2][0];
                    cube[GREEN][2][0] = tmp;

                    tmp = cube[WHITE][0][1];
                    cube[WHITE][0][1] = cube[BLUE][1][2];
                    cube[BLUE][1][2] = cube[YELLOW][0][1];
                    cube[YELLOW][0][1] = cube[GREEN][1][0];
                    cube[GREEN][1][0] = tmp;

                    tmp = cube[WHITE][0][2];
                    cube[WHITE][0][2] = cube[BLUE][2][2];
                    cube[BLUE][2][2] = cube[YELLOW][0][2];
                    cube[YELLOW][0][2] = cube[GREEN][0][0];
                    cube[GREEN][0][0] = tmp;
                } else {
                    char tmp = cube[WHITE][0][0];
                    cube[WHITE][0][0] = cube[GREEN][2][0];
                    cube[GREEN][2][0] = cube[YELLOW][0][0];
                    cube[YELLOW][0][0] = cube[BLUE][0][2];
                    cube[BLUE][0][2] = tmp;

                    tmp = cube[WHITE][0][1];
                    cube[WHITE][0][1] = cube[GREEN][1][0];
                    cube[GREEN][1][0] = cube[YELLOW][0][1];
                    cube[YELLOW][0][1] = cube[BLUE][1][2];
                    cube[BLUE][1][2] = tmp;

                    tmp = cube[WHITE][0][2];
                    cube[WHITE][0][2] = cube[GREEN][0][0];
                    cube[GREEN][0][0] = cube[YELLOW][0][2];
                    cube[YELLOW][0][2] = cube[BLUE][2][2];
                    cube[BLUE][2][2] = tmp;
                }
                break;
            case GREEN:
                if (dir == '+') {
                    char tmp = cube[WHITE][0][0];
                    cube[WHITE][0][0] = cube[ORANGE][2][2];
                    cube[ORANGE][2][2] = cube[YELLOW][2][2];
                    cube[YELLOW][2][2] = cube[RED][0][0];
                    cube[RED][0][0] = tmp;

                    tmp = cube[WHITE][1][0];
                    cube[WHITE][1][0] = cube[ORANGE][1][2];
                    cube[ORANGE][1][2] = cube[YELLOW][1][2];
                    cube[YELLOW][1][2] = cube[RED][1][0];
                    cube[RED][1][0] = tmp;

                    tmp = cube[WHITE][2][0];
                    cube[WHITE][2][0] = cube[ORANGE][0][2];
                    cube[ORANGE][0][2] = cube[YELLOW][0][2];
                    cube[YELLOW][0][2] = cube[RED][2][0];
                    cube[RED][2][0] = tmp;
                } else {
                    char tmp = cube[WHITE][0][0];
                    cube[WHITE][0][0] = cube[RED][0][0];
                    cube[RED][0][0] = cube[YELLOW][2][2];
                    cube[YELLOW][2][2] = cube[ORANGE][2][2];
                    cube[ORANGE][2][2] = tmp;

                    tmp = cube[WHITE][1][0];
                    cube[WHITE][1][0] = cube[RED][1][0];
                    cube[RED][1][0] = cube[YELLOW][1][2];
                    cube[YELLOW][1][2] = cube[ORANGE][1][2];
                    cube[ORANGE][1][2] = tmp;

                    tmp = cube[WHITE][2][0];
                    cube[WHITE][2][0] = cube[RED][2][0];
                    cube[RED][2][0] = cube[YELLOW][0][2];
                    cube[YELLOW][0][2] = cube[ORANGE][0][2];
                    cube[ORANGE][0][2] = tmp;
                }
                break;
            case YELLOW:
                if (dir == '+') {
                    char[] tmp = Arrays.copyOf(cube[RED][2], 3);
                    cube[RED][2] = Arrays.copyOf(cube[GREEN][2], 3);
                    cube[GREEN][2] = Arrays.copyOf(cube[ORANGE][2], 3);
                    cube[ORANGE][2] = Arrays.copyOf(cube[BLUE][2], 3);
                    cube[BLUE][2] = tmp;
                } else {
                    char[] tmp = Arrays.copyOf(cube[RED][2], 3);
                    cube[RED][2] = Arrays.copyOf(cube[BLUE][2], 3);
                    cube[BLUE][2] = Arrays.copyOf(cube[ORANGE][2], 3);
                    cube[ORANGE][2] = Arrays.copyOf(cube[GREEN][2], 3);
                    cube[GREEN][2] = tmp;
                }
                break;
            case BLUE:
                if (dir == '+') {
                    char tmp = cube[WHITE][0][2];
                    cube[WHITE][0][2] = cube[RED][0][2];
                    cube[RED][0][2] = cube[YELLOW][2][0];
                    cube[YELLOW][2][0] = cube[ORANGE][2][0];
                    cube[ORANGE][2][0] = tmp;

                    tmp = cube[WHITE][1][2];
                    cube[WHITE][1][2] = cube[RED][1][2];
                    cube[RED][1][2] = cube[YELLOW][1][0];
                    cube[YELLOW][1][0] = cube[ORANGE][1][0];
                    cube[ORANGE][1][0] = tmp;

                    tmp = cube[WHITE][2][2];
                    cube[WHITE][2][2] = cube[RED][2][2];
                    cube[RED][2][2] = cube[YELLOW][0][0];
                    cube[YELLOW][0][0] = cube[ORANGE][0][0];
                    cube[ORANGE][0][0] = tmp;
                } else {
                    char tmp = cube[WHITE][0][2];
                    cube[WHITE][0][2] = cube[ORANGE][2][0];
                    cube[ORANGE][2][0] = cube[YELLOW][2][0];
                    cube[YELLOW][2][0] = cube[RED][0][2];
                    cube[RED][0][2] = tmp;

                    tmp = cube[WHITE][1][2];
                    cube[WHITE][1][2] = cube[ORANGE][1][0];
                    cube[ORANGE][1][0] = cube[YELLOW][1][0];
                    cube[YELLOW][1][0] = cube[RED][1][2];
                    cube[RED][1][2] = tmp;

                    tmp = cube[WHITE][2][2];
                    cube[WHITE][2][2] = cube[ORANGE][0][0];
                    cube[ORANGE][0][0] = cube[YELLOW][0][0];
                    cube[YELLOW][0][0] = cube[RED][2][2];
                    cube[RED][2][2] = tmp;
                }
                break;
            case WHITE:
                if (dir == '+') {
                    char[] tmp = Arrays.copyOf(cube[ORANGE][0], 3);
                    cube[ORANGE][0] = Arrays.copyOf(cube[GREEN][0], 3);
                    cube[GREEN][0] = Arrays.copyOf(cube[RED][0], 3);
                    cube[RED][0]= Arrays.copyOf(cube[BLUE][0], 3);
                    cube[BLUE][0] = tmp;
                } else {
                    char[] tmp = Arrays.copyOf(cube[ORANGE][0], 3);
                    cube[ORANGE][0] = Arrays.copyOf(cube[BLUE][0], 3);
                    cube[BLUE][0]= Arrays.copyOf(cube[RED][0], 3);
                    cube[RED][0] = Arrays.copyOf(cube[GREEN][0], 3);
                    cube[GREEN][0] = tmp;
                }
                break;
            case RED:
                if (dir == '+') {
                    char tmp = cube[WHITE][2][0];
                    cube[WHITE][2][0] = cube[GREEN][2][2];
                    cube[GREEN][2][2] = cube[YELLOW][2][0];
                    cube[YELLOW][2][0] = cube[BLUE][0][0];
                    cube[BLUE][0][0] = tmp;

                    tmp = cube[WHITE][2][1];
                    cube[WHITE][2][1] = cube[GREEN][1][2];
                    cube[GREEN][1][2] = cube[YELLOW][2][1];
                    cube[YELLOW][2][1] = cube[BLUE][1][0];
                    cube[BLUE][1][0] = tmp;

                    tmp = cube[WHITE][2][2];
                    cube[WHITE][2][2] = cube[GREEN][0][2];
                    cube[GREEN][0][2] = cube[YELLOW][2][2];
                    cube[YELLOW][2][2] = cube[BLUE][2][0];
                    cube[BLUE][2][0] = tmp;
                } else {
                    char tmp = cube[WHITE][2][0];
                    cube[WHITE][2][0] = cube[BLUE][0][0];
                    cube[BLUE][0][0] = cube[YELLOW][2][0];
                    cube[YELLOW][2][0] = cube[GREEN][2][2];
                    cube[GREEN][2][2] = tmp;

                    tmp = cube[WHITE][2][1];
                    cube[WHITE][2][1] = cube[BLUE][1][0];
                    cube[BLUE][1][0] = cube[YELLOW][2][1];
                    cube[YELLOW][2][1] = cube[GREEN][1][2];
                    cube[GREEN][1][2] = tmp;

                    tmp = cube[WHITE][2][2];
                    cube[WHITE][2][2] = cube[BLUE][2][0];
                    cube[BLUE][2][0] = cube[YELLOW][2][2];
                    cube[YELLOW][2][2] = cube[GREEN][0][2];
                    cube[GREEN][0][2] = tmp;
                }
                break;
        }
    }

    private static void rotateCube(char[][] cube, char dir) {
        char tmp = cube[0][0];
        if (dir == '+') {
            cube[0][0] = cube[2][0];
            cube[2][0] = cube[2][2];
            cube[2][2] = cube[0][2];
            cube[0][2] = tmp;
            tmp = cube[0][1];
            cube[0][1] = cube[1][0];
            cube[1][0] = cube[2][1];
            cube[2][1] = cube[1][2];
            cube[1][2] = tmp;
        } else {
            cube[0][0] = cube[0][2];
            cube[0][2] = cube[2][2];
            cube[2][2] = cube[2][0];
            cube[2][0] = tmp;
            tmp = cube[0][1];
            cube[0][1] = cube[1][2];
            cube[1][2] = cube[2][1];
            cube[2][1] = cube[1][0];
            cube[1][0] = tmp;
        }
    }

    private static char[][][] makeCube() {
        char[][][] cube = new char[6][3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[0][i][j] = 'o';
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[1][i][j] = 'g';
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[2][i][j] = 'y';
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[3][i][j] = 'b';
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[4][i][j] = 'w';
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[5][i][j] = 'r';
            }
        }

        return cube;
    }

    private static void result(StringBuilder sb, char[][] cube) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(cube[i][j]);
            }
            sb.append("\n");
        }
    }
}
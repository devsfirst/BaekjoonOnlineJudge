import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N + 1][N + 1];
        int score = 0;

        LinkedList<Node> list = new LinkedList<>();
        StringTokenizer st;
        int[] likes = new int[4];
        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                likes[j] = Integer.parseInt(st.nextToken());
            }
            list.add(new Node(student, likes));
        }

        for (Node node : list) {
            int maxLike = 0;
            int maxEmpty = 0;
            int posR = N, posC = N;
            for (int r = N; r >= 1; r--) {
                for (int c = N; c >= 1; c--) {
                    // 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
                    if (board[r][c] != 0) continue;
                    int likeStudent = 0;
                    for (int i = 0; i < 4; i++) {
                        if (r - 1 >= 1 && node.likes[i] == board[r - 1][c]) likeStudent++;
                        else if (r + 1 <= N && node.likes[i] == board[r + 1][c]) likeStudent++;
                        else if (c - 1 >= 1 && node.likes[i] == board[r][c - 1]) likeStudent++;
                        else if (c + 1 <= N && node.likes[i] == board[r][c + 1]) likeStudent++;
                    }

                    // 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
                    int empty = 0;
                    if (r - 1 >= 1 && board[r - 1][c] == 0) empty++;
                    if (r + 1 <= N && board[r + 1][c] == 0) empty++;
                    if (c - 1 >= 1 && board[r][c - 1] == 0) empty++;
                    if (c + 1 <= N && board[r][c + 1] == 0) empty++;

                    // 3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
                    if (likeStudent > maxLike) {
                        maxLike = likeStudent;
                        maxEmpty = empty;
                        posR = r;
                        posC = c;
                    } else if (likeStudent == maxLike) {
                        if (empty >= maxEmpty) {
                            maxEmpty = empty;
                            posR = r;
                            posC = c;
                        }
                    }
                }
            }
            board[posR][posC] = node.num;
        }

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                for (Node node : list) {
                    if (node.num == board[r][c]) {
                        int likeStudentNum = 0;
                        for (int i = 0; i < 4; i++) {
                            if (r - 1 >= 1 && node.likes[i] == board[r - 1][c]) likeStudentNum++;
                            else if (r + 1 <= N && node.likes[i] == board[r + 1][c]) likeStudentNum++;
                            else if (c - 1 >= 1 && node.likes[i] == board[r][c - 1]) likeStudentNum++;
                            else if (c + 1 <= N && node.likes[i] == board[r][c + 1]) likeStudentNum++;
                        }
                        if (likeStudentNum == 1) score += 1;
                        else if (likeStudentNum == 2) score += 10;
                        else if (likeStudentNum == 3) score += 100;
                        else if (likeStudentNum == 4) score += 1000;
                        break;
                    }
                }
            }
        }

        System.out.println(score);
    }

    private static class Node {
        int num;
        int[] likes;

        public Node(int num, int[] likes) {
            this.num = num;
            this.likes = new int[4];
            System.arraycopy(likes, 0, this.likes, 0, 4);
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int r, c, k;
    static int rowLen = 3;
    static int colLen = 3;
    static int[][] A = new int[101][101];
    static int time = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            // 검사한 row와 col이 r, c만큼 되었는지
            if (r <= rowLen && c <= colLen) {
                if (A[r][c] == k) break;
            }

            time++;
            if (time > 100) break;

            // maxLen 만큼 배열의 크기가 바뀜
            int maxLen = 0;
            HashMap<Integer, Integer>[] hashMaps = new HashMap[101];
            PriorityQueue<Node>[] queues = new PriorityQueue[101];
            if (rowLen >= colLen) {
                // R 연산 -> 열이 바뀜
                // row 마다 HashMap과 PriorityQueue를 가짐

                for (int i = 1; i <= rowLen; i++) {
                    hashMaps[i] = new HashMap<>();
                    for (int j = 1; j <= colLen; j++) {
                        // HashMap에 각 행마다 (숫자, 횟수) 쌍 저장, 0은 포함하지 않음
                        int key = A[i][j];
                        if (key != 0) {
                            hashMaps[i].put(key, hashMaps[i].getOrDefault(key, 0) + 1);
                        }
                    }

                    // 각 행마다 등장한 (숫자, 횟수)를 PriorityQueue에 저장
                    queues[i] = new PriorityQueue<>();
                    for (Integer key : hashMaps[i].keySet()) {
                        queues[i].add(new Node(key, hashMaps[i].get(key)));
                    }
                    maxLen = Math.max(maxLen, queues[i].size() * 2);
                    if (maxLen > 100) maxLen = 100;
                }

                for (int i = 1; i <= rowLen; i++) {
                    int index;
                    for (index = 1; queues[i].size() != 0 && index < 50; index += 2) {
                        Node node = queues[i].remove();
                        A[i][index] = node.num;
                        A[i][index + 1] = node.cnt;
                    }

                    // 배열이 커지고 남은 부분은 0으로 채움
                    while (index <= maxLen) {
                        A[i][index++] = 0;
                    }
                }

                // R 연산 이후 col의 크기가 maxLen으로 바뀜
                colLen = maxLen;
            } else {
                // C 연산 -> 행이 바뀜
                // col 마다 HashMap과 PriorityQueue를 가짐

                for (int j = 1; j <= colLen; j++) {
                    hashMaps[j] = new HashMap<>();
                    for (int i = 1; i <= rowLen; i++) {
                        // HashMap에 각 행마다 (숫자, 횟수) 쌍 저장, 0은 포함하지 않음
                        int key = A[i][j];
                        if (key != 0) {
                            hashMaps[j].put(key, hashMaps[j].getOrDefault(key, 0) + 1);
                        }
                    }

                    // 각 열마다 등장한 (숫자, 횟수)를 PriorityQueue에 저장
                    queues[j] = new PriorityQueue<>();
                    for (Integer key : hashMaps[j].keySet()) {
                        queues[j].add(new Node(key, hashMaps[j].get(key)));
                    }
                    maxLen = Math.max(maxLen, queues[j].size() * 2);
                    if (maxLen > 100) maxLen = 100;
                }

                for (int j = 1; j <= colLen; j++) {
                    int index;
                    for (index = 1; queues[j].size() != 0 && index < 50; index += 2) {
                        Node node = queues[j].remove();
                        A[index][j] = node.num;
                        A[index + 1][j] = node.cnt;
                    }

                    // 배열이 커지고 남은 부분은 0으로 채움
                    while (index <= maxLen) {
                        A[index++][j] = 0;
                    }
                }

                // C 연산 이후 row의 크기가 maxLen으로 바뀜
                rowLen = maxLen;
            }
        }

        if (time > 100) System.out.println(-1);
        else System.out.println(time);
    }

    static class Node implements Comparable<Node>{
        int num;
        int cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node node) {
            if (cnt == node.cnt) return num - node.num;
            else return cnt - node.cnt;
        }
    }
}
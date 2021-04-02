import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int length = input.length();
        boolean[] visit = new boolean[length];

        // 길이 1 ~ length 일 때
        for (int i = 1; i <= length; i++) {
            PriorityQueue<Node> queue = new PriorityQueue<>();
            // 이번에 추가할 문자 고름
            for (int j = 0; j < length; j++) {
                // 이전에 검사하지 않았으면 검사 진행
                if (!visit[j]) {
                    StringBuilder sb = new StringBuilder();
                    // 이전에 사전순으로 정렬된 문자와 이번에 검사할 문자 sb에 더함
                    for (int k = 0; k < length; k++) {
                        if (j == k || visit[k]) sb.append(input.charAt(k));
                        // 길이가 i로 만들어졌으면 우선순위 큐에 추가
                        if (sb.length() == i) queue.add(new Node(sb.toString(), j));
                    }
                }
            }
            // 사전순으로 가장 앞선 문자열을 가진 노드가 나옴
            Node node = queue.remove();
            System.out.println(node.s);
            // 사전순으로 가장 앞선 문자를 사용함
            visit[node.index] = true;
        }
    }

    static class Node implements Comparable<Node> {
        String s;
        int index;

        public Node(String s, int index) {
            this.s = s;
            this.index = index;
        }

        // 사전순으로 정렬
        @Override
        public int compareTo(Node node) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == node.s.charAt(i)) continue;
                return s.charAt(i) - node.s.charAt(i);
            }
            return 0;
        }
    }
}
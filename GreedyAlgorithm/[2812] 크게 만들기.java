import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String input = br.readLine();
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        int remove = 0;
        for (int i = 0; i < N; i++) {
            int num = input.charAt(i) - '0';
            // 스택이 비었거나 K번 다 지웠으면 스택에 그냥 넣음
            if (stack.size() == 0 || remove == K) {
                stack.add(num);
                continue;
            }

            // 스택의 top < i번째 수 -> 스택에서 i번째 수보다 작은 값을 제거
            while (stack.size() > 0 && stack.peek() < num) {
                remove++;
                stack.pop();
                if (remove == K) break;
            }
            stack.add(num);
        }

        // 스택의 top은 top - 1보다 작거나 같은 수이므로
        // 아직 다 제거하지 못했으면 스택의 맨 위의 수(일의 자리) 제거
        while (remove < K) {
            stack.pop();
            remove++;
        }

        while (stack.size() != 0) {
            sb.append(stack.pop());
        }

        System.out.println(sb.reverse());
    }
}
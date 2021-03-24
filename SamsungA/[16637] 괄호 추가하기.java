import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static int N;
    static String expression;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        expression = br.readLine();

        dfs(0, 0);

        System.out.println(max);
    }

    private static void dfs(int currentIndex, int result) {
        if (currentIndex > N - 1) {
            max = Math.max(max, result);
            return;
        }

        // + 1 + 2 + 3 + 4 처럼 있다고 생각하면 0번 index(1) 앞에는 +가 있음
        int num = expression.charAt(currentIndex) - '0';
        char op = currentIndex == 0 ? '+' : expression.charAt(currentIndex - 1);

        // currentIndex 다음에 숫자가 있으면 괄호 추가
        // 1 + 2 + 3 + 4 에서 currentIndex가 0일 때
        // (1 + 2) + 3 + 4 처럼 묶음
        if (currentIndex + 2 < N) {
            int parentheses = calculate(num, expression.charAt(currentIndex + 2) - '0', expression.charAt(currentIndex + 1));
            dfs(currentIndex + 4, calculate(result, parentheses, op));
        }
        // 괄호 추가 X
        dfs(currentIndex + 2, calculate(result, num, op));
    }

    private static int calculate(int a, int b, char op) {
        if (op == '+') return a + b;
        else if (op == '-') return a - b;
        return a * b;
    }
}
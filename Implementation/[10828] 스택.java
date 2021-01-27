import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int[] stack;
    private static int top = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        stack = new int[N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            if (s.equals("pop")) {
                System.out.println(pop());
            } else if (s.equals("size")) {
                System.out.println(size());
            } else if (s.equals("empty")) {
                System.out.println(empty());
            } else if (s.equals("top")) {
                System.out.println(top());
            } else {
                int x = Integer.parseInt(s.substring(5));
                push(x);
            }
        }
    }

    private static void push(int x) {
        stack[++top] = x;
    }

    private static int top() {
        if (top == -1) return -1;
        return stack[top];
    }

    private static int empty() {
        if (top == -1) return 1;
        return 0;
    }

    private static int size() {
        if (top == -1) return 0;
        return top + 1;
    }

    private static int pop() {
        if (top == -1) return top;
        return stack[top--];
    }
}
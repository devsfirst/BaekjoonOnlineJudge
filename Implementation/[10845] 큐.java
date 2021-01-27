import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int[] queue;
    private static int front = 0;
    private static int back = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        queue = new int[N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            if (s.equals("pop")) {
                System.out.println(pop());
            } else if (s.equals("size")) {
                System.out.println(size());
            } else if (s.equals("empty")) {
                System.out.println(empty());
            } else if (s.equals("front")) {
                System.out.println(front());
            } else if (s.equals("back")) {
                System.out.println(back());
            } else {
                int x = Integer.parseInt(s.substring(5));
                push(x);
            }
        }
    }

    private static void push(int x) {
        queue[++back] = x;
    }

    private static int back() {
        if (empty() == 1) return -1;
        return queue[back];
    }

    private static int front() {
        if (empty() == 1) return -1;
        return queue[front];
    }

    private static int empty() {
        if (front > back) return 1;
        return 0;
    }

    private static int size() {
        if (empty() == 1) return 0;
        return (back - front) + 1;
    }

    private static int pop() {
        if (empty() == 1) return -1;
        return queue[front++];
    }
}
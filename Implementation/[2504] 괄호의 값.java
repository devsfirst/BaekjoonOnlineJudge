import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        Stack<Character> stack = new Stack<>();
        int result = 0;
        int tmp = 1;
        boolean impossible = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                tmp *= 2;
                stack.add(c);
            } else if (c == '[') {
                tmp *= 3;
                stack.add(c);
            } else if (c == ')') {
                if (stack.size() == 0) {
                    impossible = true;
                    break;
                }

                if (stack.peek() == '(') {
                    if (s.charAt(i - 1) != ')' && s.charAt(i - 1) != ']') {
                        result += tmp;
                    }
                    stack.pop();
                    tmp /= 2;
                } else {
                    impossible = true;
                    break;
                }
            } else if (c == ']') {
                if (stack.size() == 0) {
                    impossible = true;
                    break;
                }

                if (stack.peek() == '[') {
                    if (s.charAt(i - 1) != ')' && s.charAt(i - 1) != ']') {
                        result += tmp;
                    }
                    stack.pop();
                    tmp /= 3;
                } else {
                    impossible = true;
                    break;
                }
            }
        }
        if (stack.size() > 0) impossible = true;

        if (impossible) System.out.println(0);
        else System.out.println(result);
    }
}
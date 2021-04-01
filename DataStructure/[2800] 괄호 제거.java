import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static Set<String> set = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();

        dfs(expression);

        for (String s : set) {
            System.out.println(s);
        }
    }

    private static void dfs(String expression) {
        // 수식에서 ( 괄호가 나올 때마다 이에 해당하는 짝 괄호랑 제거하고
        // 제거해서 만들어진 새로운 수식으로 dfs 진행
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                String newExpression = newExpression(expression, i);
                if (!set.contains(newExpression)) {
                    set.add(newExpression);
                    dfs(newExpression);
                }
            }
        }
    }

    private static String newExpression(String expression, int index) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        boolean removed = false;
        // 인자로 받은 index가 제거할 ( 괄호 시작 index
        // num값으로 ( 의 짝 괄호 찾고 제거
        for (int i = 0; i < expression.length(); i++) {
            if (!removed) {
                if (i >= index && expression.charAt(i) == '(') {
                    num++;
                    if (num == 1) continue;
                } else if (i >= index && expression.charAt(i) == ')') {
                    num--;
                    if (num == 0) {
                        removed = true;
                        continue;
                    }
                }
            }
            sb.append(expression.charAt(i));
        }

        return sb.toString();
    }
}
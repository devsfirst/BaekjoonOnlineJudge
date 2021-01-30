import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int N;
    private static String[] input;
    private static int inputAlphabetNum;
    private static boolean[] exist = new boolean[26];
    private static char[] alphabet = new char[10];
    private static int[] weight = new int[26];
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = new String[N];

        for (int i = 0; i < N; i++) {
            input[i] = br.readLine();
            for (int j = 0; j < input[i].length(); j++) {
                if (!exist[input[i].charAt(j) - 'A']) {
                    alphabet[inputAlphabetNum++] = input[i].charAt(j);
                    exist[input[i].charAt(j) - 'A'] = true;
                }
            }
        }

        backtracking(0);

        System.out.println(max);
    }

    private static void backtracking(int length) {
        if (length == inputAlphabetNum) {
            max = Math.max(max, calculate());
            return;
        }

        for (int i = 0; i < inputAlphabetNum; i++) {
            if (weight[alphabet[i] - 'A'] == 0) {
                weight[alphabet[i] - 'A'] = 9 - length;
                backtracking(length + 1);
                weight[alphabet[i] - 'A'] = 0;
            }
        }
    }

    private static int calculate() {
        int num = 0;
        for (int i = 0; i < N; i++) {
            int mul = (int) Math.pow(10, input[i].length() - 1);
            for (int j = 0; j < input[i].length(); j++) {
                num += (weight[input[i].charAt(j) - 'A'] * mul);
                mul /= 10;
            }
        }
        return num;
    }
}
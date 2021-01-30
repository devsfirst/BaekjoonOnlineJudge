import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int L;
    private static int C;
    private static char[] alphabet;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        alphabet = new char[C];

        String s = br.readLine();
        for (int i = 0; i < C; i++) {
            alphabet[i] = s.charAt(i * 2);
        }
        Arrays.sort(alphabet);

        backtracking(0, 0);
    }

    private static void backtracking(int length, int index) {
        if (length == L) {
            String s = sb.toString();
            if (s.contains("a") || s.contains("e") || s.contains("i") || s.contains("o") || s.contains("u")) {
                if (s.replaceAll("[aeiou]", "").length() >= 2) {
                    System.out.println(s);
                }
            }
            return;
        }

        for (int i = index; i < C; i++) {
            sb.append(alphabet[i]);
            backtracking(length + 1, i + 1);
            sb.deleteCharAt(length);
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static String[] words;
    private static boolean[] learnedAlphabets;
    private static int maxLearn = Integer.MIN_VALUE;
    private static final ArrayList<Character> arrayList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        words = new String[N];
        learnedAlphabets = new boolean[26];
        learnedAlphabets['a' - 'a'] = true; // a
        learnedAlphabets['c' - 'a'] = true; // c
        learnedAlphabets['i' - 'a'] = true; // i
        learnedAlphabets['n' - 'a'] = true; // n
        learnedAlphabets['t' - 'a'] = true; // t

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            words[i] = s.substring(4, s.length() - 4);
            for (int j = 0; j < words[i].length(); j++) {
                if (!arrayList.contains(words[i].charAt(j)) && !learnedAlphabets[words[i].charAt(j) - 'a']) {
                    arrayList.add(words[i].charAt(j));
                }
            }
        }

        if (K < 5) {
            System.out.println(0);
        } else if (K == 26) {
            System.out.println(N);
        } else if (K - 5 > arrayList.size()) {
            System.out.println(N);
        } else {
            dfs(0, K - 5, 0);

            System.out.println(maxLearn);
        }
    }

    private static void dfs(int addNum, int maxAddNum, int next) {
        if (addNum == maxAddNum) {
            int num = 0;
            for (String word : words) {
                boolean isLearned = true;
                for (int i = 0; i < word.length(); i++) {
                    if (!learnedAlphabets[word.charAt(i) - 'a']) {
                        isLearned = false;
                        break;
                    }
                }
                if (isLearned) num++;
            }
            maxLearn = Math.max(maxLearn, num);
            return;
        }

        for (int i = next; i < arrayList.size(); i++) {
            char character = arrayList.get(i);
            if (!learnedAlphabets[character - 'a']) {
                learnedAlphabets[character - 'a'] = true;
                dfs(addNum + 1, maxAddNum, i + 1);
                learnedAlphabets[character - 'a'] = false;
            }
        }
    }
}
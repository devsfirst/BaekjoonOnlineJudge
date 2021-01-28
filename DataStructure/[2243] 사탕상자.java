import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int TREE_SIZE = 2097152;
    private static final int OFFSET = TREE_SIZE / 2;
    private static int[] tree = new int[TREE_SIZE];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            if (Integer.parseInt(st.nextToken()) == 1) {
                pick(Integer.parseInt(st.nextToken()));
            } else {
                update(Integer.parseInt(st.nextToken()) + OFFSET, Integer.parseInt(st.nextToken()));
            }
        }
    }

    private static void pick(int priority) {
        int index = 1;
        int left, right;
        while (index < OFFSET) {
            left = index * 2;
            right = left + 1;
            if (priority <= tree[left]) {
                index *= 2;
            }
            else {
                priority -= tree[left];
                index = right;
            }
        }
        System.out.println(index - OFFSET);
        update(index, -1);
    }

    private static void update(int index, int amount) {
        while (index > 0) {
            tree[index] += amount;
            index /= 2;
        }
    }
}
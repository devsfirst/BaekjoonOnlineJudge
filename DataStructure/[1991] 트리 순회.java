import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static char[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new char[1000];
        int[] tmp = new int[1000]; // 1부터 ABC 순서대로 tree에 위치하는 index

        tmp[1] = 1;
        tree[1] = 'A';
        for (int i = 1; i <= N; i++) {
            String s = br.readLine();

            if (s.charAt(2) != '.') {
                int index = tmp[s.charAt(0) - 'A' + 1];
                tree[index * 2] = s.charAt(2);
                tmp[s.charAt(2) - 'A' + 1] = index * 2;
            }
            if (s.charAt(4) != '.') {
                int index = tmp[s.charAt(0) - 'A' + 1];
                tree[index * 2 + 1] = s.charAt(4);
                tmp[s.charAt(4) - 'A' + 1] = index * 2 + 1;
            }
        }



        preorder(1);
        System.out.println();
        inorder(1);
        System.out.println();
        postorder(1);
    }

    private static void postorder(int index) {
        if (tree[index] != 0) {
            postorder(index * 2);
            postorder(index * 2 + 1);
            System.out.print(tree[index]);
        }
    }

    private static void inorder(int index) {
        if (tree[index] != 0) {
            inorder(index * 2);
            System.out.print(tree[index]);
            inorder(index * 2 + 1);
        }
    }

    private static void preorder(int index) {
        if (tree[index] != 0) {
            System.out.print(tree[index]);
            preorder(index * 2);
            preorder(index * 2 + 1);
        }
    }
}
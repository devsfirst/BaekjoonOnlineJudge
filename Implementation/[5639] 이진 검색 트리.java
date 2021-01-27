import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        Node root = new Node(Integer.parseInt(s));
        while ((s = br.readLine()) != null && s.length() != 0) {
            root.insert(new Node(Integer.parseInt(s)));
        }

        postOrder(root);
    }

    private static void postOrder(Node node) {
        if (node.getLeft() != null) postOrder(node.getLeft());
        if (node.getRight() != null) postOrder(node.getRight());
        System.out.println(node.getNumber());
    }

    private static class Node {
        private final int number;
        private Node left, right;

        public Node(int number) {
            this.number = number;
            this.left = null;
            this.right = null;
        }

        public int getNumber() {
            return number;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public void insert(Node node) {
            if (node.getNumber() < this.number) {
                if (this.left == null) this.left = node;
                else this.left.insert(node);
            } else {
                if (this.right == null) this.right = node;
                else this.right.insert(node);
            }
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        List<Document> documentList = new ArrayList<>();

        for (int i = 0; i < T; i++) {
            String[] split = br.readLine().split(" ");
            int N = Integer.parseInt(split[0]);
            int M = Integer.parseInt(split[1]);

            split = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                documentList.add(new Document(j, Integer.parseInt(split[j])));
            }

            int order = 1;
            int max;
            while (true) {
                max = -1;
                for (Document document : documentList) {
                    if (document.getPriority() > max) {
                        max = document.getPriority();
                    }
                }

                while (documentList.get(0).getPriority() != max) {
                    documentList.add(documentList.remove(0));
                }

                if (documentList.remove(0).getId() == M) {
                    System.out.println(order);
                    break;
                }
                order++;
            }
            documentList.clear();
        }
    }

    public static class Document {

        private int id;
        private int priority;

        public Document(int id, int priority) {
            this.id = id;
            this.priority = priority;
        }

        public int getId() {
            return id;
        }

        public int getPriority() {
            return priority;
        }
    }
}
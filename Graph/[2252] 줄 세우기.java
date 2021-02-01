import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] inDegree = new int[N + 1];
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            inDegree[b]++;
        }

        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) linkedList.offer(i);
        }

        while (linkedList.size() != 0) {
            int num = linkedList.poll();
            System.out.print(num + " ");

            for (Integer dest : graph.get(num)) {
                inDegree[dest]--;
                if (inDegree[dest] == 0) {
                    linkedList.offer(dest);
                }
            }
        }
    }
}
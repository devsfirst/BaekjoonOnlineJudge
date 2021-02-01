import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] inDegree = new int[N + 1];
        int[] time = new int[N + 1];
        int[] endTime = new int[N + 1];
        ArrayList<ArrayList<Integer>> prevList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> nextList = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            prevList.add(new ArrayList<>());
            nextList.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int next = Integer.parseInt(st.nextToken());
            time[i] = next;

            while (st.hasMoreTokens()) {
                next = Integer.parseInt(st.nextToken());
                if (next == -1) break;
                // i번째 건물을 짓기 전 next 건물이 필요하고
                // next 건물은 i번째 건물을 짓는데 영향을 줌
                prevList.get(i).add(next);
                nextList.get(next).add(i);
                inDegree[i]++;
            }
        }

        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            // 가장 먼저 지을 수 있는 건물은 inDegree가 0
            // 그 건물은 짓는데 걸리는 시간이 입력으로 받은 시간과 같음
            if (inDegree[i] == 0) {
                linkedList.offer(i);
                endTime[i] = time[i];
            }
        }

        while (linkedList.size() != 0) {
            int b = linkedList.poll();
            // inDegree가 0인 건물 b에 대해 b를 지은 후 영향을 받는 건물이 i
            for (Integer i : nextList.get(b)) {
                inDegree[i]--;
                // b를 지은 후 i번 건물을 지을 수 있게 됨
                if (inDegree[i] == 0) {
                    int max = 0;
                    // i번 건물을 짓기 전 가장 늦게 끝난 선행 건물의 시간 구하기
                    for (Integer j : prevList.get(i)) {
                        max = Math.max(max, endTime[j]);
                    }
                    // i번 건물을 짓기 전 가장 늦게 끝난 선행 건물의 시간에 i번 건물을 짓는데 필요한 시간을 더함
                    endTime[i] = time[i] + max;
                    linkedList.offer(i);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.println(endTime[i]);
        }
    }
}
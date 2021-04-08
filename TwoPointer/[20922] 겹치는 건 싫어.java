import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        int max = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 1;
        // map: (수열, 빈도수) 저장
        Map<Integer, Integer> map = new HashMap<>();
        for (int right = 1; right <= N; right++) {
            int rightNum = arr[right];
            map.put(rightNum, map.getOrDefault(rightNum, 0) + 1);
            // K번 보다 많이 나옴
            if (map.get(rightNum) > K) {
                // right번째 수를 제외한 길이
                max = Math.max(max, right - left);
                // K번 나온 수를 찾아 빈도수를 하나 줄일 때까지 left를 옮김
                do {
                    int leftNum = arr[left];
                    map.put(leftNum, map.get(leftNum) - 1);
                } while (arr[left++] != rightNum);
            } else {
                // right번째 수를 포함한 길이
                max = Math.max(max, right - left + 1);
            }
        }
        System.out.println(max);
    }
}
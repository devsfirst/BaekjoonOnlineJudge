import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Village> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            list.add(new Village(X, A));
        }
        // 마을 번호 오름차순으로 정렬
        Collections.sort(list);

        // sum[i] : i번째 마을까지 인구수 합
        long tmp = 0;
        long[] sum = new long[N + 1];
        for (int i = 0; i < N; i++) {
            tmp += list.get(i).population;
            sum[i + 1] = tmp;
        }

        // i 번째 마을 기준으로 왼쪽, 오른쪽 인구수 차이가 제일 적은 곳 구하기
        long diff = Long.MAX_VALUE;
        int minLocation = 0;
        for (int i = 1; i <= N; i++) {
            long tmpDiff = Math.abs(sum[N] - sum[i] - sum[i - 1]);
            if (tmpDiff < diff) {
                diff = tmpDiff;
                minLocation = list.get(i - 1).num;
            }
        }

        System.out.println(minLocation);
    }

    static class Village implements Comparable<Village> {
        int num, population;

        public Village(int num, int population) {
            this.num = num;
            this.population = population;
        }

        @Override
        public int compareTo(Village village) {
            return num - village.num;
        }
    }
}
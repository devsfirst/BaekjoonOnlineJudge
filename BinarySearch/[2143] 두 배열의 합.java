import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        // A 배열
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // B 배열
        int M = Integer.parseInt(br.readLine());
        int[] B = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> sumA = new ArrayList<>();
        ArrayList<Integer> sumB = new ArrayList<>();

        // A 배열의 모든 가능한 부분합
        for (int i = 1; i <= N; i++) {
            int tmpA = A[i];
            sumA.add(tmpA);
            for (int j = i + 1; j <= N; j++) {
                tmpA += A[j];
                sumA.add(tmpA);
            }
        }

        // B 배열의 모든 가능한 부분합
        for (int i = 1; i <= M; i++) {
            int tmpB = B[i];
            sumB.add(tmpB);
            for (int j = i + 1; j <= M; j++) {
                tmpB += B[j];
                sumB.add(tmpB);
            }
        }

        // A는 큰 값부터, B는 작은 값부터 조회
        Collections.sort(sumA);
        Collections.sort(sumB);


        long result = 0;
        int idxA = sumA.size() - 1; // 큰 값부터
        int idxB = 0; // 작은 값부터
        // 범위를 벗어나면 T를 만족하는 합이 없는 것
        while (idxA >= 0 && idxB < sumB.size()) {
            int a = sumA.get(idxA);
            int b = sumB.get(idxB);

            if (a + b == T) {
                int numA = 0, numB = 0;
                // A 배열에서 a와 같은 값 개수
                while (idxA >= 0 && sumA.get(idxA) == a) {
                    numA++;
                    idxA--;
                }
                // B 배열에서 b와 같은 값 개수
                while (idxB < sumB.size() && sumB.get(idxB) == b) {
                    numB++;
                    idxB++;
                }
                // 두 while에서 얻은 a, b 값 개수 곱 -> T가 되는 쌍의 수
                result += (long) numA * (long) numB;
            }
            else if (a + b < T) {
                idxB++;
            } else {
                idxA--;
            }
        }

        System.out.println(result);
    }
}
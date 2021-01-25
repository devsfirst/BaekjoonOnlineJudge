import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        int M = Integer.parseInt(br.readLine());
        int[] B = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
            if (binarySearch(0, A.length - 1, A, B[i])) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }

    private static boolean binarySearch(int start, int end, int[] arr, int num) {
        if (start > end) return false;

        int mid = (start + end) / 2;

        if (arr[mid] == num) return true;
        else if (arr[mid] < num) return binarySearch(mid + 1, end, arr, num);
        else return binarySearch(start, mid - 1, arr, num);
    }
}
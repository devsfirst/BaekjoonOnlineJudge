import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        String[] split;
        int A, B, C;

        while ((line = br.readLine()) != null) {
            split = line.split(" ");
            A = Integer.parseInt(split[0]);
            B = Integer.parseInt(split[1]);
            C = Integer.parseInt(split[2]);
            System.out.println(Math.max(B - A, C - B) - 1);
        }
    }
}
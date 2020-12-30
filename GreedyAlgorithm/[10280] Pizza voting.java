import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int p = Integer.parseInt(split[1]);
        int Alice = n, Bob = 1;

        while (true) {
            Alice--;
            if (Alice == Bob) break;

            Bob++;
            if (Bob == Alice) break;

            if (p - Bob >= Alice - p) Bob++;
            else Alice--;
            if (Alice == Bob) break;
        }

        if (Alice == p) System.out.println("YES");
        else System.out.println("NO");
    }
}
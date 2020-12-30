import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String alphabets = br.readLine();
        char curPointingAlphabet = 'A';
        char curAlphabet;
        int num = 0;
        int dist1, dist2;

        for (int i = 0; i < alphabets.length(); i++) {
            curAlphabet = alphabets.charAt(i);
            dist1 = Math.abs(curPointingAlphabet - curAlphabet);
            dist2 = 26 - dist1;
            num += Math.min(dist1, dist2);
            curPointingAlphabet = curAlphabet;
        }

        System.out.println(num);
    }
}
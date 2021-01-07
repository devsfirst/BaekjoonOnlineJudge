import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String line = br.readLine();
        StringBuilder sb = new StringBuilder();
        int len = line.length();
        char c;

        for (int i = 0; i < len; i++) {
            StringBuilder tmpSb = new StringBuilder("");
            c = line.charAt(i);
            if (c == ' ') tmpSb.append(c);
            else if (c == '<') {
                while (true) {
                    tmpSb.append(c);
                    if (c == '>') break;
                    c = line.charAt(i + 1);
                    i++;
                }
            } else {
                while (true) {
                    tmpSb.append(c);
                    if (i + 1 < len) {
                        c = line.charAt(i + 1);
                        if (c == ' ' || c == '<') break;
                        i++;
                    }
                    else break;
                }
                tmpSb = tmpSb.reverse();
            }
            sb.append(tmpSb);
        }

        System.out.println(sb.toString());
        br.close();
    }
}
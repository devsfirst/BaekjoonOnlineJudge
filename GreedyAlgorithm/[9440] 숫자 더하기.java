import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        String[] split;
        int len, num;
        ArrayList<Integer> arrayList = new ArrayList<>();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        while (true) {
            line = br.readLine();
            if (line.equals("0")) break;

            split = line.split(" ");
            len = Integer.parseInt(split[0]) + 1;
            for (int i = 1; i < len; i++) {
                num = Integer.parseInt(split[i]);
                arrayList.add(num);
            }
            Collections.sort(arrayList);
            for (int i = 0; i < arrayList.size(); i++) {
                if (i % 2 == 0) sb1.append(arrayList.get(i));
                else sb2.append(arrayList.get(i));
            }

            if (sb1.charAt(0) == '0') moveZero(sb1);
            if (sb2.charAt(0) == '0') moveZero(sb2);
            if (Integer.parseInt(split[0]) % 2 != 0) headNumCmp(sb1, sb2);

            System.out.println(Integer.parseInt(sb1.toString()) + Integer.parseInt(sb2.toString()));
            clear(arrayList, sb1, sb2);
        }
    }

    private static void clear(ArrayList<Integer> arrayList, StringBuilder sb1, StringBuilder sb2) {
        sb1.delete(0, sb1.length());
        sb2.delete(0, sb2.length());
        arrayList.clear();
    }

    private static void headNumCmp(StringBuilder sb1, StringBuilder sb2) {
        int s1 = sb1.charAt(0) - '0';
        int s2 = sb2.charAt(0) - '0';
        if (s1 > s2) {
            sb1.deleteCharAt(0);
            sb1.insert(0, s2);
            sb2.deleteCharAt(0);
            sb2.insert(0, s1);
        }
    }

    private static void moveZero(StringBuilder sb) {
        int num;
        for (int i = 1; i < sb.length(); i++) {
            if (sb.charAt(i) != '0') {
                num = sb.charAt(i) - '0';
                sb.deleteCharAt(i);
                sb.insert(0, num);
                break;
            }
        }
    }
}
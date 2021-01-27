import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        String s;
        float total = 0;

        while ((s = br.readLine()) != null) {
            treeMap.put(s, treeMap.getOrDefault(s, 0) + 1);
            total++;
        }

        for (String key : treeMap.keySet()) {
            float rate = treeMap.get(key) * 100 / total;
            String format = String.format("%.4f", rate);
            System.out.println(key + " " + format);
        }
    }
}
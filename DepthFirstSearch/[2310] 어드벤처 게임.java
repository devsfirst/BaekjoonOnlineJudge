import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int n;
    private static boolean[] visit;
    private static char[] npc;
    private static int[] moneyArr;
    private static boolean canReach;
    private static ArrayList<ArrayList<Integer>> roomArray = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            visit = new boolean[n + 1];
            npc = new char[n + 1];
            moneyArr = new int[n + 1];
            canReach = false;
            roomArray.add(new ArrayList<>());

            String[] split;
            for (int i = 1; i <= n; i++) {
                split = br.readLine().split(" ");
                npc[i] = split[0].charAt(0);
                moneyArr[i] = Integer.parseInt(split[1]);
                roomArray.add(new ArrayList<>());
                for (int j = 2; j < split.length - 1; j++) {
                    roomArray.get(i).add(Integer.parseInt(split[j]));
                }
            }

            arrive(1, 0);
            if (canReach) System.out.println("Yes");
            else System.out.println("No");
            roomArray.clear();
        }
    }

    public static void arrive(int room, int money) {
        if (npc[room] == 'L') {
            if (money < moneyArr[room]) money = moneyArr[room];
        }
        else if (npc[room] == 'T') {
            if (money < moneyArr[room]) return;
            money -= moneyArr[room];
        }

        if (room == n) {
            canReach = true;
            return;
        }

        visit[room] = true;
        for (int i = 0; i < roomArray.get(room).size(); i++) {
            int nextRoom = roomArray.get(room).get(i);
            if (!visit[nextRoom]) {
                arrive(nextRoom, money);
            }
        }
        visit[room] = false;
    }
}
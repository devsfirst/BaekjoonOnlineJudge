import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, fuel;
    static int[][] map;
    static int[] location = {0, 0};
    static int minDistance;
    static ArrayList<Customer> customers = new ArrayList<>();
    static Customer minCustomer;
    static int[] moveR = {-1, 0, 1, 0};
    static int[] moveC = {0, 1, 0, -1};
    static boolean[][] visit;
    static boolean success;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        location[0] = Integer.parseInt(st.nextToken());
        location[1] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());
            int dr = Integer.parseInt(st.nextToken());
            int dc = Integer.parseInt(st.nextToken());
            customers.add(new Customer(sr, sc, dr, dc));
        }
        Collections.sort(customers);

        simulate();

        if (!success) System.out.println(-1);
        else System.out.println(fuel);
    }

    private static void simulate() {
        while (customers.size() != 0) {
            // 백준이 태울 승객을 고를 때는 현재 위치에서 최단거리가 가장 짧은 승객을 고른다.
            // 택시와 승객이 같은 위치에 서 있으면 그 승객까지의 최단거리는 0이다.
            minDistance = Integer.MAX_VALUE;
            minCustomer = null;
            for (Customer customer : customers) {
                visit = new boolean[N + 1][N + 1];
                findMinCustomer(customer, location[0], location[1]);
            }

            // 승객에게 못감
            if (minCustomer == null) {
                success = false;
                break;
            }

            customers.remove(minCustomer);

            // 연료는 한 칸 이동할 때마다 1만큼 소모된다.
            // 승객 위치로 이동 및 연료 소모
            location[0] = minCustomer.sr;
            location[1] = minCustomer.sc;
            fuel -= minDistance;

            // 승객 위치로 간 후 연료 부족
            if (fuel <= 0) {
                success = false;
                break;
            }

            visit = new boolean[N + 1][N + 1];
            success = false;
            move(location[0], location[1], minCustomer.dr, minCustomer.dc);
            // 승객을 목적지까지 옮기지 못함
            if (!success) break;
        }
    }

    private static void move(int r, int c, int dr, int dc) {
        LinkedList<MoveNode> linkedList = new LinkedList<>();
        linkedList.add(new MoveNode(r, c, fuel, 0));
        visit[r][c] = true;
        while (linkedList.size() != 0) {
            MoveNode node = linkedList.remove();
            // 한 승객을 목적지로 성공적으로 이동시키면, 그 승객을 태워 이동하면서 소모한 연료 양의 두 배가 충전된다.
            if (node.r == dr && node.c == dc) {
                fuel = node.leftFuel + (2 * node.moveFuel);
                location[0] = node.r;
                location[1] = node.c;
                success = true;
                break;
            }

            // 이동하는 도중에 연료가 바닥나면 이동에 실패하고, 그 날의 업무가 끝난다.
            // 승객을 목적지로 이동시킨 동시에 연료가 바닥나는 경우는 실패한 것으로 간주하지 않는다.
            if (node.leftFuel == 0) {
                if (linkedList.size() != 0) continue;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nextR = node.r + moveR[d];
                int nextC = node.c + moveC[d];
                if (nextR < 1 || nextR > N || nextC < 1 || nextC > N) continue;
                if (map[nextR][nextC] != 1 && !visit[nextR][nextC]) {
                    visit[nextR][nextC] = true;
                    linkedList.add(new MoveNode(nextR, nextC, node.leftFuel - 1, node.moveFuel + 1));
                }
            }
        }
    }

    private static void findMinCustomer(Customer customer, int r, int c) {
        LinkedList<CustomerNode> linkedList = new LinkedList<>();
        linkedList.add(new CustomerNode(r, c, 0));
        visit[r][c] = true;

        while (linkedList.size() != 0) {
            CustomerNode customerNode = linkedList.remove();
            if (customerNode.r == customer.sr && customerNode.c == customer.sc) {
                if (customerNode.distance < minDistance) {
                    minDistance = customerNode.distance;
                    minCustomer = customer;
                }
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nextR = customerNode.r + moveR[d];
                int nextC = customerNode.c + moveC[d];
                if (nextR < 1 || nextR > N || nextC < 1 || nextC > N) continue;
                if (map[nextR][nextC] != 1 && !visit[nextR][nextC]) {
                    visit[nextR][nextC] = true;
                    linkedList.add(new CustomerNode(nextR, nextC, customerNode.distance + 1));
                }
            }
        }
    }

    static class Customer implements Comparable<Customer> {
        int sr, sc, dr, dc;

        public Customer(int sr, int sc, int dr, int dc) {
            this.sr = sr;
            this.sc = sc;
            this.dr = dr;
            this.dc = dc;
        }

        @Override
        public int compareTo(Customer customer) {
            if (sr == customer.sr) return sc - customer.sc;
            else {
                return sr - customer.sr;
            }
        }
    }

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class CustomerNode extends Node {
        int distance;

        public CustomerNode(int r, int c, int distance) {
            super(r, c);
            this.distance = distance;
        }
    }

    static class MoveNode extends Node {
        int leftFuel, moveFuel;

        public MoveNode(int r, int c, int leftFuel, int moveFuel) {
            super(r, c);
            this.leftFuel = leftFuel;
            this.moveFuel = moveFuel;
        }
    }
}
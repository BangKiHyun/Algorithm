package baekJ.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_19238_스타트_택시 {

    private static int n, m, fuel;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int[][] board;
    private static boolean[][] visit;
    private static Taxi taxi;
    private static List<Passenger> passengerList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        board = new int[n + 1][n + 1];
        visit = new boolean[n + 1][n + 1];
        for (int raw = 1; raw <= n; raw++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= n; col++) {
                board[raw][col] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int taxiRaw = Integer.parseInt(st.nextToken());
        int taxiCol = Integer.parseInt(st.nextToken());
        taxi = new Taxi(taxiRaw, taxiCol, 0);
        passengerList = new ArrayList<>();
        for (int idx = 1; idx <= m; idx++) {
            st = new StringTokenizer(br.readLine());
            int passengerRaw = Integer.parseInt(st.nextToken());
            int passengerCol = Integer.parseInt(st.nextToken());
            int destinationRaw = Integer.parseInt(st.nextToken());
            int destinationCol = Integer.parseInt(st.nextToken());
            passengerList.add(new Passenger(idx,
                    new Pos(passengerRaw, passengerCol),
                    new Pos(destinationRaw, destinationCol)));
        }

        while (!passengerList.isEmpty()) {
            Passenger passenger = findPickUpPassenger();
            if (passenger == null) {
                System.out.println(-1);
                return;
            }
            final int distance = getDistanceOfDestination(passenger);
            if (distance == -1) {
                System.out.println(-1);
                return;
            }
            if (fuel - distance < 0) {
                System.out.println(-1);
                return;
            }
            taxi.pos = passenger.destinationPos;
            fuel += (distance * 2) - distance;
        }
        System.out.println(fuel);
    }

    private static int getDistanceOfDestination(Passenger passenger) {
        visit = new boolean[n + 1][n + 1];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(passenger.myPos.x, passenger.myPos.y, 0));
        visit[passenger.myPos.x][passenger.myPos.y] = true;
        while (!q.isEmpty()) {
            final Node curNode = q.poll();
            if (curNode.x == passenger.destinationPos.x && curNode.y == passenger.destinationPos.y) {
                return curNode.distance;
            }
            for (int idx = 0; idx < 4; idx++) {
                int nx = curNode.x + dx[idx];
                int ny = curNode.y + dy[idx];
                if (isRange(nx, ny) && !visit[nx][ny] && board[nx][ny] == 0) {
                    visit[nx][ny] = true;
                    q.add(new Node(nx, ny, curNode.distance + 1));
                }
            }
        }
        return -1;
    }

    private static Passenger findPickUpPassenger() {
        List<Distance> distances = new ArrayList<>();
        for (Passenger passenger : passengerList) {
            int distance = findDistance(passenger);
            if (distance == -1) return null;
            distances.add(new Distance(passenger.idx, passenger.destinationPos, distance));
        }
        Collections.sort(distances, (o1, o2) -> {
            if (o1.distance == o2.distance) {
                return o1.idx - o2.idx;
            }
            return o1.distance - o2.distance;
        });
        final Distance distance = distances.get(0);
        Passenger findPassenger = null;
        for (Passenger passenger : passengerList) {
            if (passenger.idx == distance.idx) {
                findPassenger = passenger;
            }
        }
        if (findPassenger == null) return null;
        passengerList.remove(findPassenger);
        fuel -= distance.distance;
        return findPassenger;
    }

    private static int findDistance(Passenger passenger) {
        visit = new boolean[n + 1][n + 1];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(taxi.pos.x, taxi.pos.y, 0));
        visit[taxi.pos.x][taxi.pos.y] = true;
        while (!q.isEmpty()) {
            final Node curNode = q.poll();
            if (curNode.x == passenger.myPos.x && curNode.y == passenger.myPos.y) {
                return curNode.distance;
            }
            for (int idx = 0; idx < 4; idx++) {
                int nx = dx[idx] + curNode.x;
                int ny = dy[idx] + curNode.y;
                if (isRange(nx, ny) && !visit[nx][ny] && board[nx][ny] == 0) {
                    visit[nx][ny] = true;
                    q.add(new Node(nx, ny, curNode.distance + 1));
                }
            }
        }
        return -1;
    }

    private static boolean isRange(int x, int y) {
        return x > 0 && y > 0 && x <= n && y <= n;
    }

    private static class Node {
        private int x;
        private int y;
        private int distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    private static class Taxi {
        private Pos pos;
        private int consumptionFuel;

        public Taxi(int x, int y, int consumptionFuel) {
            this.pos = new Pos(x, y);
            this.consumptionFuel = consumptionFuel;
        }
    }

    private static class Passenger {
        private int idx;
        private Pos myPos;
        private Pos destinationPos;

        public Passenger(int idx, Pos myPos, Pos destinationPos) {
            this.idx = idx;
            this.myPos = myPos;
            this.destinationPos = destinationPos;
        }
    }

    private static class Pos {
        private int x;
        private int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Distance {
        private int idx;
        private Pos pos;
        private int distance;

        public Distance(int idx, Pos pos, int distance) {
            this.idx = idx;
            this.pos = pos;
            this.distance = distance;
        }
    }
}

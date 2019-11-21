package baekJ;

import java.util.*;

public class bj_4485_다익스트라 {
    private static int n;
    private static int[][] map;
    private static int[][] distance;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static Queue<Node> pq;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = 1;

        while (true) {
            n = sc.nextInt();
            if (n == 0) break;
            map = new int[n][n];
            distance = new int[n][n];
            pq = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
            pq.add(new Node(0, 0, map[0][0]));
            distance[0][0] = 0;
            dijkstra();
            System.out.println("Problem " + cnt++ + ": " + distance[n - 1][n - 1]);
        }
    }

    private static void dijkstra() {
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (isRange(nx, ny)) {
                    if (distance[nx][ny] > node.dist + map[nx][ny]) {
                        distance[nx][ny] = node.dist + map[nx][ny];
                        pq.add(new Node(nx, ny, distance[nx][ny]));
                    }
                }
            }
        }
    }

    private static boolean isRange(int x, int y) {
        if (x >= 0 && y >= 0 && x < n && y < n) {
            return true;
        }
        return false;
    }

    private static class Node implements Comparable<Node> {
        int x, y, dist;

        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist < o.dist ? -1 : 1;
        }
    }
}

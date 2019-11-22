package baekJ;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class bj_1916_다익스트라 {
    private static int busCount, cityCount;
    private static int[][] path;
    private static int[] dist;
    private static int start, end;
    private static int INF = 1000000001;
    private static Queue<Integer> q = new PriorityQueue<>();

    public static void main(String[] args) {
        init();
        dijkstra();
        System.out.println(dist[end]);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        cityCount = sc.nextInt();
        busCount = sc.nextInt();
        path = new int[cityCount + 1][cityCount + 1];
        dist = new int[cityCount + 1];

        for (int i = 1; i <= cityCount; i++) {
            dist[i] = INF;
            for (int j = 1; j <= cityCount; j++) {
                path[i][j] = INF;
            }
        }

        for (int i = 1; i <= busCount; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int distance = sc.nextInt();
            if (path[s][e] > distance) {
                path[s][e] = distance;
            }
        }

        start = sc.nextInt();
        end = sc.nextInt();
    }

    private static void dijkstra() {
        path[start][start] = 0;
        dist[start] = 0;
        q.add(start);

        while (!q.isEmpty()) {
            int next = q.poll();

            for (int i = 1; i <= cityCount; i++) {
                if (dist[i] > path[next][i] + dist[next]) {
                    dist[i] = path[next][i] + dist[next];
                    q.add(i);
                }
            }
        }
    }
}

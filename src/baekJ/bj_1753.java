package baekJ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class bj_1753 {
    private static int distance[];
    private static ArrayList<Node>[] list;
    private static boolean visit[];
    private static PriorityQueue<Node> prq = new PriorityQueue<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        int start = sc.nextInt();
        distance = new int[v + 1];
        visit = new boolean[v + 1];
        list = new ArrayList[v + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);

        for (int i = 1; i <= v; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            list[sc.nextInt()].add(new Node(sc.nextInt(), sc.nextInt()));
        }
        prq.add(new Node(start, 0));
        distance[start] = 0;

        solution();
        for (int i = 1; i <= v; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else System.out.println(distance[i]);
        }
    }

    private static void solution() {
        while (!prq.isEmpty()) {
            Node tmpNode = prq.poll();
            int current = tmpNode.idx;

            if (visit[current]) continue;
            visit[current] = true;

            for (Node node : list[current]) {
                if (distance[node.idx] > distance[current] + node.dis) {
                    distance[node.idx] = distance[current] + node.dis;
                    prq.add(new Node(node.idx, distance[node.idx]));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        int idx, dis;

        Node(int idx, int dis) {
            this.idx = idx;
            this.dis = dis;
        }

        int compatreTo(Node n) {
            return this.dis - dis;
        }

        @Override
        public int compareTo(Node o) {
            return this.dis - o.dis;
        }
    }
}

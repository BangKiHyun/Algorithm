package baekJ;

import java.util.*;

public class bj_6118_다익스트라 {
    private static int n;
    private static List<Integer>[] list;
    private static boolean visit[];
    private static int max = -1;
    private static int distance[];
    private static Queue<Graph> q = new LinkedList<>();

    public static void main(String[] args) {
        init();
        visit[1] = true;
        solution(1, 1);
        int ans[] = getAnswer();

        for (int i : ans) {
            System.out.print(i + " ");
        }
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int m = sc.nextInt();
        list = new ArrayList[n + 1];
        visit = new boolean[n + 1];
        distance = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            list[a].add(b);
            list[b].add(a);
        }
    }

    private static void solution(int start, int dis) {
        for (int i : list[start]) {
            if (!visit[i]) {
                visit[i] = true;
                distance[i] = dis;
                max = Math.max(max, dis);
                q.add(new Graph(i, dis + 1));
            }
        }

        while (!q.isEmpty()) {
            Graph graph = q.poll();
            solution(graph.idx, graph.distance);
        }
    }

    private static int[] getAnswer() {
        int idx = n, same = 0;

        for (int i = 1; i <= n; i++) {
            if (max == distance[i]) {
                same++;
                if (i < idx) {
                    idx = i;
                }
            }
        }

        return new int[]{idx, max, same};
    }

    private static class Graph {
        int idx, distance;

        Graph(int idx, int distance) {
            this.idx = idx;
            this.distance = distance;
        }
    }
}

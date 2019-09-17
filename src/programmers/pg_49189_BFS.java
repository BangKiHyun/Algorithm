package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class pg_49189_BFS {
    static boolean visit[];
    static ArrayList<Integer>[] list;
    static int max = -1;
    static Queue<Node> q = new LinkedList();
    static int arr[];

    public static void main(String[] args) {
        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        int ans = solution(n, edge);
        System.out.println(ans);
    }

    public static int solution(int n, int[][] edge) {
        int ans = 0;
        visit = new boolean[n + 1];
        list = new ArrayList[n + 1];
        arr = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList();
        }

        for (int i = 0; i < edge.length; i++) {
            list[edge[i][0]].add(edge[i][1]);
            list[edge[i][1]].add(edge[i][0]);
        }

        q.add(new Node(1, 0));
        visit[1] = true;

        while (!q.isEmpty()) {
            Node nd = q.poll();
            int cnt = nd.cnt;
            int v = nd.num;

            for (int x : list[v]) {
                if (!visit[x]) {
                    q.add(new Node(x, cnt + 1));
                    visit[x] = true;
                    arr[x] = cnt + 1;
                }
            }
            if (cnt > max) max = cnt;
        }

        for (int i = 1; i <= n; i++) {
            if (max == arr[i]) ans++;
        }
        return ans;
    }

    static class Node {
        int num, cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}

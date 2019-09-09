package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class pg_42861 {
    static ArrayList<Node> list = new ArrayList<Node>();
    static int[] parent;
    static int m;

    public static void main(String[] args) {
        int n = 6;
        //int costs[][] = {{0, 1, 1,}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}, {1, 0, 1}};
        int costs[][] = {{0, 1, 5}, {0, 3, 2}, {0, 4, 3}, {1, 4, 1}, {3, 4, 10}, {1, 2, 2}, {2, 5, 3}, {4, 5, 4}};
        int ans = solution(n, costs);
        System.out.println(ans);
    }

    public static int solution(int n, int[][] costs) {
        init(n, costs);
        sortList();
        int ans = getDistance();
        return ans;

    }

    static int getDistance() {
        int sum = 0;
        for (int i = 0; i < m; i++) {
            if (!findParent(parent, list.get(i).x, list.get(i).y)) {
                sum += list.get(i).d;
                unionParent(parent, list.get(i).x, list.get(i).y);
            }
        }
        return sum;
    }

    static int getParent(int[] p, int x) {
        if (x == p[x]) return x;
        return p[x] = getParent(p, p[x]);
    }

    static void unionParent(int[] p, int x, int y) {
        x = getParent(p, x);
        y = getParent(p, y);
        if (x > y) {
            p[x] = y;
        } else {
            p[y] = x;
        }
    }

    static boolean findParent(int[] p, int x, int y) {
        x = getParent(p, x);
        y = getParent(p, y);

        if (p[x] == p[y]) return true;
        else return false;
    }

    static void sortList() {
        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.d < o2.d) return -1;
                else if (o1.d > o2.d) return 1;
                return 0;
            }
        });
    }

    static void init(int n, int[][] costs) {
        m = costs.length;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < m; i++) {
            list.add(new Node(costs[i][0], costs[i][1], costs[i][2]));
        }
    }

    static class Node {
        int x, y, d;

        Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}

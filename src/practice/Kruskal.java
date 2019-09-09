package practice;

import java.util.*;

//최소비용 신장 트리
public class Kruskal {
    static int getParent(int parent[], int x) {
        if (parent[x] == x) return x;
        return parent[x] = getParent(parent, parent[x]);
    }

    static void unionParent(int parent[], int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);
        if (a > b)
            parent[a] = b;
        else
            parent[b] = a;
    }

    static boolean findParent(int parent[], int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);
        if (a == b)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 7;
        int m = 11;

        int parent[] = new int[n];
        ArrayList<Node> list = new ArrayList<>();
        int sum = 0;

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int dis = sc.nextInt();
            list.add(new Node(a, b, dis));
        }

        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.d < o2.d) {
                    return -1;
                } else if (o1.d > o2.d) {
                    return 1;
                }
                return 0;
            }
        });

        for (int i = 0; i < m; i++) {
            if (!findParent(parent, list.get(i).node[0] - 1, list.get(i).node[1] - 1)) {
                sum += list.get(i).d;
                System.out.print(list.get(i).d + " ");
                unionParent(parent, list.get(i).node[0] - 1, list.get(i).node[1] - 1);
            }
        }
        System.out.println(sum);
    }

    static class Node {
        int node[] = new int[2];
        int d;

        Node(int a, int b, int d) {
            this.node[0] = a;
            this.node[1] = b;
            this.d = d;
        }

        boolean op(Node n) {
            return this.d < n.d;
        }
    }
}

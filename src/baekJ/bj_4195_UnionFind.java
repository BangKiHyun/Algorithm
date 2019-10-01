package baekJ;

import java.util.HashMap;
import java.util.Scanner;

public class bj_4195_UnionFind {
    private static int MAX = 200001;
    private static int parent[] = new int[MAX];
    private static int relation[] = new int[MAX];
    private static HashMap<String, Integer> friends = new HashMap();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int test_case = 0; test_case < t; test_case++) {
            int n = sc.nextInt();
            int idx = 0;

            for (int i = 0; i < MAX; i++) {
                parent[i] = i;
                relation[i] = 1;
            }
            for (int i = 0; i < n; i++) {
                String name1 = sc.next();
                String name2 = sc.next();

                if (!friends.containsKey(name1)) friends.put(name1, ++idx);

                if (!friends.containsKey(name2)) friends.put(name2, ++idx);

                int i1 = friends.get(name1);
                int i2 = friends.get(name2);

                if (!findParent(i1, i2)) {
                    relation[i1] += relation[i2];
                    relation[i2] = relation[i1];
                    unionParent(i1, i2);
                    System.out.println(relation[i1]);
                } else {
                    System.out.println(relation[i1]);
                }
            }
        }
    }

    private static boolean findParent(int a, int b) {
        a = getParent(a);
        b = getParent(b);

        if (a == b) return true;
        else return false;
    }

    private static int getParent(int x) {
        if (parent[x] == x) return x;
        return parent[x] = getParent(parent[x]);
    }

    private static void unionParent(int a, int b) {
        a = getParent(a);
        b = getParent(b);

        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }
}

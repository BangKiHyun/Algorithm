package practice;

import java.util.*;
import java.util.Stack;

import static java.lang.Math.min;

//백준 2150
public class SCC_1 {
    static int MAX = 10001;
    static int id;
    static int d[] = new int[MAX];
    static boolean finished[] = new boolean[MAX];
    static ArrayList<Integer> a[] = new ArrayList[MAX];
    static ArrayList<ArrayList<Integer>> SCC = new ArrayList<>();
    static Stack<Integer> s = new Stack<>();

    static int dfs(int x) {
        d[x] = ++id;
        s.push(x);

        int parent = d[x];
        for (int y : a[x]) {
            if (d[y] == 0) parent = min(parent, dfs(y));
            else if (!finished[y]) parent = min(parent, d[y]);
        }

        if (parent == d[x]) {
            ArrayList<Integer> scc = new ArrayList<>();
            while (true) {
                int t = s.pop();
                scc.add(t);
                finished[t] = true;
                if (t == x) break;
            }
            Collections.sort(scc);
            SCC.add(scc);
        }
        return parent;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v, e;
        v = sc.nextInt();
        e = sc.nextInt();
        for (int i = 1; i <= v; i++) {
            a[i] = new ArrayList<>();
        }
        for (int i = 0; i < e; i++) {
            int x, y;
            x = sc.nextInt();
            y = sc.nextInt();
            a[x].add(y);
        }
        for (int i = 1; i <= v; i++) {
            if (d[i] == 0) dfs(i);
        };
        System.out.println(SCC.size());
        for (int i = 0; i < SCC.size(); i++) {
            for (int j : SCC.get(i)) {
                System.out.print(j + " ");
            }
            System.out.println("-1");
        }
    }
}

/*
package practice;

import java.util.ArrayList;
import java.util.Stack;

import static java.lang.Math.cbrt;
import static java.lang.Math.min;

public class SCC {

    public static final int MAX = 10001;
    static int id;
    static int d[] = new int[MAX];
    static boolean finished[] = new boolean[MAX];
    static ArrayList<Integer> a[] = new ArrayList[MAX];
    static ArrayList<ArrayList<Integer>> SCC;
    static Stack<Integer> s = new Stack<Integer>();

    static int dfs(int x) {
        d[x] = id++;
        s.push(x);

        int parent = d[x];
        for (int i : a[x]) {
            int y = i;
            if (d[y] == 0) parent = min(parent, dfs(y));
            else if (!finished[y]) parent = min(parent, d[y]);
        }

        if (parent == d[x]) {
            ArrayList<Integer> scc = new ArrayList<Integer>();
            while (true) {
                int t = s.peek();
                s.pop();
                scc.add(t);
                finished[t] = true;
                if (t == x) break;
                ;
            }
            SCC.add(scc);
        }
        return parent;
    }

    public static void main(String[] args) {
        int v = 11;
        for (int i = 1; i <= v; i++) {
            a[i] = new ArrayList<Integer>();
            SCC<ArrayList<Integer> = new ArrayList<ArrayList>();
        }
        a[1].add(2);
        a[2].add(3);
        a[3].add(1);
        a[4].add(2);
        a[4].add(5);
        a[5].add(7);
        a[6].add(5);
        a[7].add(6);
        a[8].add(5);
        a[8].add(9);
        a[9].add(10);
        a[10].add(11);
        a[11].add(3);
        a[11].add(8);
        for (int i = 1; i <= v; i++) {
            if (d[i] == 0) dfs(i);
        }
        System.out.println("SCC의 갯수 : " + SCC.size());
    }
}
*/

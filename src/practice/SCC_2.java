package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

import static java.lang.Math.min;

//백준 4196
public class SCC_2 {
    static int MAX = 100001;
    static int d[] = new int[MAX];
    static int id;
    static int n, m;
    static ArrayList<ArrayList<Integer>> SCC = new ArrayList<>();
    static ArrayList<Integer> a[] = new ArrayList[MAX];
    static boolean finished[] = new boolean[MAX];
    static Stack<Integer> s = new Stack<>();
    static int group[] = new int[MAX];
    static boolean inDegree[] = new boolean[MAX];

    static int dfs(int x) {
        d[x] = ++id;
        s.push(x);

        int parent = d[x];
        for (int y : a[x]) {
            if (d[y] == 0) parent = min(parent, dfs(y));
            else if (!finished[y]) parent = min(parent, d[y]);
        }

        if (parent == x) {
            ArrayList<Integer> scc = new ArrayList<>();
            while (true) {
                int t = s.pop();
                scc.add(t);
                group[t] = SCC.size() + 1;
                finished[t] = true;
                if (x == t) break;
            }
            SCC.add(scc);
        }
        return parent;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int k = 0; k < t; k++) {
            SCC.clear();
            Arrays.fill(d, 0);
            Arrays.fill(finished, false);
            Arrays.fill(inDegree, false);
            int n = sc.nextInt();
            int m = sc.nextInt();
            for (int i = 1; i <= n; i++) {
                a[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                a[x].add(y);
            }
            for (int i = 1; i <= n; i++) {
                if (d[i] == 0) dfs(i);
            }
            for (int i = 1; i <= n; i++) {
                for (int y : a[i]) {
                    if (group[i] != group[y])
                        inDegree[group[y]] = true;
                }
            }
            int result = 0;
            for (int i = 1; i <= SCC.size(); i++) {
                if (!inDegree[i]) result++;
            }
            System.out.println(result);
        }
    }
}

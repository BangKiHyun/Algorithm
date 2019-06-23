package practice;

import java.util.*;
import java.util.Stack;

import static java.lang.Math.min;

//백준 3977
public class SCC_3 {
    static int MAX = 100001;
    static int n, m;
    static ArrayList<ArrayList<Integer>> SCC = new ArrayList<>();
    static ArrayList<Integer> a[] = new ArrayList[MAX];
    static boolean inDegree[] = new boolean[MAX];
    static boolean finished[] = new boolean[MAX];
    static int d[] = new int[MAX];
    static int id;
    static Stack<Integer> s = new Stack<>();
    static int group[] = new int[MAX];
    static ArrayList result = new ArrayList();

    static int dfs(int x) {
        s.push(x);
        d[x] = ++id;

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
                group[t] = SCC.size() + 1;
                finished[t] = true;
                if (t == x) break;
            }
            SCC.add(scc);
        }
        return parent;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        for (int i = 0; i < testcase; i++) {
            SCC.clear();
            Arrays.fill(d, 0);
            Arrays.fill(finished, false);
            Arrays.fill(inDegree, false);
            result.clear();
            n = sc.nextInt();
            m = sc.nextInt();
            for (int j = 1; j <= n; j++) {
                a[j] = new ArrayList<>();
            }
            for (int j = 0; j < m; j++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                a[x + 1].add(y + 1);
            }
            for (int j = 1; j <= n; j++) {
                if (d[j] == 0) dfs(j);
            }
            for (int j = 1; j <= n; j++) {
                for (int k : a[j]) {
                    if (group[j] != group[k])
                        inDegree[group[k]] = true;
                }
            }
            int cnt = 0;
            for (int j = 0; j < SCC.size(); j++) {
                if (!inDegree[j + 1]) {
                    cnt++;
                    for (int k : SCC.get(j)) {
                        result.add(k - 1);
                    }
                }
            }
            Collections.sort(result);
            if (cnt != 1) {
                System.out.println("Confused");
            } else {
                for (int j = 0; j < result.size(); j++) {
                    System.out.println(result.get(j));
                }
            }
        }
    }
}

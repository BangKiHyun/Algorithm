package practice;

import java.util.ArrayList;
import java.util.Arrays;

public class Bipartite_Matching {
    static int MAX = 101;
    static ArrayList<Integer> a[] = new ArrayList[MAX];
    static int d[] = new int[MAX];
    static boolean c[] = new boolean[MAX];
    static int n = 3, m;

    static boolean dfs(int x) {
        for (int t : a[x]) {
            if (c[t]) continue;
            c[t] = true;
            if (d[t] == 0 || dfs(d[t])) {
                d[t] = x;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        a[1].add(1);
        a[1].add(2);
        a[1].add(3);
        a[2].add(1);
        a[3].add(2);
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            Arrays.fill(c, false);
            if(dfs(i)) cnt++;
        }
        System.out.print(cnt + "개의 매칭이 이루어졌습니다.");
    }
}

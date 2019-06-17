package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//백준 11376
public class Bipartite_Matching_2 {
    static int MAX = 1001;
    static ArrayList<Integer>[] a = new ArrayList[MAX];
    static boolean c[] = new boolean[MAX];
    static int[] d = new int[MAX];
    static int n, m;

    static boolean dfs(int x) {
        for (int y : a[x]) {
            if (c[y]) continue;
            c[y] = true;
            if (d[y] == 0 || dfs(d[y])) {
                d[y] = x;
                return true;
            }
        }
        return false;
    }

    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= m; i++) {
            a[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            int k = sc.nextInt();
            for (int j = 1; j <= k; j++) {
                int y = sc.nextInt();
                a[i].add(y);
            }
        }
        int cnt = 0;
        for (int k = 0; k < 2; k++) {
            for (int i = 1; i <= n; i++) {
                Arrays.fill(c, false);
                if (dfs(i)) cnt++;
            }
        }
        System.out.print(cnt + " ");
    }
}

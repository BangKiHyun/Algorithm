package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//백준 1671
public class Bipartite_Matching_3 {
    static int MAX = 1001;
    static ArrayList<Integer>[] a = new ArrayList[MAX];
    static int d[] = new int[MAX];
    static boolean c[] = new boolean[MAX];
    static int n;
    static int stat[][] = new int[MAX][3];

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            a = stat[i][0];
            b = stat[i][1];
            c = stat[i][2];
        }
        for (int i = 1; i <= n - 1; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (stat[i][0] == stat[j][0] && stat[i][1] == stat[j][1] && stat[i][2] == stat[j][2]) {
                    a[i].add(j);
                } else if (stat[i][0] >= stat[j][0] && stat[i][1] >= stat[j][1] && stat[i][2] >= stat[j][2]) {
                    a[i].add(j);
                } else if (stat[i][0] <= stat[j][0] && stat[i][1] <= stat[j][1] && stat[i][2] <= stat[j][2]) {
                    a[j].add(i);
                }
            }
        }
        int cnt = 0;
        for (int k = 0; k < 2; k++) {
            for (int i = 1; i <= n; i++) {
                Arrays.fill(c, false);
                if (dfs(i)) cnt++;
            }
        }
        System.out.println(n - cnt + " ");
    }
}

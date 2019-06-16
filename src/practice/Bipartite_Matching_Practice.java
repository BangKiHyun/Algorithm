package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//백준 2188
public class Bipartite_Matching_Practice {
    static int MAX = 201;
    static ArrayList<Integer>[] a = new ArrayList[MAX];
    static boolean c[] = new boolean[MAX];
    static int[] d = new int[MAX];
    static int n, m, s;

    static boolean dfs(int x){
        for(int t : a[x]){
            if(c[t]) continue;
            c[t] = true;
            if(d[t] == 0 || dfs(d[t])){
                d[t] = x;
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
            s = sc.nextInt();
            for (int j = 1; j <= s; j++) {
                int t = sc.nextInt();
                a[i].add(t);
            }
        }
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            Arrays.fill(c, false);
            if (dfs(i)) cnt++;
        }
        System.out.print(cnt + " ");
    }
}

package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class DFS_Searching {
    static int MAX = 30;
    static boolean c[] = new boolean[MAX];
    static ArrayList<Integer> a[] = new ArrayList[MAX];

    static void dfs(int x) {
        if (c[x]) return;
        c[x] = true;
        System.out.print(x + " ");
        for (int i : a[x]) {
            dfs(i);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        int start = sc.nextInt();

        for (int i = 1; i <= v; i++) {
            a[i] = new ArrayList<>();
        }
        for(int i=0;i<e;i++){
            int x =sc.nextInt();
            int y = sc.nextInt();
            a[x].add(y);
            a[y].add(x);
        }
        dfs(start);
    }
}

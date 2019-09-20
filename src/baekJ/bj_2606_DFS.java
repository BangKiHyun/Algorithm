package baekJ;

import java.util.ArrayList;
import java.util.Scanner;

public class bj_2606_DFS {
    static int computerNum;
    static int connectNum;
    static ArrayList<Integer> connectList[];
    static boolean visit[];
    static int ans = -1;

    public static void main(String[] args) {
        init();
        dfs(1);
        System.out.println(ans);
    }

    static void dfs(int x) {
        if (visit[x]) return;
        visit[x] = true;
        ans++;
        for (int i : connectList[x]) {
            dfs(i);
        }
    }

    static void init() {
        Scanner sc = new Scanner(System.in);
        computerNum = sc.nextInt();
        connectNum = sc.nextInt();
        visit = new boolean[computerNum + 1];
        connectList = new ArrayList[computerNum + 1];
        for (int i = 1; i <= computerNum; i++) {
            connectList[i] = new ArrayList<>();
        }

        for (int i = 1; i <= connectNum; i++) {
            int c1 = sc.nextInt();
            int c2 = sc.nextInt();
            connectList[c1].add(c2);
            connectList[c2].add(c1);
        }
    }
}

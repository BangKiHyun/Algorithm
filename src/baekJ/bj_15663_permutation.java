package baekJ;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class bj_15663_permutation {
    private static HashSet<String> hashSet = new HashSet<>();
    private static int n, m;
    private static int[] list;
    private static boolean visit[];

    public static void main(String[] args) {
        init();
        permutate(0, "");
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        list = new int[n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            list[i] = sc.nextInt();
        }

        Arrays.sort(list);
    }

    private static void permutate(int cnt, String s) {
        if (cnt == m && !hashSet.contains(s)) {
            System.out.println(s);
            hashSet.add(s);
        }

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                permutate(cnt + 1, s + list[i] + " ");
                visit[i] = false;
            }
        }
    }
}

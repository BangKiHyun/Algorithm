package baekJ;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class bj_15664_Combination {
    private static int n, m;
    private static int[] list;
    private static HashSet<String> hashSet = new HashSet<>();

    public static void main(String[] args) {
        init();
        Arrays.sort(list);
        combination(0, 0, "");
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        list = new int[n];

        for (int i = 0; i < n; i++) {
            list[i] = sc.nextInt();
        }
    }

    private static void combination(int start, int cnt, String ans) {
        if (cnt == m && !hashSet.contains(ans)) {
            hashSet.add(ans);
            System.out.println(ans);
            return;
        }

        for (int i = start; i < n; i++) {
            if (cnt < m) {
                combination(i + 1, cnt + 1, ans + list[i] + " ");
            }
        }
    }
}

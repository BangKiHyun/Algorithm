package baekJ;

import java.util.LinkedList;
import java.util.Scanner;

public class bj_15649_permutation {
    private static int n, m;
    private static boolean[] visit = new boolean[10];
    private static LinkedList<Integer> list = new LinkedList<>();

    public static void main(String[] args) {
        init();
        permutate();
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
    }

    private static void permutate() {
        if (list.size() == m) {
            for (int i : list)
                System.out.print(i + " ");
            System.out.println();
        }

        for (int i = 1; i <= n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                list.add(i);
                permutate();
                visit[i] = false;
                list.pollLast();
            }
        }
    }
}

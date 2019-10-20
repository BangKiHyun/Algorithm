package baekJ;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class bj_15654_permutation {
    private static int n, m;
    private static boolean visit[];
    private static int list[];
    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        init();
        permutate();
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

    private static void permutate() {
        if (stack.size() == m) {
            for (int i : stack) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                stack.add(list[i]);
                visit[i] = true;
                permutate();
                stack.pop();
                visit[i] = false;
            }
        }
    }
}

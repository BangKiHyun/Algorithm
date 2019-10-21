package baekJ;

import java.util.Scanner;
import java.util.Stack;

public class bj_15650_Combination {
    private static int n, m;
    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        init();
        combination(1);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
    }

    private static void combination(int start) {
        if (stack.size() == m) {
            for (int i : stack) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i <= n; i++) {
            if (stack.size() < m) {
                stack.push(i);
                combination(i + 1);
                stack.pop();
            }
        }
    }
}

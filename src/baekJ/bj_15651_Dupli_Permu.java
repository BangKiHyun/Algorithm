package baekJ;

import java.util.Scanner;
import java.util.Stack;

public class bj_15651_Dupli_Permu {

    private static int n, m;
    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        init();
        dupAndPermu();
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
    }

    private static void dupAndPermu() {
        if (stack.size() == m) {
            for (int i : stack) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (stack.size() < m) {
                stack.push(i);
                dupAndPermu();
                stack.pop();
            }
        }
    }
}

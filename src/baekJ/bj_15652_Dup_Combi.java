package baekJ;

import java.util.Scanner;
import java.util.Stack;

public class bj_15652_Dup_Combi {
    private static int n, m;
    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        init();
        DupAndCombi(1);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
    }

    private static void DupAndCombi(int start) {
        if (stack.size() == m) {
            for (int i : stack) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        for (int i = start; i <= n; i++) {
            if (stack.size() < m ) {
                stack.push(i);
                DupAndCombi(i);
                stack.pop();
            }
        }
    }
}
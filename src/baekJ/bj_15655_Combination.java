package baekJ;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class bj_15655_Combination {
    private static int n, m;
    private static int[] list;
    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        init();
        Arrays.sort(list);
        combination(0, 0);
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

    private static void combination(int start, int cnt) {
        if (cnt == m) {
            for (int i : stack) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i < n; i++) {
            if (stack.size() < m) {
                stack.push(list[i]);
                combination(i + 1, cnt + 1);
                stack.pop();
            }
        }
    }
}

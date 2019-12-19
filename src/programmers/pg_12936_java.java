package programmers;

import java.util.LinkedList;

public class pg_12936_java {
    private static boolean[] check = new boolean[21];
    private static int cnt = 0;
    private static LinkedList<Integer> list = new LinkedList<>();

    public static void main(String[] args) {
        int n = 9;
        int k = 5;

        for (int i : solution(n, k)) {
            System.out.print(i + " ");
        }
    }

    private static int[] solution(int n, int k) {
        permucomb(n, k, 0);
        int[] ans = new int[n];
        int idx = 0;
        for (int i : list) {
            ans[idx] = i;
            idx++;
        }
        return ans;
    }

    private static void permucomb(int n, int k, int depth) {
        if (depth == n) {
            cnt++;
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!check[i]) {
                check[i] = true;
                list.add(i);
                permucomb(n, k, depth + 1);
                if (cnt == k) return;
                list.pollLast();
                check[i] = false;
            }
        }
    }
}

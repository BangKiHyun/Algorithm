package programmers;

import java.util.ArrayList;
import java.util.List;

public class pg_12936_Re {
    public static void main(String[] args) {
        int n = 3;
        int k = 5;

        for (int i : solution(n, k)) {
            System.out.print(i + " ");
        }
    }

    private static int[] solution(int n, long k) {
        int[] ans = new int[n];

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        int idx = 0;
        k--;
        while (n > 0) {
            long factorial = getFactorial(n);
            long caseCount = (factorial / n--);

            ans[idx++] = list.get((int) (k / caseCount));
            list.remove( (int) (k / caseCount));
            k %= caseCount;
        }

        return ans;
    }

    private static long getFactorial(final int num) {
        long mul = 1;
        for (int i = 2; i <= num; i++) {
            mul *= i;
        }

        return mul;
    }
}

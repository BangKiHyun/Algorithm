package programmers;

import java.util.LinkedList;
import java.util.List;

public class pg_12936 {
    public static void main(String[] args) {
        int n = 3;
        long k = 5;
        int ans[] = solution(n, k);
        for (int i : ans) {
            System.out.print(i + " ");
        }
    }

    public static int[] solution(int n, long k) {
        int[] ans = new int[n];
        int idx = 0;
        int fac = 1;
        int m = 0;
        List<Integer> line = new LinkedList();
        for (int i = 1; i <= n; i++) {
            line.add(i);
            fac *= i;
        }
        k--;

        while (n > 0) {
            fac /= n--;
            m = (int) (k / fac);
            ans[idx] = line.get(m);
            line.remove(m);
            k %= fac;
            idx++;
        }
        return ans;
    }
}

package programmers;

import java.util.Arrays;

public class pg_42860_Greedy {
    public static void main(String[] args) {
        String name = "JAN";
        int ans = solution(name);
        System.out.println(ans);
    }

    private static int solution(String name) {

        int lcnt = 0, rcnt = 0, idx = 0;
        char[] larr = new char[name.length()];
        char[] rarr = new char[name.length()];
        Arrays.fill(larr, 'A');
        Arrays.fill(rarr, 'A');

        if (name.charAt(0) != 'A') {
            int cnt = (name.charAt(0) - 'A' <= 13 ? name.charAt(0) - 'A' : 91 - name.charAt(0));

            rarr[0] = name.charAt(0);
            larr[0] = name.charAt(0);
            rcnt = lcnt = cnt;
            idx = 1;
        }

        for (int i = idx, j = name.length() - 1; i < name.length(); i++, j--) {
            if (!String.copyValueOf(rarr).equals(name)) {
                rcnt++;
                if (name.charAt(i) - 'A' <= 13) rcnt += name.charAt(i) - 'A';
                else rcnt += 91 - name.charAt(i);
                rarr[i] = name.charAt(i);
            }

            if (!String.copyValueOf(larr).equals(name) && j > 0) {
                lcnt++;
                if (name.charAt(j) - 'A' <= 13) lcnt += name.charAt(j) - 'A';
                else lcnt += 91 - name.charAt(j);
                larr[j] = name.charAt(j);
            }
        }
        return Math.min(lcnt, rcnt);
    }
}
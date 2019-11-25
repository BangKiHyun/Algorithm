package baekJ;

import java.util.Arrays;
import java.util.Scanner;

public class bj_1759_백트래킹 {
    private static boolean visit[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String[] candidate = new String[m];
        visit = new boolean[m];

        for (int i = 0; i < m; i++) {
            candidate[i] = sc.next();
        }

        Arrays.sort(candidate);
        searchSecret(n, m, candidate, 0, "", 0, 0, 0);
    }

    private static void searchSecret(int n, int m, String[] candidate, int depth, String secret, int start, int mo, int ja) {
        if (depth == n && mo >= 1 && ja >= 2) {
            System.out.println(secret);
        }

        for (int i = start; i < m; i++) {
            if (!visit[i]) {
                visit[i] = true;
                if (isMo(candidate[i])) {
                    searchSecret(n, m, candidate, depth + 1, secret + candidate[i], i + 1, mo + 1, ja);

                } else {
                    searchSecret(n, m, candidate, depth + 1, secret + candidate[i], i + 1, mo, ja + 1);

                }
                visit[i] = false;
            }
        }
    }

    private static boolean isMo(String candidate) {
        switch (candidate) {
            case "a":
            case "e":
            case "i":
            case "o":
            case "u":
                return true;
        }
        return false;
    }
}

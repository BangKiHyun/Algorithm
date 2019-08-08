package problem;

import java.util.Scanner;

class bj_14501 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int day[] = new int[n + 1];
        int pay[] = new int[n + 1];
        int dp[] = new int[n + 1];

        int max = 0;

        for (int i = 1; i <= n; i++) {
            day[i] = sc.nextInt();
            pay[i] = sc.nextInt();

            dp[i] = pay[i];
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (i - j >= day[j]) {
                    dp[i] = Math.max(pay[i] + dp[j], dp[i]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (i + day[i] <= n + 1) {
                if (max < dp[i]) {
                    max = dp[i];
                }
            }
        }
        System.out.println(max);
    }
}
package baekJ;

import java.util.Scanner;

public class bj_2875_Greedy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        int team = 0;

        while (n >= 2 && m >= 1 && n + m >= 3 + k) {
            n -= 2;
            m -= 1;
            team++;
        }

        System.out.println(team);
    }
}

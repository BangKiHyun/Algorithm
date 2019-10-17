package baekJ;

import java.util.Arrays;
import java.util.Scanner;

public class bj_10610_Greedy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.next();
        String num[] = line.split("");

        int len = num.length;
        Arrays.sort(num);

        if (!num[0].equals("0")) {
            System.out.println(-1);
            return;
        }

        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += Integer.parseInt(num[i]);
        }

        if (sum % 3 != 0) {
            System.out.println(-1);
        } else {
            for (int i = len - 1; i >= 0; i--) {
                System.out.print(num[i]);
            }
        }
    }
}

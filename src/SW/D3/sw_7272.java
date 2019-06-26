package SW.D3;

import java.util.Arrays;
import java.util.Scanner;

public class sw_7272 {
    static void result(String x, String y) {
        int s1 = x.length();
        int s2 = y.length();
        if (s1 != s2) {
            System.out.println("DIFF");
            return;
        }
        char[] c1 = x.toCharArray();
        char[] c2 = y.toCharArray();
        change(c1);
        change(c2);
        if (Arrays.equals(c1, c2))
            System.out.println("SAME");
        else
            System.out.println("DIFF");
    }

    static void change(char[] text) {
        for (int i = 0; i < text.length; i++) {
            if (text[i] == 'A' || text[i] == 'D' || text[i] == 'O' || text[i] == 'P' || text[i] == 'Q' || text[i] == 'R') {
                text[i] = '1';
            } else if (text[i] == 'B') {
                text[i] = '2';
            } else {
                text[i] = '0';
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        String x, y;
        for (int i = 1; i <= t; i++) {
            System.out.print("#" + i + " ");
            x = sc.next();
            y = sc.next();
            result(x, y);
        }
    }
}

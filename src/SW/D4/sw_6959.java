package SW.D4;

import java.util.Scanner;

public class sw_6959 {
    static String ans, s;
    static int size;
    static char c[];
    static boolean check;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            check = true;
            s = sc.next();
            size = s.length();
            c = new char[size];
            c = s.toCharArray();
            solution();
            if (check) {
                ans = "B";
            } else {
                ans = "A";
            }
            System.out.println("#" + test_case + " " + ans);
        }
        sc.close();
    }

    static void solution() {
        if (size == 1) {
            return;
        }
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                return;
            }
            int n1 = c[i] - '0';
            int n2 = c[i + 1] - '0';
            int sum = n1 + n2;
            if (sum >= 10) {
                c[i] = (char) (sum / 10 + '0');
                c[i + 1] = (char) (sum % 10 + '0');
                i--;
                check = !check;
            } else {
                c[i + 1] = (char) (sum + '0');
                check = !check;
            }
        }
    }
}

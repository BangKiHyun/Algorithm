package SW.D4;

import java.util.Scanner;

public class sw_3459 {
    static String name[] = {"Bob", "Alice"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        long x = 2;
        int idx = 1;
        for (int test_case = 1; test_case <= T; test_case++) {
            System.out.println("#" + test_case);
            long n = sc.nextLong();
            for (long i = x; i < x * 2; i++) {
                if (n < i) {
                    System.out.println(idx < 0 ? name[1] : name[0]);
                    idx *= -1;
                    x *= 2;
                    break;
                } else if (n > i && i == x * 2 - 1) {
                    System.out.println(idx > 0 ? name[1] : name[0]);
                    idx *= -1;
                    x *= 2;
                    break;
                }
            }
        }
    }
}

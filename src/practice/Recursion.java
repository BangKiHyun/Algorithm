package practice;

import java.util.Scanner;

public class Recursion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int test_case;
        int T;
        int num;
        double value;

        T = sc.nextInt();

        for (test_case = 1; test_case <= T; test_case++) {
            num = sc.nextInt();
            value = factorial(num);
            System.out.println(value);
        }

    }

    static double factorial(int num) {
        if (num == 0) {
            return 1;
        } else {
            return num * factorial(num - 1);
        }

    }
}

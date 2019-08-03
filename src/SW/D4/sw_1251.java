package SW.D4;

import java.util.Scanner;

public class sw_1251 {
    static int x[];
    static int y[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            x = new int[n];
            y = new int[n];
            for (int i = 0; i < n; i++) {
                x[i] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                y[i] = sc.nextInt();
            }
        }
    }
}

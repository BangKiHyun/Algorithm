package SW.D4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class sw_6719 {
    static int N, K, m1, m2;
    static double[] a;
    static double sum;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int test_case = 1; test_case <= t; test_case++) {
            sum = 0;
            N = sc.nextInt();
            K = sc.nextInt();
            a = new double[N];
            for (int i = 0; i < N; i++) {
                a[i] = sc.nextDouble();
            }
            Arrays.sort(a);
            if (K == 1) sum = a[N - 1] / 2;
            else {
                for (int i = N-K; i < N; i++) {
                    sum = (sum + a[i]) / 2;
                }
            }
            System.out.println("#" + test_case + " " + sum);
        }
    }
}

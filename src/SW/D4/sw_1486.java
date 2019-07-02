package SW.D4;

import java.util.Arrays;
import java.util.Scanner;

public class sw_1486 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int a[];
        for (int test_case = 1; test_case <= t; test_case++) {
            int N = sc.nextInt();
            int B = sc.nextInt();
            int sum = 0;
            int result = 0;
            a = new int[N];
            for (int i = 0; i < N; i++) {
                a[i] = sc.nextInt();
            }
            Arrays.sort(a);
            for (int i = 0; i < N; i++) {
                sum += a[i];
                if (sum >= B) break;
            }
            result = sum - B;
            System.out.println("#" + test_case + " " + result);
        }
    }
}

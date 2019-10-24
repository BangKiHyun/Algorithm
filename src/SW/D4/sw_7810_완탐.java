package SW.D4;

import java.util.Arrays;
import java.util.Scanner;

public class sw_7810_완탐 {
    private static int n;
    private static int[] arrNum;
    private static int max = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            n = sc.nextInt();
            arrNum = new int[n];

            for (int i = 0; i < n; i++) {
                arrNum[i] = sc.nextInt();
                Math.max(max, arrNum[i]);
            }

            Arrays.sort(arrNum);
            findHNum();

            System.out.println("#" + test_case + " " + findHNum());
        }
    }

    private static int findHNum() {
        int max_h_num = 0;

        for (int i = 1; i <= n; i++) {
            if (arrNum[n - i] >= i) {
                max_h_num = i;
            }
        }
        return max_h_num;
    }
}

package SW.D4;

import java.util.Arrays;
import java.util.Scanner;

public class sw_8567_시뮬 {
    private static int PrimeCount[][] = new int[100001][2];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        initPrimeCount();
        findMaxCountOfPrime();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            System.out.println("#" + test_case + " " + PrimeCount[n][1]);
        }
    }

    private static void findMaxCountOfPrime() {
        int idx = 1;
        PrimeCount[1][1] = 1;
        for (int i = 2; i < PrimeCount.length; i++) {
            for (int j = i; j < PrimeCount.length; j = j + i) {
                PrimeCount[j][0]++;

                if (PrimeCount[i][0] > PrimeCount[idx][0]) {
                    idx = i;
                } else if (PrimeCount[i][0] == PrimeCount[idx][0]) {
                    idx = (i > idx) ? i : idx;
                }

                PrimeCount[i][1] = idx;
            }
        }
    }

    private static void initPrimeCount() {
        Arrays.fill(PrimeCount[0], 1);
    }
}

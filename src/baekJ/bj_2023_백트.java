package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_2023_백트 {
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        backtracking("", 0, n);
        System.out.println(sb.toString());
    }

    private static void backtracking(String ans, int len, int max) {
        if (max == len) {
            sb.append(ans + "\n");
        } else {
            for (int i = 1; i <= 9; i++) {
                if (isPrimeNumber(Integer.parseInt(ans + i))) {
                    backtracking(ans + i, len + 1, max);
                }
            }
        }
    }

    private static boolean isPrimeNumber(int num) {
        if (num == 1) return false;

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }

        return true;
    }
}

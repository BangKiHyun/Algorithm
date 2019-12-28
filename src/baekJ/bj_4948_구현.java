package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_4948_구현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            int cnt = 0;
            int start = n + 1;
            int end = 2 * n;
            for (int i = start; i <= end; i++) {
                if (isPrimeNumber(i)) cnt++;
            }
            System.out.println(cnt);
        }
    }

    private static boolean isPrimeNumber(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}

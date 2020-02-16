package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_1789_구현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        long sum = 0;
        long plusCnt = 1;

        while (sum < n) {
            sum += plusCnt++;
        }

        if (sum > n) plusCnt--;

        System.out.println(--plusCnt);
    }
}

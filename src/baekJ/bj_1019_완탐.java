package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_1019_완탐 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] cnt = new int[10];

        for (int i = 1; i <= n; i++) {
            int num = i;
            while (num != 0) {
                int measure = num % 10;
                cnt[measure]++;
                num /= 10;
            }
        }

        for(int i : cnt){
            System.out.print(i + " ");
        }
        System.out.println((System.currentTimeMillis() - start) / 1000.0);
    }
}

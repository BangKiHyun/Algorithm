package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_1789_구현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Integer.parseInt(br.readLine());
        long sum = 0;
        long plus = 1;
        while (n >= sum){
            sum += plus++;
        }
        System.out.println((plus - 2));
    }
}

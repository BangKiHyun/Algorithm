package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_2042_구현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");

        int n = Integer.parseInt(line[0]);
        int[] num = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        int m = Integer.parseInt(line[1]);
        int k = Integer.parseInt(line[2]);
        for (int i = 0; i < m + k; i++) {
            line = br.readLine().split(" ");
            int b = Integer.parseInt(line[1]);
            int c = Integer.parseInt(line[2]);
            if (isChange(line[0])) {
                changeNum(num, b, c);
            } else {
                System.out.println(getSum(num, b, c));
            }
        }
    }

    private static boolean isChange(String str) {
        return str.equals("1");
    }

    private static void changeNum(int[] num, int idx, int postNum) {
        num[idx] = postNum;
        return;
    }

    private static long getSum(int[] num, int start, int end) {
        long sum = 0;
        for (int i = start; i <= end; i++) {
            sum += num[i];
        }
        return sum;
    }
}

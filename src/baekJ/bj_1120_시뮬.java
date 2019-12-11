package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_1120_시뮬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] text = br.readLine().split(" ");
        String A = text[0];
        String B = text[1];
        int min_diff = A.length();

        for (int i = 0; i <= B.length() - A.length(); i++) {
            String temp = B.substring(i, A.length() + i);
            int diff_cnt = getDiff(A, temp);
            min_diff = getMin(min_diff, diff_cnt);
        }

        System.out.println(min_diff);
    }

    private static int getDiff(String A, String B) {
        int cnt = 0;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) != B.charAt(i)) cnt++;
        }
        return cnt;
    }

    private static int getMin(int origin, int diff) {
        return Math.min(origin, diff);
    }
}

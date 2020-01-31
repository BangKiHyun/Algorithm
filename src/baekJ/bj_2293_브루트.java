package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_2293_브루트 {
    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] money = new int[n];
        for (int i = 0; i < n; i++) {
            money[i] = Integer.parseInt(br.readLine());
        }

        goBrute(money, k, 0, 0);

        System.out.println(ans);
    }

    private static void goBrute(int[] money, int target, int total, int start) {
        if (total > target) return;
        if (total == target) {
            ans++;
            return;
        }

        for (int i = start; i < money.length; i++) {
            goBrute(money, target, total + money[i], i);
        }
    }
}

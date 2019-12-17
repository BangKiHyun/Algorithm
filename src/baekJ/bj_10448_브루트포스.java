package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_10448_브루트포스 {
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] num_list = new int[1000];
        num_list[1] = 1;
        for (int i = 2; i <= 1000; i++) {
            num_list[i] = num_list[i - 1] + i;
            if (num_list[i] >= 1000) break;
        }

        for (int i = 1; i <= T; i++) {
            int num = Integer.parseInt(br.readLine());
            goPermu(num, 3, num_list, 0, 0);
            System.out.println(result);
            result = 0;
        }
    }

    private static void goPermu(int num, int depth, int[] list, int cnt, int ans) {
        if (result == 1) return;

        if (depth == cnt) {
            if (num == ans) result = 1;
            return;
        }

        for (int i = 1; i < 1000; i++) {
            if (list[i] > num) return;
            if (cnt == 0) ans = list[i];
            else ans += list[i];
            goPermu(num, depth, list, cnt + 1, ans);
            if (cnt != 0) ans -= list[i];
        }
    }
}

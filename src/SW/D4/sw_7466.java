package SW.D4;

import java.util.Scanner;

public class sw_7466 {
    static int n, k;
    static int ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            n = sc.nextInt();
            k = sc.nextInt();
            if (n > k) {
                ans = k;
            } else {
                find(n, k, 1);
            }
            System.out.println("#" + test_case + " " + ans);
        }
    }

    static void find(int n, int k, int mul) {
        while (true) {
            if (n == 1 || k == 1) break;
            if (k % n == 0) {
                mul *= n;
                k /= n;
                if (n >= k) n = k;
                else n -= 1;
                ans = mul;
                if (n == 1) return;
            } else {
                n -= 1;
            }
        }
    }
}

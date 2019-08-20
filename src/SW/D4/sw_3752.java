package SW.D4;

import java.util.Scanner;

public class sw_3752 {
    static int N;
    static int a[];
    static int sum;
    static boolean c[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            sum = 0;
            a = new int[101];
            c = new boolean[10001];

            for (int i = 0; i < N; i++) {
                a[i] = sc.nextInt();
                sum += a[i];
            }
            int ans = dp();
            System.out.println("#" + test_case + " " + ans);
        }
        sc.close();
    }

    static int dp() {
        c[0] = true;
        for (int i = 0; i < N; i++) {
            for (int j = sum; j >= 0; j--) {
                if (c[j]) {
                    c[a[i] + j] = true;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= sum; i++) {
            if (c[i]) ans++;
        }
        return ans;
    }
}

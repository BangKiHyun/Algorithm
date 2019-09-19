package baekJ;

import java.util.Scanner;

public class bj_2231_브루트포스 {
    static int N;

    public static void main(String[] args) {
        init();
        int ans = solution();
        System.out.println(ans);
    }

    public static int solution() {
        int tmp = 0;
        for (int i = N / 2; i <= N; i++) {
            int sum = 0;
            tmp = i;
            sum += i;
            while (true) {
                if (i != 0) {
                    int mok = i / 10;
                    int remain = i % 10;
                    sum += remain;
                    i = mok;
                } else break;
            }
            if (sum == N) {
                return tmp;
            }
            i = tmp;
        }
        return 0;
    }

    public static void init() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
    }
}

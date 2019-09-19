package baekJ;

import java.util.Scanner;

public class bj_2798_브루트포스 {
    static int n;
    static int m;
    static int card[];
    static int ans;
    static boolean visit[];

    public static void main(String[] args) {
        init();
        solution(0, 0, 0);
        System.out.println(ans);
    }

    static void solution(int start, int sum, int cnt) {
        if (cnt == 3 && sum <= m) {
            if (m - sum < m - ans) {
                ans = sum;
            }
            return;
        }else if(cnt > 3 || sum > m) return;

        for (int i = start; i < n; i++) {
            if(visit[i]) continue;
            visit[i] = true;
            sum += card[i];
            solution(start + 1, sum, cnt + 1);
            visit[i] = false;
            sum -= card[i];
        }
    }

    static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        card = new int[n];
        visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            card[i] = sc.nextInt();
        }
    }
}

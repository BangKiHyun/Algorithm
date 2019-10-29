package baekJ;

import java.util.Scanner;

public class bj_6603_DFS {
    private static int k;
    private static int[] lotto;
    private static boolean[] visit;
    private static int DIGIT_LOTTO = 6;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while ((k = sc.nextInt()) != 0) {
            init();
            goDFS(0, 0, "");
            System.out.println();
        }
    }

    private static void init() {
        lotto = new int[k];
        visit = new boolean[k];
        for (int i = 0; i < k; i++) {
            lotto[i] = sc.nextInt();
        }
    }

    private static void goDFS(int start, int cnt, String ans) {
        if (cnt == DIGIT_LOTTO) {
            System.out.println(ans);
            return;
        }

        for (int i = start; i < k; i++) {
            if (!visit[i]) {
                visit[i] = true;
                goDFS(i + 1, cnt + 1, ans + lotto[i] + " ");
                visit[i] = false;
            }
        }
    }
}

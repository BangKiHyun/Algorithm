package problem;

import java.util.Scanner;

public class bj_17136 {
    static int map[][] = new int[10][10];
    static int[] paper = {0, 5, 5, 5, 5, 5};
    static int oncCnt = 0;
    static int min = 987654321;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = sc.nextInt();
                oncCnt += map[i][j];
            }
        }

        dfs(0, 0, 0);
        System.out.println(min == 987654321 ? -1 : min);
    }

    static void dfs(int x, int cnt, int total) {
        if (min <= cnt) return;
        if (oncCnt == total) {
            min = Math.min(min, cnt);
            return;
        }
        for (int i = x; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j] == 1) {
                    boolean flag = false;
                    for (int k = 5; k > 0; k--) {
                        if (i + k <= 10 && j + k <= 10 && paper[k] > 0) {
                            if (!flag) {
                                flag = check(i, j, k);
                            }
                            if (flag) {
                                setPaper(i, j, k);
                                paper[k]--;
                                dfs(i, cnt + 1, total + k * k);
                                paper[k]++;
                                setPaper(i, j, k);
                            }
                        }
                    }
                    if (!flag) return;
                }
                if (map[i][j] == 1) return;
            }
        }
    }

    static boolean check(int x, int y, int k) {
        for (int i = x; i < x + k; i++) {
            for (int j = y; j < y + k; j++) {
                if (map[i][j] == 0) return false;
            }
        }
        return true;
    }

    static void setPaper(int x, int y, int k) {
        for (int i = x; i < x + k; i++) {
            for (int j = y; j < y + k; j++) {
                map[i][j] = map[i][j] ^ 1;
            }
        }
    }
}

package problem;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class bj_17471_브루트포스_Re {
    private static int n;
    private static int[] people;
    private static List<Integer> list[];
    private static boolean visit[];
    private static boolean check[];
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        init();
        permucomb(1);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        people = new int[n + 1];
        list = new LinkedList[n + 1];
        visit = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            people[i] = sc.nextInt();
            list[i] = new LinkedList<>();
        }

        for (int i = 1; i <= n; i++) {
            int adj = sc.nextInt();
            for (int j = 1; j <= adj; j++) {
                list[i].add(sc.nextInt());
            }
        }
    }

    private static void permucomb(int start) {
        if (isValid()) {
            min = Math.min(min, getMinNumber());
        }

        for (int i = start; i <= n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                permucomb(i + 1);
                visit[i] = false;
            }
        }
    }

    private static boolean isValid() {
        check = new boolean[n + 1];
        int areaCount = 0, area1 = 0, area2 = 0;

        for (int i = 1; i <= n; i++) {
            if (areaCount > 2) return false;

            if (visit[i] == true) {
                if (check[i]) continue;
                area1++;
                check[i] = true;
                areaCount++;
                connectList(i, true);
            } else {
                if (check[i]) continue;
                area2++;
                check[i] = true;
                areaCount++;
                connectList(i, false);
            }
        }
        if (areaCount != 2 || area1 == 0 || area2 == 0) return false;

        return true;
    }

    private static void connectList(int num, boolean confirm) {
        for (int j : list[num]) {
            if (visit[j] == confirm && !check[j]) {
                check[j] = true;
                connectList(j, confirm);
            }
        }
    }

    private static int getMinNumber() {
        int area1 = 0, area2 = 0;
        for (int i = 1; i <= n; i++) {
            if (visit[i]) {
                area1 += people[i];
            } else {
                area2 += people[i];
            }
        }
        return Math.abs(area1 - area2);
    }
}

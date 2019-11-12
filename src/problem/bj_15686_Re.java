package problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bj_15686_Re {
    private static int n, m, ans = Integer.MAX_VALUE;
    private static int map[][];
    private static List<Dot> house = new ArrayList<>();
    private static List<Dot> chicken = new ArrayList<>();
    private static int len_house, len_chicken;
    private static boolean visit[];

    public static void main(String[] args) {
        init();
        selectChicken(0, 0);
        System.out.println(ans);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) {
                    house.add(new Dot(i, j));
                } else if (map[i][j] == 2) {
                    chicken.add(new Dot(i, j));
                }
            }
        }
        len_house = house.size();
        len_chicken = chicken.size();
        visit = new boolean[len_chicken];
    }

    private static void selectChicken(int start, int depth) {
        if (depth == m) {
            ans = Math.min(ans, getDistance());
            return;
        }

        for (int i = start; i < len_chicken; i++) {
            if (!visit[i]) {
                visit[i] = true;
                selectChicken(i + 1, depth + 1);
                visit[i] = false;
            }
        }
    }

    private static int getDistance() {
        int distance = 0;

        for (int i = 0; i < len_house; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < len_chicken; j++) {
                if (visit[j]) {
                    min = Math.min(min, Calc(house.get(i), chicken.get(j)));
                }
            }
            distance += min;
        }
        return distance;
    }

    private static int Calc(Dot house, Dot chicken) {
        return Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y);
    }

    private static class Dot {
        int x, y;

        Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

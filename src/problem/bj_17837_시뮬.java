package problem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_17837_시뮬 {
    private static int n, k;
    private static int[][] map;
    private static int[] horse = new int[11];
    private static int min = 1001;
    private static Queue<Horse> q = new LinkedList<>();

    public static void main(String[] args) {
        init();
        solution();
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for (int i = 1; i <= k; i++) {
            Queue<Integer> dq = new LinkedList<>();
            dq.add(i);
            q.add(new Horse(sc.nextInt(), sc.nextInt(), sc.nextInt(), dq));
        }
    }

    private static void solution() {
        while (!q.isEmpty()) {
            Horse now = q.poll();
            now = changeHorse(now.x, now.y, now.dir, now.dq);
            if (isRange(now.x, now.y)) {
                moveHorse(now.x, now.y, now.dir);
            } else {
                int dir = changeDir(now.dir);
                now = changeHorse(now.x, now.y, dir, now.dq);
                if (isRange(now.x, now.y)) {
                    moveHorse(now.x, now.y, now.dir);
                } else return;
            }
        }
    }

    private static Horse changeHorse(int x, int y, int dir, Queue<Integer> dq) {
        //우 좌 상 하
        switch (dir) {
            case 1:
                y++;
                break;
            case 2:
                y--;
                break;
            case 3:
                x--;
                break;
            case 4:
                x++;
                break;
        }

        return new Horse(x, y, dir, dq);
    }

    private static boolean isRange(int x, int y) {
        if (x <= 0 || y <= 0 || x > n || y > n || map[x][y] == 2) {
            return false;
        }
        return true;
    }

    private static void moveHorse(int x, int y, int dir) {
        int color = map[x][y];

        switch (color) {
            case 0:
                for (Horse i : q) {
                    if (i.x == x && i.y == y) {

                    }
                }
                break;
            case 1:
                reversePosition();
                if (isExist(x, y)) {

                } else {

                }
                break;
        }
    }

    private static boolean isExist(int x, int y) {
        for (Horse i : q) {
            if (i.x == x && i.y == y) {
                return true;
            }
        }
        return false;
    }

    private static void reversePosition() {

    }

    private static int changeDir(int dir) {
        switch (dir) {
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 4;
            case 4:
                return 3;
        }
        return 0;
    }

    private static class Horse {
        Queue<Integer> dq;
        int x, y, dir;

        Horse(int x, int y, int dir, Queue<Integer> dq) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.dq = dq;
        }
    }
}

package baekJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_14499_시뮬 {
    private static int n, m;
    private static int start_x, start_y;
    private static Queue<Integer> command = new LinkedList<>();
    private static int[][] map;
    private static int[] dice = new int[6]; //아래 위 동 서 남 북

    public static void main(String[] args) {
        init();
        solution();
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        start_x = sc.nextInt();
        start_y = sc.nextInt();
        int command_cnt = sc.nextInt();

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        while (command_cnt != 0) {
            command.add(sc.nextInt());
            command_cnt--;
        }
    }

    private static void solution() {
        while (!command.isEmpty()) {
            int dir = command.poll();
            if (isRange(dir)) {
                moveDice(dir);
                if (isZero(map[start_x][start_y])) {
                    copyDiceToMap();
                } else {
                    copyMapToDice();
                }
                System.out.println(dice[1]);
            }
        }
    }

    private static boolean isRange(int dir) {
        switch (dir) {
            case 1:
                if (start_y + 1 == m)
                    return false;
                start_y++;
                break;
            case 2:
                if (start_y - 1 == -1)
                    return false;
                start_y--;
                break;
            case 3:
                if (start_x - 1 == -1)
                    return false;
                start_x--;
                break;
            case 4:
                if (start_x + 1 == n)
                    return false;
                start_x++;
                break;
        }
        return true;
    }

    private static void moveDice(int dir) {
        int temp1, temp2;
        switch (dir) {
            case 1:
                temp1 = dice[2];
                temp2 = dice[3];
                dice[2] = dice[1];
                dice[3] = dice[0];
                dice[0] = temp1;
                dice[1] = temp2;
                break;
            case 2:
                temp1 = dice[2];
                temp2 = dice[3];
                dice[2] = dice[0];
                dice[3] = dice[1];
                dice[0] = temp2;
                dice[1] = temp1;
                break;
            case 3:
                temp1 = dice[4];
                temp2 = dice[5];
                dice[4] = dice[1];
                dice[5] = dice[0];
                dice[0] = temp1;
                dice[1] = temp2;
                break;
            case 4:
                temp1 = dice[4];
                temp2 = dice[5];
                dice[4] = dice[0];
                dice[5] = dice[1];
                dice[0] = temp2;
                dice[1] = temp1;
                break;
        }
    }

    private static boolean isZero(int num) {
        if (num == 0) return true;

        return false;
    }

    private static void copyDiceToMap() {
        map[start_x][start_y] = dice[0];
    }

    private static void copyMapToDice() {
        dice[0] = map[start_x][start_y];
        map[start_x][start_y] = 0;
    }
}

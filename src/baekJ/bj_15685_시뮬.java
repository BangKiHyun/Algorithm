package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_15685_시뮬 {
    private static final int size = 101;
    private static final int RIGHT = 0;
    private static final int UP = 1;
    private static final int LEFT = 2;
    private static final int DOWN = 3;
    private static int[][] map = new int[size][size];
    private static Queue<Dragon> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int curveCnt = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < curveCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int start_x = Integer.parseInt(st.nextToken());
            int start_y = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            q.add(new Dragon(start_x, start_y, direction));
            moveDragon(age);
        }
        int ans = getSquare();
        System.out.println(ans);

    }

    private static void moveDragon(int age) {
        ArrayList<Integer> direction = new ArrayList<>();
        Dragon dragon = q.poll();
        direction.add(dragon.direction);
        map[dragon.x][dragon.y] = 1;

        while (age > 0) {
            int size = direction.size();
            for (int i = size - 1; i >= 0; i--) {
                if (direction.get(i) < 3) {
                    direction.add(direction.get(i) + 1);
                } else {
                    direction.add(0);
                }
            }
            age--;
        }

        int x = dragon.x;
        int y = dragon.y;
        for (int dir : direction) {
            switch (dir) {
                case UP:
                    y--;
                    break;
                case DOWN:
                    y++;
                    break;
                case RIGHT:
                    x++;
                    break;
                case LEFT:
                    x--;
                    break;
            }
            map[x][y] = 1;
        }
    }

    private static int getSquare() {
        int cnt = 0;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (isSquare(i, j)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static boolean isSquare(int x, int y) {
        return map[x][y] == 1 && map[x + 1][y] == 1 && map[x][y + 1] == 1 && map[x + 1][y + 1] == 1;
    }

    private static class Dragon {
        private int x;
        private int y;
        private int direction;

        public Dragon(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}

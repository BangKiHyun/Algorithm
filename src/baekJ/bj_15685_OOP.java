package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj_15685_OOP {
    private static boolean[][] map = new boolean[101][101];
    private static final int UP = 1;
    private static final int RIGHT = 0;
    private static final int LEFT = 2;
    private static final int DOWN = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int curve_cnt = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < curve_cnt; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            Direction direction = new Direction(dir);
            while (age > 0) {
                direction.change();
                age--;
            }
            Dragon dragon = new Dragon(x, y, direction);
            dragon.move();
        }

        System.out.println(getSquare());
    }

    private static class Direction {
        private ArrayList<Integer> list = new ArrayList<>();

        public Direction(int direction) {
            this.list.add(direction);
        }

        public void change() {
            int size = list.size();
            for (int i = size - 1; i >= 0; i--) {
                this.list.add((list.get(i) + 1) % 4);
            }
        }
    }

    private static class Dragon {
        private int x;
        private int y;
        Direction direction;

        public Dragon(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public void move() {
            for (int dir : direction.list) {
                changeXY(dir);
                checkMap(x, y);
            }
        }

        private void changeXY(int dir) {
            //1, 3, 0, 2
            switch (dir) {
                case UP:
                    this.y--;
                    break;
                case DOWN:
                    this.y++;
                    break;
                case RIGHT:
                    this.x++;
                    break;
                case LEFT:
                    this.x--;
                    break;
            }
        }
    }

    private static void checkMap(int x, int y) {
        map[x][y] = true;
    }

    private static int getSquare() {
        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (isSquare(i, j)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static boolean isSquare(int x, int y) {
        return map[x][y] && map[x + 1][y] && map[x][y + 1] && map[x + 1][y + 1];
    }
}

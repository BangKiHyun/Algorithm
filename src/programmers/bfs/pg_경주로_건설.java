package programmers.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class pg_경주로_건설 {

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static final int CORNER = 500;
    private static final int STRAIGHT = 100;

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0}};
        System.out.println(solution(board));
    }

    public static int solution(int[][] board) {
        Queue<Car> q = new LinkedList<>();
        q.add(new Car(new Pos(0, 0), 1, 0));
        q.add(new Car(new Pos(0, 0), 3, 0));

        int length = board.length;
        int minCost = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            final Car curCar = q.poll();

            printBoard(board);

            if (curCar.isFinish(length)) {
                minCost = Math.min(minCost, curCar.cost);
            }

            for (int i = 0; i < 4; i++) {
                int nx = DX[i] + curCar.pos.x;
                int ny = DY[i] + curCar.pos.y;

                if (isRange(nx, ny, length) && isNotBlocking(nx, ny, board)) {
                    int newCost = curCar.cost + STRAIGHT;
                    if (i != curCar.dir) {
                        newCost += CORNER;
                    }

                    if (board[nx][ny] == 0 || newCost <= board[nx][ny]) {
                        board[nx][ny] = newCost;
                        q.add(new Car(new Pos(nx, ny), i, newCost));
                    }
                }
            }
        }
        return minCost;
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static boolean isRange(int x, int y, int length) {
        return x >= 0 && y >= 0 && x < length && y < length;
    }

    public static boolean isNotBlocking(int x, int y, int[][] board) {
        return board[x][y] != 1;
    }

    private static class Car {
        private Pos pos;
        private int dir;

        private int cost;

        public Car(Pos pos, int dir, int cost) {
            this.pos = pos;
            this.dir = dir;
            this.cost = cost;
        }

        public boolean isFinish(int length) {
            return pos.x == length - 1 && pos.y == length - 1;
        }
    }

    private static class Pos {
        private int x;
        private int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_17837_시뮬_OOP연습 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Board[][] boards;
    private static Queue<Horse> q = new LinkedList<>();
    private static final int N = 1;
    private static final int E = 4;
    private static final int S = 2;
    private static final int W = 3;

    public static void main(String[] args) throws IOException {
        //첫째 줄에 체스판의 크기 N, 말의 개수 K가 주어진다.
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        //둘째 줄부터 N개의 줄에 체스판의 정보가 주어진다. 체스판의 정보는 정수로 이루어져 있고, 각 정수는 칸의 색을 의미한다. 0은 흰색, 1은 빨간색, 2는 파란색이다.
        StringTokenizer st;
        boards = new Board[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int status = Integer.parseInt(st.nextToken());
                boards[i][j] = new Board(status, null);
            }
        }

        //다음 K개의 줄에 말의 정보가 1번 말부터 순서대로 주어진다. 말의 정보는 세 개의 정수로 이루어져 있고, 순서대로 행, 열의 번호, 이동 방향이다. 행과 열의 번호는 1부터 시작하고, 이동 방향은 4보다 작거나 같은 자연수이고 1부터 순서대로 →, ←, ↑, ↓의 의미를 갖는다.
        //같은 칸에 말이 두 개 이상 있는 경우는 입력으로 주어지지 않는다.
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());
            Horse horse = new Horse(x, y, direction);
            q.add(horse);
            boards[x][y] = new Board(boards[x][y].status, horse);
        }

        while (true) {
            Horse horse = q.poll();
            Map nextStep = horse.nextStep();

            if (nextStep.canMove(n) || boards[nextStep.x][nextStep.y].status != 2) {
                horse.move(nextStep.x, nextStep.y);
            } else {
                horse.changeDirection();
                nextStep = horse.nextStep();
                if (nextStep.canMove(n) || boards[nextStep.x][nextStep.y].status != 2) {
                    horse.move(nextStep.x, nextStep.y);
                }
            }

            boards[nextStep.x][nextStep.y] = new Board(boards[nextStep.x][nextStep.y].status, horse);
            q.add(horse);
        }
    }


    private static class Board {
        private int status;
        private Horse horse;

        public Board(int status, Horse horse) {
            this.status = status;
            this.horse = horse;
        }

        public Horse getHorse() {
            return horse;
        }

        public boolean isExist() {
            return horse != null;
        }
    }

    private static class Map {
        private int x;
        private int y;

        public Map(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean canMove(int range) {
            return (x >= 0 && y >= 0 && x < range && y < range);
        }
    }

    private static class Horse {
        private Horse horse;
        private int x;
        private int y;
        private int direction;
        private final int[] dx = {-1, 0, 1, 0};
        private final int[] dy = {0, 1, 0, -1};
        private Queue<Horse> list = new LinkedList<>();

        public Horse(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public Map nextStep() {
            int nx = this.x + dx[this.direction];
            int ny = this.y + dy[this.direction];

            return new Map(nx, ny);
        }

        public void move(int x, int y) {
            if (boards[x][y].status == 1) {
                reverseHorse();
            }
            if (boards[x][y].isExist()) {
                horse.up(this);
            }
            this.x = x;
            this.y = y;
            this.horse = horse;
        }

        public void reverseHorse() {

        }

        private void up(Horse horse) {
            this.horse = horse;
        }

        private void changeDirection() {
            if (this.direction == N || this.direction == W) {
                this.direction++;
            } else {
                this.direction--;
            }
        }
    }
}

package baekJ;

import java.io.*;
import java.util.*;

public class bj_3190_시뮬 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int N = 0;
    private static final int E = 1;
    private static final int S = 2;
    private static final int W = 3;

    private static int[][] map;

    public static void main(String[] args) throws IOException {
        /*첫째 줄에 보드의 크기 N이 주어진다. (2 ≤ N ≤ 100) 다음 줄에 사과의 개수 K가 주어진다. (0 ≤ K ≤ 100)
        다음 K개의 줄에는 사과의 위치가 주어지는데, 첫 번째 정수는 행, 두 번째 정수는 열 위치를 의미한다. 사과의 위치는 모두 다르며, 맨 위 맨 좌측 (1행 1열) 에는 사과가 없다.*/
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        map = new int[n][n];

        StringTokenizer st;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            map[x][y] = 2;
        }

        //다음 줄에는 뱀의 방향 변환 횟수 L 이 주어진다. (1 ≤ L ≤ 100)
        int L = Integer.parseInt(br.readLine());

        //다음 L개의 줄에는 뱀의 방향 변환 정보가 주어지는데,  정수 X와 문자 C로 이루어져 있으며. 게임 시작 시간으로부터 X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전시킨다는 뜻이다. X는 10,000 이하의 양의 정수이며, 방향 전환 정보는 X가 증가하는 순으로 주어진다.
        Map<Integer, String> timeMap = new HashMap<>();

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            timeMap.put(time, direction);
        }

        int time = 0;
        Snake snake = new Snake(0, 0, E);
        while (true) {
            time++;

            Coordinate nextStep = snake.getNextStep();
            if (!nextStep.canMove(n) || snake.isHitSelf(nextStep)) {
                break;
            }

            snake.move(nextStep);

            if (timeMap.containsKey(time)) {
                String direction = timeMap.get(time);
                snake.changeDirection(direction);
            }
        }

        System.out.println(time);
        br.close();
    }

    private static class Coordinate {
        private int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean canMove(int range) {
            return (x >= 0 && y >= 0 && x < range && y < range);
        }
    }

    private static class Snake {
        private int x, y, direction;
        private static final int[] dx = {-1, 0, 1, 0};
        private static final int[] dy = {0, 1, 0, -1};
        Queue<Coordinate> tail = new LinkedList<>();

        public Snake(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.tail.add(new Coordinate(0, 0));
            this.direction = direction;
        }

        public Coordinate getNextStep() {
            int nx = this.x + dx[this.direction];
            int ny = this.y + dy[this.direction];
            return new Coordinate(nx, ny);
        }

        public boolean isHitSelf(Coordinate nextStep) {
            int x = nextStep.x;
            int y = nextStep.y;
            return map[x][y] == 1;
        }

        public void move(Coordinate nextStep) {
            this.x = nextStep.x;
            this.y = nextStep.y;
            this.tail.add(nextStep);

            if (!eatApple()) {
                shrink();
            }

            map[x][y] = 1;
        }

        public boolean eatApple() {
            return map[x][y] == 2;
        }

        public void shrink() {
            Coordinate coordinate = tail.poll();
            map[coordinate.x][coordinate.y] = 0;
        }

        public void changeDirection(String direction) {
            if (direction.equals("L")) {
                this.direction = (this.direction + 3) % 4;
            } else {
                this.direction = (this.direction + 1) % 4;
            }
        }
    }
}

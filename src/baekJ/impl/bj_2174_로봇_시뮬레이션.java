package baekJ.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_2174_로봇_시뮬레이션 {
    private static int n, m;
    private static Map<Integer, Robot> robotMap = new HashMap<>();
    private static int[][] board;
    private static int crashesRobotIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        board = new int[n + 1][m + 1];

        st = new StringTokenizer(br.readLine());
        int robotCount = Integer.parseInt(st.nextToken());
        int commandCount = Integer.parseInt(st.nextToken());

        for (int idx = 1; idx <= robotCount; idx++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            String strDir = st.nextToken();
            robotMap.put(idx, new Robot(x, y, dirConvertToInt(strDir)));
            board[x][y] = idx;
        }

        for (int count = 0; count < commandCount; count++) {
            st = new StringTokenizer(br.readLine());
            int robotIdx = Integer.parseInt(st.nextToken());
            String command = st.nextToken();
            int moveCount = Integer.parseInt(st.nextToken());
            final Robot robot = robotMap.get(robotIdx);
            if (command.equals("F")) {
                if (robot.crashesOtherRobot(moveCount, robotIdx)) {
                    return;
                }
                robot.move(moveCount);
                board[robot.x][robot.y] = robotIdx;
            } else robot.changeDirection(command, moveCount % 4);
        }
        System.out.println("OK");
    }

    private static int dirConvertToInt(String strDir) {
        switch (strDir) {
            case "N":
                return 0;
            case "E":
                return 1;
            case "S":
                return 2;
            default:
                return 3;
        }
    }

    private static class Robot {
        private int x;
        private int y;
        private int dir;

        public Robot(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public void changeDirection(String command, int count) {
            while (count != 0) {
                if (command.equals("L")) {
                    this.dir--;
                    if (this.dir < 0) dir = 3;
                } else {
                    this.dir = (this.dir + 1) % 4;
                }
                count--;
            }
        }

        public boolean crashesOtherRobot(int moveCount, int robotIdx) {
            if (dir == 0) {
                int x = this.x;
                while (moveCount != 0) {
                    x--;
                    if (isCrashesTheWall(x, this.y)) {
                        System.out.println("Robot " + robotIdx + " crashes into the wall");
                        return true;
                    }
                    if (board[x][this.y] != 0) {
                        crashesRobotIdx = board[x][this.y];
                        System.out.println("Robot " + robotIdx + " crashes into robot " + crashesRobotIdx);
                        return true;
                    }
                    moveCount--;
                }
            } else if (dir == 1) {
                int y = this.y;
                while (moveCount != 0) {
                    y++;
                    if (isCrashesTheWall(this.x, y)) {
                        System.out.println("Robot " + robotIdx + " crashes into the wall");
                        return true;
                    }
                    if (board[this.x][y] != 0) {
                        crashesRobotIdx = board[this.x][y];
                        System.out.println("Robot " + robotIdx + " crashes into robot " + crashesRobotIdx);
                        return true;
                    }
                    moveCount--;
                }
            } else if (dir == 2) {
                int x = this.x;
                while (moveCount != 0) {
                    x++;
                    if (isCrashesTheWall(x, this.y)) {
                        System.out.println("Robot " + robotIdx + " crashes into the wall");
                        return true;
                    }
                    if (board[x][this.y] != 0) {
                        crashesRobotIdx = board[x][this.y];
                        System.out.println("Robot " + robotIdx + " crashes into robot " + crashesRobotIdx);
                        return true;
                    }
                    moveCount--;
                }
            } else {
                int y = this.y;
                while (moveCount != 0) {
                    y--;
                    if (isCrashesTheWall(this.x, y)) {
                        System.out.println("Robot " + robotIdx + " crashes into the wall");
                        return true;
                    }
                    if (board[this.x][y] != 0) {
                        crashesRobotIdx = board[this.x][y];
                        System.out.println("Robot " + robotIdx + " crashes into robot " + crashesRobotIdx);
                        return true;
                    }
                    moveCount--;
                }
            }
            return false;
        }

        private boolean isCrashesTheWall(int x, int y) {
            return x <= 0 || y <= 0 || x > n || y > m;
        }

        public void move(int moveCount) {
            board[this.x][this.y] = 0;
            if (dir == 0) {
                this.x -= moveCount;
            } else if (dir == 1) {
                this.y += moveCount;
            } else if (dir == 2) {
                this.x += moveCount;
            } else {
                this.y -= moveCount;
            }
        }
    }
}

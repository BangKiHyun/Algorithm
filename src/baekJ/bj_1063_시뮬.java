package baekJ;
//8*8크기의 체스판에 왕이 하나 있다. 킹의 현재 위치가 주어진다. 체스판에서 말의 위치는 다음과 같이 주어진다. 알파벳 하나와 숫자 하나로 이루어져 있는데, 알파벳은 열을 상징하고, 숫자는 행을 상징한다. 열은 가장 왼쪽 열이 A이고, 가장 오른쪽 열이 H까지 이고, 행은 가장 아래가 1이고 가장 위가 8이다. 예를 들어, 왼쪽 아래 코너는 A1이고, 그 오른쪽 칸은 B1이다.

//킹은 다음과 같이 움직일 수 있다.
//
//R : 한 칸 오른쪽으로
//L : 한 칸 왼쪽으로
//B : 한 칸 아래로
//T : 한 칸 위로
//RT : 오른쪽 위 대각선으로
//LT : 왼쪽 위 대각선으로
//RB : 오른쪽 아래 대각선으로
//LB : 왼쪽 아래 대각선으로

//체스판에는 돌이 하나 있는데, 돌과 같은 곳으로 이동할 때는, 돌을 킹이 움직인 방향과 같은 방향으로 한 칸 이동시킨다.

//입력으로 킹이 어떻게 움직여야 하는지 주어진다. 입력으로 주어진 대로 움직여서 킹이나 돌이 체스판 밖으로 나갈 경우에는 그 이동은 건너 뛰고 다음 이동을 한다.
//
//킹과 돌의 마지막 위치를 구하는 프로그램을 작성하시오.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class bj_1063_시뮬 {

    private static final int MAX = 8;
    private static final int STONE = 2;
    private static int[][] board = new int[MAX][MAX];
    private static Queue<Node> q = new LinkedList<>();
    private static Queue<String> command = new LinkedList();
    private static int stonePos_x, stonePos_y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //첫째 줄에 킹의 위치, 돌의 위치, 움직이는 횟수 N이 주어진다.
        String[] line = br.readLine().split(" ");

        int[] RC = findIntegerPosition(line[0]);
        q.add(new Node(RC[0], RC[1]));

        RC = findIntegerPosition(line[1]);
        board[RC[0]][RC[1]] = STONE;
        stonePos_x = RC[0];
        stonePos_y = RC[1];

        int n = Integer.parseInt(line[2]);
        while (n > 0) {
            command.add(br.readLine());
            n--;
        }

        moveKing();

        Node node = q.poll();
        String king = findStrPosition(node.x, node.y);
        String stone = findStrPosition(stonePos_x, stonePos_y);

        System.out.println(king + "\n" + stone);
    }

    private static void moveKing() {
        while (!command.isEmpty()) {
            String com = command.poll();
            Node now = q.peek();

            int[] move_xy = findXY(com);
            int nx = now.x + move_xy[0];
            int ny = now.y + move_xy[1];

            if (isRange(nx, ny)) {
                if (ExistStone(nx, ny)) {
                    int stone_x = nx + move_xy[0];
                    int stone_y = ny + move_xy[1];
                    if (isRange(stone_x, stone_y)) {
                        board[nx][ny] = 0;
                        board[stone_x][stone_y] = STONE;
                        stonePos_x = stone_x;
                        stonePos_y = stone_y;
                    } else continue;
                }
                q.poll();
                q.add(new Node(nx, ny));
            }
        }
    }

    private static int[] findIntegerPosition(String position) {
        int[] ret = new int[2];
        ret[0] = MAX - Integer.parseInt(position.substring(1, 2));
        ret[1] = position.charAt(0) - 65;

        return ret;
    }

    private static String findStrPosition(int x, int y) {
        char ret_y = (char) (65 + y);
        return ret_y + String.valueOf(MAX - x);
    }

    private static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int[] findXY(String command) {
        int[] nextXY = new int[2];
        switch (command.substring(0, 1)) {
            case "R":
                nextXY[1]++;
                break;
            case "L":
                nextXY[1]--;
                break;
            case "B":
                nextXY[0]++;
                break;
            case "T":
                nextXY[0]--;
                break;
        }
        if (command.length() == 2) {
            switch (command.substring(1, 2)) {
                case "T":
                    nextXY[0]--;
                    break;
                case "B":
                    nextXY[0]++;
            }
        }
        return nextXY;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < MAX && y < MAX;
    }

    private static boolean ExistStone(int x, int y) {
        return board[x][y] == STONE;
    }
}
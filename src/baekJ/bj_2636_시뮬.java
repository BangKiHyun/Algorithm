package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.*;

public class bj_2636_시뮬 {
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static final int CHEESE = 1;

    private static int n, m;
    private static int[][] map;
    private static Queue<Node> nodes = new LinkedList<>();
    private static int curCheeseCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = parseInt(st.nextToken());
                if (map[i][j] == CHEESE) {
                    nodes.offer(new Node(i, j));
                }
            }
        }

        int time = 0;
        if (nodes.size() == 0) {
            System.out.printf("%d\n%d", time, curCheeseCnt);
            return;
        }

        Queue<Node> cheesePositions = new LinkedList<>();
        Queue<Node> airPositions = new LinkedList<>();
        while (!nodes.isEmpty()) {
            time++;
            while (!nodes.isEmpty()) {
                Node curPos = nodes.poll();
                int x = curPos.x;
                int y = curPos.y;
                if (isMelt(x, y)) airPositions.offer(new Node(x, y));
                else cheesePositions.offer(curPos);
            }
            curCheeseCnt -= airPositions.size();
            checkAirPosition(airPositions);
            nodes.addAll(cheesePositions);
            cheesePositions.clear();
        }

        System.out.printf("%d\n%d", time, curCheeseCnt);
    }

    private static boolean isMelt(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = DX[i] + x;
            int ny = DY[i] + y;
            if (isRange(nx, ny) && map[nx][ny] == 0) {
                return true;
            }
        }
        return false;
    }

    private static void checkAirPosition(Queue<Node> airPositions) {
        while (!airPositions.isEmpty()) {
            final Node airPosition = airPositions.poll();
            map[airPosition.x][airPosition.y] = 0;
        }
    }

    private static boolean isRange(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < n && ny < m;
    }

    private static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

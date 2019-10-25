package baekJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_6593_BFS {
    private static int dx[];
    private static int dy[];
    private static int floor, r, c;
    private static int ans = -1;
    private static final String EXIT = "E";
    private static String[][] building;
    private static boolean visit[][];
    private static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) {
        init();
        findExit();
        result();
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        floor = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();
        dx = new int[]{-1, 1, 0, 0, r, -r};
        dy = new int[]{0, 0, -1, 1, 0, 0};
        r *= floor;
        building = new String[r][c];
        visit = new boolean[r][c];
        boolean check = false;

        for (int i = 0; i < r; i++) {
            String strLine = sc.next();
            building[i] = strLine.split("");
            if (!check) {
                for (int k = 0; k < c; k++) {
                    if (isStart(building[i][k])) {
                        q.add(new Node(i, k, 0));
                        visit[i][k] = true;
                        check = true;
                    }
                }
            }
        }
    }
    private static boolean isStart(String tmpStr) {
        if (tmpStr.equals("S")) {
            return true;
        }
        return false;
    }

    private static void findExit() {
        while (!q.isEmpty()) {
            Node now = q.poll();
            if (isExit(building[now.x][now.y])) {
                ans = now.time;
                return;
            }
            for (int i = 0; i < 6; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < r && ny < c && !visit[nx][ny] && !building[nx][ny].equals("#")) {
                    q.add(new Node(nx, ny, now.time + 1));
                    visit[nx][ny] = true;
                }
            }
        }
    }

    private static boolean isExit(String position) {
        if (position.equals(EXIT)) {
            return true;
        }
        return false;
    }

    private static void result() {
        if (ans == -1) {
            System.out.println("Trapped!");
        } else {
            System.out.println("Escaped in " + ans + " minute(s).");
        }
    }

    private static class Node {
        int x, y, time;

        Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}

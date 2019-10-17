package baekJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_3055_BFS {
    private static int r, c, end_x, end_y;
    private static String[][] map;
    private static int dx[] = {-1, 1, 0, 0};
    private static int dy[] = {0, 0, -1, 1};
    private static Queue<Node> q_path = new LinkedList<>();
    private static Queue<Node> q_water = new LinkedList<>();
    private static boolean visit[][];
    private static int cnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        map = new String[r][c];
        visit = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            String line = sc.next();
            map[i] = line.split("");
            for (int j = 0; j < c; j++) {
                if (map[i][j].equals("S")){
                    q_path.add(new Node(i, j));
                    visit[i][j] = true;
                }
                if (map[i][j].equals("D")) {
                    end_x = i;
                    end_y = j;
                }
                if (map[i][j].equals("*")) q_water.add(new Node(i, j));
            }
        }
        int ans = solution();
        System.out.println(ans == -1 ? "KAKTUS" : ans);
    }

    private static int solution() {

        while (true) {
            spreadWater(q_water.size());
            int check = goBiber(q_path.size());
            if (check == 0) {
                return -1;
            }else if(check == -1){
                return cnt;
            }
        }
    }

    private static int goBiber(int size) {
        int flag = 0;

        while (size != 0) {
            Node now = q_path.poll();
            if (now.x == end_x && now.y == end_y) return -1;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < r && ny < c && !visit[nx][ny] && !map[nx][ny].equals("*") && !map[nx][ny].equals("X")) {
                    flag = -2;
                    visit[nx][ny] = true;
                    q_path.add(new Node(nx, ny));
                }
            }
            size--;
        }
        cnt++;
        return flag;
    }

    private static void spreadWater(int size) {
        while (size != 0) {
            Node now = q_water.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < r && ny < c && map[nx][ny].equals(".")) {
                    map[nx][ny] = "*";
                    visit[nx][ny] = true;
                    q_water.add(new Node(nx, ny));
                }
            }
            size--;
        }
    }

    private static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
package problem;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class bj_17142_me {
    static int n, virus;
    static int map[][];
    static int copy[][];
    static boolean visit[][];
    static int min = Integer.MAX_VALUE;
    static List<Dot> virusList = new LinkedList<>();
    static Queue<Dot> copyList = new LinkedList<>();
    static int[] X = {-1, 1, 0, 0};
    static int[] Y = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        virus = sc.nextInt();
        map = new int[n][n];
        copy = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 2) {
                    virusList.add(new Dot(i, j, 0));
                }
            }
        }
        solution(0, 0);
        System.out.println(min);
    }

    static void solution(int start, int depth) {
        if (depth == virus) {
            visit = new boolean[n][n];
            copyMap();
            min = Math.min(min, spreadVirus());
            return;
        }

        for (int i = start; i < virusList.size(); i++) {
            copyList.add(virusList.get(i));
            solution(i + 1, depth + 1);
            copyList.poll();
        }
    }

    static void copyMap() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = map[i][j];
                if (map[i][j] != 0) visit[i][j] = true;
            }
        }
    }

    static int spreadVirus() {
        int day = Integer.MIN_VALUE;
        while (!copyList.isEmpty()) {
            Dot t = copyList.poll();
            for (int i = 0; i < 4; i++) {
                int nx = t.x + X[i];
                int ny = t.y + Y[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < n && map[nx][ny] == 0 && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    copyList.add(new Dot(nx, ny, t.cnt + 1));
                }
            }
            day = Math.max(day, t.cnt);
        }
        return day;
    }

    static class Dot {
        int x, y, cnt;

        public Dot(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}

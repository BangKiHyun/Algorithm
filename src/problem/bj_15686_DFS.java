package problem;

import java.util.*;

public class bj_15686_DFS {
    private static int n, m;
    private static int min = Integer.MAX_VALUE;
    private static int map[][];
    private static int dx[] = {-1, 1, 0, 0};
    private static int dy[] = {0, 0, -1, 1};
    private static boolean visit[][];
    private static boolean check[][];
    private static Queue<House> q = new LinkedList<>();
    private static List<House> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][n];
        visit = new boolean[n][n];
        check = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) {
                    list.add(new House(i, j));
                }
            }
        }

        dfs(0, 0);
        System.out.println(min);
    }

    private static void dfs(int start, int depth) {
        if (depth == m) {
            min = Math.min(min, getCityDistance());
            return;
        }

        for (int i = start; i < n * n; i++) {
            int x = i / n;
            int y = i % n;

            if (map[x][y] == 2 && !check[x][y]) {
                check[x][y] = true;
                dfs(i + 1, depth + 1);
                check[x][y] = false;
            }
        }
    }

    private static int getCityDistance() {
        int distance = 0;

        for (int i = 0; i < list.size(); i++) {
            int x = list.get(i).x;
            int y = list.get(i).y;
            visit[x][y] = true;
            q.add(new House(x, y, 0));
            distance += getChickenDistance();
            visit = new boolean[n][n];
            q.clear();
        }

        return distance;
    }

    private static int getChickenDistance() {
        while (!q.isEmpty()) {
            House house = q.poll();
            if (check[house.x][house.y]) {
                return house.distance;
            }

            for (int i = 0; i < 4; i++) {
                int nx = house.x + dx[i];
                int ny = house.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    q.add(new House(nx, ny, house.distance + 1));
                }
            }
        }
        return 0;
    }

    private static class House {
        int x, y, distance;

        House(int x, int y) {
            this.x = x;
            this.y = y;
        }

        House(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}

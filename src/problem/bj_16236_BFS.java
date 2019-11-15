package problem;

import java.util.*;

public class bj_16236_BFS {
    private static int n;
    private static int map[][];
    private static int dx[] = {-1, 0, 1, 0};
    private static int dy[] = {0, -1, 0, 1};
    private static Queue<Node> q = new LinkedList<>();
    private static Queue<Node> path = new LinkedList<>();
    private static boolean path_visit[][];
    private static List<Node> list = new ArrayList<>();
    private static int distance = Integer.MAX_VALUE;
    private static int feed_x;
    private static int ans = 0;

    public static void main(String[] args) {
        init();
        solution();
        System.out.println(ans);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];
        path_visit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 9) {
                    q.add(new Node(i, j));
                    map[i][j] = 0;
                }
            }
        }
    }

    private static void solution() {
        int cnt = 0;
        int shark_size = 2;

        while (!q.isEmpty()) {
            Node node = q.poll();
            path.add(new Node(node.x, node.y, 0));
            findShark(shark_size);
            if (list.size() == 0) return;
            ans += distance;
            int min_y = getY();
            findFeed(min_y);
            initPath();

            cnt++;
            if (cnt == shark_size) {
                shark_size++;
                cnt = 0;
            }
        }
    }

    private static int getY() {
        int min = n;
        for (Node node : list) {
            if (node.x == feed_x) {
                if (node.y < min) {
                    min = node.y;
                }
            }
        }
        return min;
    }

    private static void findFeed(int feed_y) {
        for (Node node : list) {
            if (node.x == feed_x && node.y == feed_y) {
                q.add(new Node(node.x, node.y));
                map[node.x][node.y] = 0;
                break;
            }
        }
    }

    private static void initPath() {
        distance = Integer.MAX_VALUE;
        path_visit = new boolean[n][n];
        list.clear();
    }

    private static void findShark(int shark_size) {
        feed_x = n;

        while (!path.isEmpty()) {
            Node now = path.poll();
            path_visit[now.x][now.y] = true;

            if (now.dis > distance) {
                continue;
            }

            if (map[now.x][now.y] < shark_size && map[now.x][now.y] != 0 && now.x <= feed_x) {
                distance = Math.min(distance, now.dis);
                list.add(new Node(now.x, now.y));
                feed_x = now.x;
            }

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;
                if (isValid(nx, ny, shark_size)) {
                    path.add(new Node(nx, ny, now.dis + 1));
                }
            }
        }
    }

    private static boolean isValid(int x, int y, int shark_size) {
        if (x >= 0 && y >= 0 && x < n && y < n && !path_visit[x][y] && map[x][y] <= shark_size) {
            return true;
        }
        return false;
    }

    private static class Node {
        int x, y, dis;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Node(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }
}

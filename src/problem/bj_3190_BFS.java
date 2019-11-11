package problem;

import java.util.*;

public class bj_3190_BFS {
    private static int n, k, ans;
    private static int map[][];
    private static boolean visit[][];
    private static Queue<Snake> q = new LinkedList<>();
    private static Queue<Snake> retail = new LinkedList<>();
    private static String last_dir;

    public static void main(String[] args) {
        init();
        visit[1][1] = true;
        retail.add(new Snake(1, 1));
        solution(1, 1, 1, q.peek().second, q.poll().direction, 0, 0);
        System.out.println(ans);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        map = new int[n + 1][n + 1];
        visit = new boolean[n + 1][n + 1];

        for (int i = 0; i < k; i++) {
            map[sc.nextInt()][sc.nextInt()] = 1;
        }

        int L = sc.nextInt();
        for (int i = 0; i < L; i++) {
            int second = sc.nextInt();
            String dir = sc.next();
            q.add(new Snake(second, dir));
            if (i == L - 1) {
                last_dir = dir;
            }
        }
    }

    private static void solution(int x, int y, int dir, int second, String next_dir, int now_time, int cnt) {
        if (second == now_time) {
            if (q.isEmpty()) {
                dir = findDir(dir, last_dir);
            } else {
                second = q.peek().second;
                dir = findDir(dir, next_dir);
                next_dir = q.poll().direction;
            }
        }

        int nx = x, ny = y;
        switch (dir) {
            case 1:
                ny = y + 1;
                break;
            case 3:
                ny = y - 1;
                break;
            case 2:
                nx = x + 1;
                break;
            case 4:
                nx = x - 1;
                break;
            default:
                break;
        }

        if (nx > n || ny > n || nx < 1 || ny < 1 || visit[nx][ny] == true) {
            ans = cnt + 1;
            return;
        } else {
            visit[nx][ny] = true;
            if (map[nx][ny] == 1) {
                map[nx][ny] = 0;
                retail.add(new Snake(nx, ny));
            } else {
                retail.add(new Snake(nx, ny));
                Snake re = retail.poll();
                visit[re.x][re.y] = false;
            }
        }
        solution(nx, ny, dir, second, next_dir, now_time + 1, cnt + 1);
        return;
    }

    private static int findDir(int now_dir, String next_dir) {
        switch (next_dir) {
            case "D":
                now_dir++;
                if (now_dir == 5) {
                    now_dir = 1;
                }
                break;
            case "L":
                now_dir--;
                if (now_dir == 0) {
                    now_dir = 4;
                }
                break;
            default:
                break;
        }
        return now_dir;
    }

    private static class Snake {
        int second;
        String direction;

        int x, y;

        Snake(int second, String direction) {
            this.second = second;
            this.direction = direction;
        }

        Snake(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

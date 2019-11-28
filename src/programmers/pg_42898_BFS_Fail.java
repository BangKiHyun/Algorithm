package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class pg_42898_BFS_Fail {
    private static int[][] map;
    private static int[] dx = {0, 1};
    private static int[] dy = {1, 0};
    private static Queue<Node> q = new LinkedList<>();
    private static int[] listCnt;

    public static void main(String[] args) {
        int m = 4;
        int n = 3;
        int[][] puddles = {{2, 2}};
        int ans = solution(m, n, puddles);
        System.out.println(ans);
    }

    private static int solution(int m, int n, int[][] puddles) {
        int answer = 0;

        map = new int[m][n];
        listCnt = new int[m * n];
        makeMap(m, n, puddles);
        q.add(new Node(0, 0, 0));
        int min = goBFS(m, n);

        for (int i = 0; i < listCnt.length; i++) {
            if (min == listCnt[i]) {
                answer++;
            }
        }

        return answer % 1000000007;
    }

    private static void makeMap(int r, int c, int[][] puddles) {
        map = new int[r][c];
        for (int[] i : puddles) {
            int x = i[0] - 1;
            int y = i[1] - 1;
            map[x][y] = -1;
        }
    }

    private static int goBFS(int r, int c) {
        int min = Integer.MAX_VALUE;
        int idx = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.x == r - 1 && now.y == c - 1) {
                listCnt[idx] = now.cnt;
                min = Math.min(min, now.cnt);
                idx++;
                continue;
            }

            for (int i = 0; i < 2; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isRange(r, c, nx, ny)) {
                    q.add(new Node(nx, ny, now.cnt + 1));
                }
            }
        }
        return min;
    }

    private static boolean isRange(int r, int c, int nx, int ny) {
        if (nx >= 0 && ny >= 0 && nx < r && ny < c && map[nx][ny] == 0) {
            return true;
        }
        return false;
    }

    private static class Node {
        int x, y, cnt;

        Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}

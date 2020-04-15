package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class bj_2206_BFS_Re {
    private static final int WALL = 1;

    private static int n, m;
    private static int[][] map, visit;

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        map = new int[n][m];
        visit = new int[n][m];

        for (int i = 0; i < n; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                visit[i][j] = 1000001;
            }
        }

        int shortestPath = getShortestPath();

        System.out.println(shortestPath);
    }

    private static int getShortestPath() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 1, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (isFinishLine(now.x, now.y)) return now.cnt;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isRange(nx, ny) && visit[nx][ny] > now.destroyCnt) {
                    if (map[nx][ny] != WALL) {
                        visit[nx][ny] = now.destroyCnt;
                        q.offer(new Node(nx, ny, now.cnt + 1, now.destroyCnt));
                    } else {
                        if (now.destroyCnt == 0) {
                            visit[nx][ny] = now.destroyCnt + 1;
                            q.offer(new Node(nx, ny, now.cnt + 1, now.destroyCnt + 1));
                        }
                    }
                }
            }
        }

        return -1;
    }

    private static boolean isFinishLine(int x, int y) {
        return x == n - 1 && y == m - 1;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    private static class Node {
        private int x;
        private int y;
        private int cnt;
        private int destroyCnt;

        public Node(int x, int y, int cnt, int destroyCnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.destroyCnt = destroyCnt;
        }
    }
}


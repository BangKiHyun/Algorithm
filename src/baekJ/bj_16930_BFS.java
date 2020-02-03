package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//진영이는 다이어트를 위해 N×M 크기의 체육관을 달리려고 한다. 체육관은 1×1 크기의 칸으로 나누어져 있고, 칸은 빈 칸 또는 벽이다. x행 y열에 있는 칸은 (x, y)로 나타낸다.
//
//매 초마다 진영이는 위, 아래, 오른쪽, 왼쪽 중에서 이동할 방향을 하나 고르고, 그 방향으로 최소 1개, 최대 K개의 빈 칸을 이동한다.
//
//시작점 (x1, y1)과 도착점 (x2, y2)가 주어졌을 때, 시작점에서 도착점으로 이동하는 최소 시간을 구해보자.
public class bj_16930_BFS {
    private static String[][] map;
    private static int[][] visit;
    private static int end_x, end_y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //첫째 줄에 체육관의 크기 N과 M, 1초에 이동할 수 있는 칸의 최대 개수 K가 주어진다.
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int k = Integer.parseInt(line[2]);

        map = new String[n][m];
        visit = new int[n][m];

        //둘째 줄부터 N개의 줄에는 체육관의 상태가 주어진다. 체육관의 각 칸은 빈 칸 또는 벽이고, 빈 칸은 '.', 벽은 '#'으로 주어진다.
        for (int i = 0; i < n; i++) {
            line = br.readLine().split("");
            map[i] = line;
        }

        //마지막 줄에는 네 정수 x1, y1, x2, y2가 주어진다. 두 칸은 서로 다른 칸이고, 항상 빈 칸이다.
        line = br.readLine().split(" ");
        int start_x = Integer.parseInt(line[0]) - 1;
        int start_y = Integer.parseInt(line[1]) - 1;
        end_x = Integer.parseInt(line[2]) - 1;
        end_y = Integer.parseInt(line[3]) - 1;

        Queue<Node> q = new LinkedList<>();

        q.add(new Node(start_x, start_y));
        visit[start_x][start_y] = 1;

        int ans = goBFS(q, n, m, k);
        System.out.println(ans);
    }

    private static int goBFS(Queue<Node> q, int max_x, int max_y, int k) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.x == end_x && now.y == end_y) {
                return visit[now.x][now.y] - 1;
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j <= k; j++) {
                    int nx = now.x + (dx[i] * j);
                    int ny = now.y + (dy[i] * j);

                    if (isWall(nx, ny, max_x, max_y)) break;
                    if (visit[nx][ny] == 0 && visit[nx][ny] < visit[now.x][now.y]) {
                        q.add(new Node(nx, ny));
                        visit[nx][ny] = visit[now.x][now.y] + 1;
                    }
                }
            }
        }

        return -1;
    }

    private static boolean isWall(int x, int y, int max_x, int max_y) {
        return x < 0 || y < 0 || x >= max_x || y >= max_y || map[x][y].equals("#");
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

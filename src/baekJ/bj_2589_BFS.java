package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//보물섬 지도를 발견한 후크 선장은 보물을 찾아나섰다.
//보물섬 지도는 아래 그림과 같이 직사각형 모양이며 여러 칸으로 나뉘어져 있다.
//각 칸은 육지(L)나 바다(W)로 표시되어 있다.
//이 지도에서 이동은 상하좌우로 이웃한 육지로만 가능하며, 한 칸 이동하는데 한 시간이 걸린다. 보물은 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 묻혀있다.
//육지를 나타내는 두 곳 사이를 최단 거리로 이동하려면 같은 곳을 두 번 이상 지나가거나, 멀리 돌아가서는 안 된다.

//보물 지도가 주어질 때, 보물이 묻혀 있는 두 곳 간의 최단 거리로 이동하는 시간을 구하는 프로그램을 작성하시오.
public class bj_2589_BFS {
    private static final int LAND = 1;
    private static final int WATER = 2;

    private static int n, m;
    private static int[][] map;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                if (input[j].equals("L")) map[i][j] = LAND;
                else map[i][j] = WATER;
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == LAND) {
                    ans = Math.max(ans, getDistance(i, j));
                }
            }
        }

        System.out.println(ans);
    }

    private static int getDistance(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visit = new boolean[n][m];
        q.offer(new Node(x, y, 0));
        visit[x][y] = true;
        int dist = 0;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            dist = cur.cnt;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isRange(nx, ny) && !visit[nx][ny] && map[nx][ny] == LAND) {
                    q.offer(new Node(nx, ny, cur.cnt + 1));
                    visit[nx][ny] = true;
                }
            }
        }

        return dist;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    private static class Node {
        private int x;
        private int y;
        private int cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}

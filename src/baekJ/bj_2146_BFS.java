package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//여러 섬으로 이루어진 나라가 있다. 이 나라의 대통령은 섬을 잇는 다리를 만들겠다는 공약으로 인기몰이를 해 당선될 수 있었다.
//하지만 막상 대통령에 취임하자, 다리를 놓는다는 것이 아깝다는 생각을 하게 되었다.
//그래서 그는, 생색내는 식으로 한 섬과 다른 섬을 잇는 다리 하나만을 만들기로 하였고, 그 또한 다리를 가장 짧게 하여 돈을 아끼려 하였다.
//
//이 나라는 N×N크기의 이차원 평면상에 존재한다. 이 나라는 여러 섬으로 이루어져 있으며, 섬이란 동서남북으로 육지가 붙어있는 덩어리를 말한다.
//지도가 주어질 때, 가장 짧은 다리 하나를 놓아 두 대륙을 연결하는 방법을 찾으시오.
public class bj_2146_BFS {
    private static final int INF = Integer.MAX_VALUE;

    private static int n;
    private static int[][] map;
    private static boolean[][] visit;

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    //첫 줄에는 지도의 크기 N(100이하의 자연수)가 주어진다.
    //그 다음 N줄에는 N개의 숫자가 빈칸을 사이에 두고 주어지며, 0은 바다, 1은 육지를 나타낸다. 항상 두 개 이상의 섬이 있는 데이터만 입력으로 주어진다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visit = new boolean[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        searchIsland();
        int bridge_len = getShortestBridge();
        System.out.println(bridge_len == INF ? -1 : bridge_len);
    }

    private static int getShortestBridge() {
        int min = INF;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0) {
                    min = Math.min(min, getBridgeLength(i, j, map[i][j]));
                }
            }
        }

        return min;
    }

    private static int getBridgeLength(int x, int y, int idx) {
        Queue<Node> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            Arrays.fill(visit[i], false);
        }

        q.offer(new Node(x, y, 0));
        visit[x][y] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isPossible(nx, ny) && map[nx][ny] != idx) {
                    if (map[nx][ny] != 0) {
                        return now.distance;
                    }
                    q.offer(new Node(nx, ny, now.distance + 1));
                    visit[nx][ny] = true;
                }
            }
        }

        return INF;
    }

    private static void searchIsland() {
        int Island_idx = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    Island_idx++;
                    numberingIsland(i, j, Island_idx);
                }
            }
        }
    }

    private static void numberingIsland(int x, int y, int Island_idx) {
        Queue<Node> q = new LinkedList<>();
        visit[x][y] = true;
        map[x][y] = Island_idx;
        q.offer(new Node(x, y));

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isPossible(nx, ny) && map[nx][ny] == 1) {
                    visit[nx][ny] = true;
                    map[nx][ny] = Island_idx;
                    q.offer(new Node(nx, ny));
                }
            }
        }
    }

    private static boolean isPossible(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n && !visit[x][y];
    }

    private static class Node {
        private int x;
        private int y;
        private int distance;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}

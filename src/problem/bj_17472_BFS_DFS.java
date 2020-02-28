package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_17472_BFS_DFS {
    private static final int INF = 999999999;

    private static int[][] map;
    private static boolean[][] visit;
    private static int[][] dist;
    private static boolean[] idx_dist;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int min = INF;
    private static ArrayList<Island> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int j = 0;
            while (st.hasMoreElements()) {
                map[i][j] = Integer.parseInt(st.nextToken());
                j++;
            }
        }

        NumberingIsland(n, m);
        initDistance(n, m);
        findMinimumDistance(1, 1, 0);

        System.out.println(min == INF ? -1 : min);
    }

    private static void NumberingIsland(int n, int m) {
        int num = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    goBFS(i, j, num++);
                }
            }
        }

        dist = new int[num][num];
        idx_dist = new boolean[num];
    }

    private static void goBFS(int x, int y, int num) {
        Queue<Island> q = new LinkedList<>();
        q.add(new Island(x, y));
        visit[x][y] = true;
        map[x][y] = num;

        while (!q.isEmpty()) {
            Island now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isValue(nx, ny)) {
                    q.add(new Island(nx, ny));
                    map[nx][ny] = num;
                    visit[nx][ny] = true;
                }
            }
        }
    }

    private static boolean isValue(int x, int y) {
        return x >= 0 && y >= 0 && x < map.length && y < map[0].length && !visit[x][y] && map[x][y] == 1;
    }

    private static void initDistance(int max_x, int max_y) {
        for (int i = 1; i < dist.length; i++) {
            Arrays.fill(dist[i], INF);
        }

        for (int i = 0; i < max_x; i++) {
            for (int j = 0; j < max_y; j++) {
                if (map[i][j] != 0) {
                    int from = map[i][j];
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        int distance = 0;

                        while (true) {
                            if (!isRange(nx, ny, max_x, max_y, from)) {
                                break;
                            } else if (map[nx][ny] == 0) {
                                distance++;
                                nx += dx[k];
                                ny += dy[k];
                            } else {
                                if (distance < 2 || map[nx][ny] == from) break;
                                int to = map[nx][ny];
                                dist[from][to] = dist[to][from] = Math.min(distance, dist[from][to]);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean isRange(int x, int y, int max_x, int max_y, int now) {
        return x >= 0 && y >= 0 && x < max_x && y < max_y;
    }

    private static void findMinimumDistance(int from, int depth, int distance) {
        if (depth == dist.length - 1) {
            min = Math.min(min, distance);
            return;
        }

        for (int to = 1; to < dist.length; to++) {
            if (!idx_dist[to]) {
                if (dist[from][to] == INF) continue;
                idx_dist[to] = true;
                findMinimumDistance(from, depth + 1, distance + dist[from][to]);
                idx_dist[to] = false;
            }
        }
    }

    private static class Island implements Comparable<Island> {
        private int x;
        private int y;
        private int dis;

        public Island(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Island(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }

        @Override
        public int compareTo(Island o) {
            return this.dis - o.dis;
        }
    }
}


//Queue 에 Island 객체를 Numbering 하여 저장한다
//Numbering 은 BFS를 통해서 진행, 이 때 idx[][] 배열에 numbering 병행
//Queue 에 저장되어 있는 Island 객체를 하나씩 빼서 상하좌우로 DFS 탐색을 하여
//섬과 섬의 거리를 구함(dist 배열에 저장), 이 때 같은 섬끼리 연결하면 안됨(idx 배열 사용), 거리는 2이상
//섬과 섬사이의 거리를 구한 후, 다익스트라를 이용하여 최소 비용 find
//만약 모든 섬들이 연결될 수 없으면 return -1
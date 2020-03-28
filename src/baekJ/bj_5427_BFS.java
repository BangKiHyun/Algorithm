package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//상근이는 빈 공간과 벽으로 이루어진 건물에 갇혀있다. 건물의 일부에는 불이 났고, 상근이는 출구를 향해 뛰고 있다.
//
//매 초마다, 불은 동서남북 방향으로 인접한 빈 공간으로 퍼져나간다. 벽에는 불이 붙지 않는다.
//상근이는 동서남북 인접한 칸으로 이동할 수 있으며, 1초가 걸린다.
//상근이는 벽을 통과할 수 없고, 불이 옮겨진 칸 또는 이제 불이 붙으려는 칸으로 이동할 수 없다.
//상근이가 있는 칸에 불이 옮겨옴과 동시에 다른 칸으로 이동할 수 있다.
//
//빌딩의 지도가 주어졌을 때, 얼마나 빨리 빌딩을 탈출할 수 있는지 구하는 프로그램을 작성하시오.
public class bj_5427_BFS {
    private static int n, m;
    private static String[][] map;
    private static boolean[][] visit;
    private static Queue<Node> q = new LinkedList<>();
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    private static final String ME = "@";
    private static final String FIRE = "*";
    private static final String EMPTY = ".";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            String[] input = br.readLine().split(" ");
            m = Integer.parseInt(input[0]);
            n = Integer.parseInt(input[1]);
            map = new String[n][m];
            visit = new boolean[n][m];
            q.clear();

            int x = 0, y = 0;
            for (int i = 0; i < n; i++) {
                input = br.readLine().split("");
                for (int j = 0; j < m; j++) {
                    map[i][j] = input[j];
                    if (FIRE.equals(map[i][j])) {
                        q.offer(new Node(i, j, 0, FIRE));
                    } else if (ME.equals(map[i][j])) {
                        x = i;
                        y = j;
                        visit[i][j] = true;
                    }
                }
            }

            q.offer(new Node(x, y, 1, ME));
            int cnt = goBFS();

            System.out.println(cnt == -1 ? "IMPOSSIBLE" : cnt);
        }
    }

    private static int goBFS() {
        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (now.name.equals(ME) && !isRange(nx, ny)) {
                    return now.cnt;
                }

                if (isRange(nx, ny)) {
                    if (now.name.equals(FIRE) && spreadFire(nx, ny)) {
                        q.offer(new Node(nx, ny, 0, FIRE));
                        map[nx][ny] = FIRE;
                    } else {
                        if (now.name.equals(ME) && map[nx][ny].equals(EMPTY) && !visit[nx][ny]) {
                            q.offer(new Node(nx, ny, now.cnt + 1, ME));
                            visit[nx][ny] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    private static boolean spreadFire(int x, int y) {
        return map[x][y].equals(ME) || map[x][y].equals(EMPTY);
    }


    private static class Node {
        private int x;
        private int y;
        private int cnt;
        private String name;

        public Node(int x, int y, int cnt, String name) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.name = name;
        }
    }
}

//불이 먼저 퍼진 후에 내가 움직여
//Queue를 하나만 만들고 불이 움직일 수 있는 경우를 먼저 넣어
//불이 다 움직이고 나서 마지막으로 내가 움직일 수 있는 반경을 넣어
//그러다가 만약에 내가 map 밖으로 나가면 끝
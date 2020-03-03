package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//도현이가 사는 도시는 N*M 크기의 모양이며, 1*1칸으로 나누어져 있다. 각 칸은 빈 칸 또는 벽이다.
//
//도현이는 학교에 가려고 한다. 도현이가 있는 곳은 항상 빈 칸이고, 학교도 빈 칸에 있다.
//도현이는 현재 있는 칸과 상하좌우로 인접한 칸으로 이동할 수 있다. 이때, 벽이 있는 칸으로는 이동할 수 없다. 또, 도시를 벗어날 수는 없다.
//
//준규는 도현이가 학교에 가지 못하게 빈 칸을 적절히 벽으로 바꾸려고 한다.
//이미 벽인 곳은 벽으로 바꿀 수 없고, 빈 칸만 벽으로 바꿀 수 있다.
//도현이와 학교가 있는 곳은 벽으로 바꿀 수 없다.
//
//도현이가 학교에 가지 못하게 하기 위해서 빈 칸을 벽으로 바꿔야하는 횟수의 최솟값을 구하는 프로그램을 작성하시오.
public class bj_1420_DFS_BFS {
    private static String[][] map;
    private static int start_x, start_y, end_x, end_y;

    private static final String EMPTY = ".";
    private static final String WALL = "#";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        map = new String[n][m];
        for (int i = 0; i < n; i++) {
            line = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = line[j];
                if (map[i][j].equals("K")) {
                    start_x = i;
                    start_y = j;
                }
                if (map[i][j].equals("H")) {
                    end_x = i;
                    end_y = j;
                }
            }
        }

        if (!approachedSchool(n, m, map)) {
            System.out.println(0);
            return;
        }

        for (int i = 1; i <= n * m; i++) {
            if (goDFS(n, m, i, 0)) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }

    private static boolean goDFS(int len_x, int len_y, int cnt, int depth) {
        if (cnt == depth) {
            String[][] copy = copyMap(len_x, len_y);
            if (!approachedSchool(len_x, len_y, copy)) {
                return true;
            }
            return false;
        }

        for (int i = 0; i < len_x; i++) {
            for (int j = 0; j < len_y; j++) {
                if (map[i][j].equals(EMPTY)) {
                    map[i][j] = WALL;
                    if (goDFS(len_x, len_y, cnt, depth + 1)) {
                        return true;
                    }
                    map[i][j] = EMPTY;
                }
            }
        }
        return false;
    }

    private static String[][] copyMap(int len_x, int len_y) {
        String[][] copy = new String[len_x][len_y];

        for (int i = 0; i < len_x; i++) {
            for (int j = 0; j < len_y; j++) {
                copy[i][j] = map[i][j];
            }
        }

        return copy;
    }

    private static boolean approachedSchool(int len_x, int len_y, String[][] map) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        boolean[][] visit = new boolean[len_x][len_y];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start_x, start_y));
        visit[start_x][start_y] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (isValue(nx, ny, len_x, len_y, visit, map)) {
                    if (isSchool(nx, ny)) {
                        return true;
                    }
                    q.add(new Node(nx, ny));
                    visit[nx][ny] = true;
                }
            }
        }

        return false;
    }

    private static boolean isValue(int x, int y, int len_x, int len_y, boolean[][] visit, String[][] map) {
        return x >= 0 && y >= 0 && x < len_x && y < len_y && !visit[x][y] && !map[x][y].equals(WALL);
    }

    private static boolean isSchool(int x, int y) {
        return x == end_x && y == end_y;
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


/*
4 5
.....
.K#..
...H.
.....

4 5
.....
.K...
...H.
.....

4 5
.....
.....
.K.H.
.....

10 10
K.........
..........
..........
..........
..........
..........
..........
..........
..........
.....H....
*/
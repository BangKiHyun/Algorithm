package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_2636_Re {
    private static final int AIR = -1;
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static int n, m;
    private static int[][] map;
    private static boolean[][] visited;
    private static Queue<Node> meltingQ = new LinkedList<>();
    private static Queue<Node> meltingCheesePoses = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n][m];
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        checkOutsideAir(0, 0);

        int ans = 0;
        int preCheeseCnt = 0;
        while (true) {
            findMeltingCheese();
            if (meltingQ.isEmpty()) break;

            preCheeseCnt = meltingQ.size();
            meltCheese();

            for (Node meltingCheesePos : meltingCheesePoses) {
                checkOutsideAir(meltingCheesePos.x, meltingCheesePos.y);
            }
            ans++;
        }

        System.out.println(ans + " " + preCheeseCnt);
    }

    private static void meltCheese() {
        while (!meltingQ.isEmpty()) {
            final Node cheesePos = meltingQ.poll();
            meltingCheesePoses.offer(new Node(cheesePos.x, cheesePos.y));
        }
    }

    private static void checkOutsideAir(int startX, int startY) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(startX, startY));
        visited[startX][startY] = true;
        map[startX][startY] = AIR;

        while (!q.isEmpty()) {
            final Node curNode = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curNode.x + DX[i];
                int ny = curNode.y + DY[i];

                if (isNewAir(nx, ny)) {
                    q.offer(new Node(nx, ny));
                    map[nx][ny] = AIR;
                    visited[nx][ny] = true;
                }
            }
        }
    }

    private static void findMeltingCheese() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isMeltingCheese(i, j)) {
                    meltingQ.offer(new Node(i, j));
                }
            }
        }
    }

    private static boolean isMeltingCheese(int x, int y) {
        return map[x][y] == 1 && isEdge(x, y);
    }

    private static boolean isNewAir(int x, int y) {
        return isRange(x, y) && !visited[x][y] && map[x][y] == 0;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= -0 && x < n && y < m;
    }

    private static boolean isEdge(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + DX[i];
            int ny = y + DY[i];

            if (map[nx][ny] == AIR) {
                return true;
            }
        }
        return false;
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

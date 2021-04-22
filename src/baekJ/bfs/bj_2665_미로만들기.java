package baekJ.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class bj_2665_미로만들기 {
    private static final int BLOCK = 0;
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static int n;
    private static int map[][];
    private static int visit[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visit = new int[n][n];
        for (int rawIdx = 0; rawIdx < n; rawIdx++) {
            String[] numbers = br.readLine().split("");
            int colIdx = 0;
            for (String number : numbers) {
                map[rawIdx][colIdx] = Integer.parseInt(number);
                visit[rawIdx][colIdx++] = Integer.MAX_VALUE;
            }
        }

        goBFS();
        System.out.println(visit[n - 1][n - 1]);
    }

    private static void goBFS() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0));
        visit[0][0] = 0;
        while (!q.isEmpty()) {
            final Node curNode = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = curNode.x + DX[dir];
                int ny = curNode.y + DY[dir];

                if (!isRange(nx, ny)) continue;
                if (visit[nx][ny] <= visit[curNode.x][curNode.y]) continue;

                if (map[nx][ny] != BLOCK) {
                    visit[nx][ny] = visit[curNode.x][curNode.y];
                } else {
                    visit[nx][ny] = visit[curNode.x][curNode.y] + 1;
                }
                q.add(new Node(nx, ny));
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
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

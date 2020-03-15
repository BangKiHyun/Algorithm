package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_2583_BFS {
    private static final int PAPER = 1;

    private static int n, m;
    private static int[][] area;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        area = new int[n][m];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int start_y = Integer.parseInt(st.nextToken());
            int start_x = Integer.parseInt(st.nextToken());
            int end_y = Integer.parseInt(st.nextToken());
            int end_x = Integer.parseInt(st.nextToken());

            checkArea(start_x, start_y, end_x, end_y);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (area[i][j] != PAPER) list.add(getEmptyArea(i, j));
            }
        }

        Collections.sort(list);
        System.out.println(list.size());
        for (int ans : list) {
            System.out.print(ans + " ");
        }
    }

    private static void checkArea(int start_x, int start_y, int end_x, int end_y) {
        for (int i = start_x; i < end_x; i++) {
            for (int j = start_y; j < end_y; j++) {
                area[i][j] = PAPER;
            }
        }
    }

    private static int getEmptyArea(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        area[x][y] = PAPER;
        q.offer(new Node(x, y));

        int cnt = 1;
        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (Range(nx, ny)) {
                    q.offer(new Node(nx, ny));
                    area[nx][ny] = PAPER;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static boolean Range(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m && area[x][y] != PAPER;
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
